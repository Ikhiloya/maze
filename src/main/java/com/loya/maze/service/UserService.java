package com.loya.maze.service;

import com.loya.maze.dao.AuthorityRepository;
import com.loya.maze.dao.UserRepository;
import com.loya.maze.entity.Authority;
import com.loya.maze.entity.User;
import com.loya.maze.security.AuthoritiesConstants;
import com.loya.maze.security.SecurityUtils;
import com.loya.maze.service.dto.FbUser;
import com.loya.maze.service.dto.UserDTO;
import com.loya.maze.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }


    public User registerUser(UserDTO userDTO, String password) {

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());

        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        log.debug("Created Information for User: {}", user);
        return user;
    }


    public void updateUser(String firstName, String lastName, String email) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(user -> {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    log.debug("Changed Information for User: {}", user);
                });
    }

    public boolean isUserExist(FbUser user) {
        boolean isUserExist = false;
        if (userRepository.findOneByEmailIgnoreCase(user.getEmail()).isPresent() && userRepository.findOneByUuid(user.getUuid()).isPresent()) {
            isUserExist = true;
        }
        return isUserExist;
    }
}

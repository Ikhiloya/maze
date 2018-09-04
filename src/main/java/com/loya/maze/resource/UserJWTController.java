package com.loya.maze.resource;


import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.rpc.Help;
import com.loya.maze.firebaseUtils.Helper;
import com.loya.maze.resource.vm.LoginVM;
import com.loya.maze.security.jwt.JWTConfigurer;
import com.loya.maze.security.jwt.TokenProvider;
import com.loya.maze.service.UserService;
import com.loya.maze.service.dto.FbUser;
import com.loya.maze.service.dto.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {

//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
//
//        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
//        String jwt = tokenProvider.createToken(authentication, rememberMe);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);

        return getJwtTokenResponseEntity(loginVM.getUsername(), loginVM.getPassword(), loginVM.isRememberMe());
    }


    @PostMapping("/fb-authenticate")
    @Timed
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody FbUser fbUser) {

        if (Helper.isTokenValid(fbUser.getIdToken())) {
            if (userService.isUserExist(fbUser)) {
                //user is created
                return getJwtTokenResponseEntity(fbUser.getUsername(), fbUser.getPassword(), fbUser.isRememberMe());

            } else {
                //user does not exist, create it
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(fbUser.getEmail());
                userDTO.setUuid(fbUser.getUuid());
                userDTO.setLogin(fbUser.getUsername());
                userService.registerUser(userDTO, fbUser.getPassword());
                return getJwtTokenResponseEntity(fbUser.getUsername(), fbUser.getPassword(), fbUser.isRememberMe());
            }


        }
        return new ResponseEntity<>(new JWTToken("Firebase Id token is not valid"), null, HttpStatus.PRECONDITION_FAILED);

    }

    private ResponseEntity<JWTToken> getJwtTokenResponseEntity(String username, String password, Boolean rememberMe2) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);


        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (rememberMe2 == null) ? false : rememberMe2;
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}

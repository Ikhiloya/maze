package com.loya.maze.security.jwt;

//import io.github.jhipster.config.JHipsterProperties;

import com.loya.maze.firebaseUtils.Helper;
import com.loya.maze.security.yaml.YAMLConfig;
import com.loya.maze.service.dto.FbUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final Base64.Encoder encoder = Base64.getEncoder();

    private String secretKey;

    private long tokenValidityInMilliseconds;

    Environment environment;

    private final YAMLConfig yamlConfig;

    private long tokenValidityInMillisecondsForRememberMe;

    // We use auth manager to validate the user credentials

    public TokenProvider(YAMLConfig yamlConfig) {
        this.yamlConfig = yamlConfig;

    }
//    public TokenProvider(Environment environment, Yaml yaml) {
//        this.environment = environment;
//        this.yaml = yaml;
//
//    }

    @PostConstruct
    public void init() {
//        String secret = environment.getRequiredProperty("secret");
//        this.secretKey = encoder.encodeToString(secret.getBytes());

        this.secretKey = encoder.encodeToString(yamlConfig.getAuth().getSecurity().getAuthentication().getJwt()
                .getSecret().getBytes(StandardCharsets.UTF_8));

        this.tokenValidityInMilliseconds =
                1000 * yamlConfig.getAuth().getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * yamlConfig.getAuth().getSecurity().getAuthentication().getJwt()
                        .getTokenValidityInSecondsForRememberMe();
    }


//    @PostConstruct
//    public void init() {
////        String secret = environment.getRequiredProperty("secret");
//        String secret = "secret";
//        this.secretKey = encoder.encodeToString(secret.getBytes());
//    }

    public String createToken(Authentication authentication, boolean rememberMe) {


        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();


//        Long tokenValidityInSeconds = 1800L;
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + tokenValidityInSeconds))  // in milliseconds
//                .compact();
    }

//    public String createTkn(Authentication authentication, boolean rememberMe) {
//
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        long now = (new Date()).getTime();
//        Date validity;
//        if (rememberMe) {
//            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
//        } else {
//            validity = new Date(now + this.tokenValidityInMilliseconds);
//        }
//
//
////        Long now = System.currentTimeMillis();
//        return  Jwts.builder()
//                .setSubject(authentication.getName())
//                // Convert to list of strings.
//                // This is important because it affects the way we get them back in the Gateway.
//                .claim(AUTHORITIES_KEY, authorities)
//                .setIssuedAt(new Date(now))
//                .setExpiration(validity)  // in milliseconds
//                .signWith(SignatureAlgorithm.HS512,secretKey )
//                .compact();
//
////        // Add token to header
////        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
//    }


    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}

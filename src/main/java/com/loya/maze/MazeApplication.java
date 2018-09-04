package com.loya.maze;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.loya.maze.resource.util.CORSFilter;
import com.loya.maze.security.yaml.YAMLConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class MazeApplication implements CommandLineRunner {


    private final YAMLConfig yamlConfig;

    public MazeApplication(YAMLConfig yamlConfig) {
        this.yamlConfig = yamlConfig;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MazeApplication.class, args);

        //config for Firebase Admin
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\IIMOKHAI\\Desktop\\Loya\\maze-21dd5-firebase-adminsdk-taa2n-203a2505ea.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://maze-21dd5.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registrationBean =
                new FilterRegistrationBean(new CORSFilter());
        registrationBean.setName("CORS FILTER");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("secret: " + yamlConfig.getAuth().getSecurity().getAuthentication().getJwt().getSecret());
        System.out.println("validity in seconds: " + yamlConfig.getAuth().getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds());
        System.out.println("validity fo token remember me " + yamlConfig.getAuth().getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe());
    }



}

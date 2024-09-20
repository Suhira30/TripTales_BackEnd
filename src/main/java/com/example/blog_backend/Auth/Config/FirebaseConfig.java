package com.example.blog_backend.Auth.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApplication() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/traveltales-blog-firebase-adminsdk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("blogtraveltales.appspot.com") // Set your Firebase Storage bucket here
                .build();

        return FirebaseApp.initializeApp(options);
    }
}

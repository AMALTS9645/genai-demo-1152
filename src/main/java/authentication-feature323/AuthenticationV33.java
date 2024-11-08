```java
//code-start

package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }
}

package com.example.loginapi.controller;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Handles user login requests.
     *
     * @param loginRequest contains the username and password.
     * @return a response entity with a success message or error message.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }
}

package com.example.loginapi.model;

public class LoginRequest {

    private String username;
    private String password;

    // Security: Ensure password is not exposed via toString or logs

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.loginapi.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /**
     * Authenticates the user based on the provided username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return true if the user is authenticated successfully, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        // Security: Validate inputs to prevent injection attacks
        // This is a placeholder. Actual implementation should validate against a database or external system
        if ("user".equals(username) && "password".equals(password)) {
            return true;
        }
        return false;
    }
}

//code-end
```
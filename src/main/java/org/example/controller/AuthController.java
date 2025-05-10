package org.example.controller;

import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.*;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AWSSecretsManager secretsManager = AWSSecretsManagerClientBuilder.defaultClient();

    @PostMapping("/login")
    public ResponseEntity<?> login(
            HttpServletRequest request,
            @RequestHeader("x-api-key") String loginApiKey,
            @RequestBody LoginRequest loginRequest) {

        // Step 1: Validate API Key from frontend header
        if (!isLoginKeyValid(loginApiKey)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        //  Step 2: Validate Credentials
        if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            request.getSession().setAttribute("authenticated", true);
            return ResponseEntity.ok(
                    new JSONObject()
                            .put("message", "Login successful")
                            .toString()
            );
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    private boolean isLoginKeyValid(String incomingKey) {
        try {
            String validKey = getApiKeyFromSecretsManager("api-keys/Varsha", "apiKeyValue");
            return incomingKey.equals(validKey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getApiKeyFromSecretsManager(String secretId, String keyName) {
        GetSecretValueRequest request = new GetSecretValueRequest().withSecretId(secretId);
        GetSecretValueResult result = secretsManager.getSecretValue(request);
        JSONObject json = new JSONObject(result.getSecretString());
        return json.getString(keyName);
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}

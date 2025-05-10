package org.example.controller;

import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.*;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final AWSSecretsManager secretsManager = AWSSecretsManagerClientBuilder.defaultClient();

    @GetMapping("/key")
    public ResponseEntity<String> getApiKeyValue() {
        //  Removed session check for now (frontend does not use Spring session)
        String key = getApiKeyFromSecretsManager("api-keys/Varsha", "apiKeyValue");
        return ResponseEntity.ok(new JSONObject().put("apiKey", key).toString());
    }

    private String getApiKeyFromSecretsManager(String secretId, String keyName) {
        GetSecretValueRequest request = new GetSecretValueRequest().withSecretId(secretId);
        GetSecretValueResult result = secretsManager.getSecretValue(request);
        JSONObject json = new JSONObject(result.getSecretString());
        return json.getString(keyName);
    }
}

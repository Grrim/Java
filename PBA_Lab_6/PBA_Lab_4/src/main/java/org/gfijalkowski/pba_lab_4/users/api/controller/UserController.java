package org.gfijalkowski.pba_lab_4.users.api.controller;

import org.gfijalkowski.pba_lab_4.users.api.model.User;
import org.gfijalkowski.pba_lab_4.users.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,
                                        @RequestHeader(value = "X-HMAC-SIGNATURE", required = false) String hmacSignature,
                                        BindingResult result) {


        String jsonPayload = serializeToJson(user);
        if (hmacSignature != null) {
            if (!validateHMACSignature(hmacSignature, jsonPayload)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid HMAC Signature");
            }
        }

        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id,
                                        @Valid @RequestBody User user,
                                        @RequestHeader(value = "X-JWS-SIGNATURE", required = false) String jwsSignature) {

        if (jwsSignature != null) {
            if (!validateJWSSignature(jwsSignature, user, id)) {
                System.out.println("Invalid JWS Signature: " + jwsSignature);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWS Signature");
            }
        }

        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public boolean validateJWSSignature(String receivedSignature, User user, UUID userIdFromUrl) {
        try {
            String secretKey = "123456";

            user.setId(userIdFromUrl);

            String payload = serializeToJson(user);
            if (payload == null) {
                return false;
            }

            String[] parts = receivedSignature.split("\\.");
            if (parts.length != 3) {
                return false;
            }

            String encodedHeader = parts[0];
            String encodedPayload = parts[1];
            String receivedSignaturePart = parts[2];

            String unsignedToken = encodedHeader + "." + encodedPayload;

            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            hmac.init(secretKeySpec);

            byte[] computedHash = hmac.doFinal(unsignedToken.getBytes());
            String computedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(computedHash);

            if (!computedSignature.equals(receivedSignaturePart)) {
                return false;
            }

            String decodedPayload = new String(Base64.getUrlDecoder().decode(encodedPayload));

            return decodedPayload.equals(payload);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateHMACSignature(String receivedSignature, String payload) {
        try {
            String payloadWithoutId = payload.replaceAll("\"id\":\\s*[^,}]+,?", "");

            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec("123456".getBytes(), "HmacSHA256");
            hmac.init(secretKeySpec);
            byte[] computedHash = hmac.doFinal(payloadWithoutId.getBytes());
            String computedSignature = Base64.getEncoder().encodeToString(computedHash);

            return computedSignature.equals(receivedSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String serializeToJson(User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(user);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

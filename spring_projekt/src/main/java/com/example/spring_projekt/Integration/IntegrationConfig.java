package com.example.spring_projekt.Integration;

import com.example.spring_projekt.Domain.User;
import com.example.spring_projekt.Domain.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.http.HttpMethod;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Map;

@Controller
@EnableIntegration
public class IntegrationConfig {

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public IntegrationFlow addUserFlow() {
        return IntegrationFlow
                .from(Http.inboundGateway("/integration/add_user")
                        .requestMapping(m -> m.methods(HttpMethod.POST))
                        .requestPayloadType(User.class))
                .channel("gateway.add_user")
                .handle((payload, headers) -> {
                    User user = (User) payload;
                    userRepository.save(user);
                    System.out.println("User added: " + user);
                    return "User added successfully";
                })
                .get();
    }

    @Bean
    public IntegrationFlow deleteUserFlow() {
        return IntegrationFlow.from(Http.inboundGateway("/integration/delete_user")
                        .requestMapping(m -> m.methods(HttpMethod.DELETE)))
                .channel("gateway.delete_user")
                .handle((payload, headers) -> {
                    try {
                        Map<String, String> params = objectMapper.readValue((byte[]) payload, new TypeReference<Map<String,String>>(){});
                        String name = params.get("name");
                        User user = userRepository.findUserByName(name);
                        if (user != null) {
                            userRepository.delete(user);
                            System.out.println("Delete user: " + user);
                            return user;
                        } else {
                            System.out.println("User not found: " + name);
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                        }
                    } catch (Exception execption) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, execption.getMessage());
                    }
                })
                .get();
    }

    @Bean
    public IntegrationFlow getUserFlow() {
        return IntegrationFlow.from(Http.inboundGateway("integration/get_user")
                        .requestMapping(m -> m.methods(HttpMethod.GET)))
                .channel("gateway.get_user")
                .handle((payload, headers) -> {
                    Map<String, ArrayList<String>> params = (Map<String, ArrayList<String>>) payload;
                    String name = params.get("name").get(0);
                    User user = userRepository.findUserByName(name);
                    if (user != null) {
                        System.out.println("Get user: " + user);
                        return user;
                    } else {
                        System.out.println("User not found: " + user);
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                    }
                })
                .get();
    }

    @Bean
    public IntegrationFlow editUserFlow() {
        return IntegrationFlow.from(Http.inboundGateway("/integration/edit_user")
                        .requestMapping(m -> m.methods(HttpMethod.POST))
                        .requestPayloadType(String.class))
                .channel("gateway.edit_user")
                .handle((payload, headers) -> {
                    try {
                        User previousUser = objectMapper.readValue(payload.toString(), User.class);
                        User user = userRepository.findUserByName(previousUser.getName());
                        user.setName(previousUser.getName());
                        user.setEmail(previousUser.getEmail());
                        userRepository.save(user);
                        return user;
                    } catch (Exception execption) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, execption.getMessage());
                    }
                })
                .get();
    }

}
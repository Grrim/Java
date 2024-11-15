package com.example.spring_projekt.SAOP;
import com.example.spring_projekt.Domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.zut.springsoap.gen.GetUserRequest;
import pl.edu.zut.springsoap.gen.GetUserResponse;
import pl.edu.zut.springsoap.gen.User;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://www.zut.edu.pl/springsoap/gen";

    @Autowired
    private UserRepository userRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        User user = userRepository.findUserByName(request.getName());
        if (user != null) {
            response.setName(user.getName());
            response.setEmail(user.getEmail());
        }
        return response;
    }
}

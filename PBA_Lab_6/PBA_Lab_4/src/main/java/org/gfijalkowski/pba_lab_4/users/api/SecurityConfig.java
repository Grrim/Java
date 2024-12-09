package org.gfijalkowski.pba_lab_4.users.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        Resource resource = new ClassPathResource("public_key.pem");

        try (InputStream inputStream = resource.getInputStream()) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(inputStream);

            RSAPublicKey rsaPublicKey = (RSAPublicKey) cert.getPublicKey();

            return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/users").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/users").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/users/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("ROLE_USER")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("fg44317")
                        .password("123456")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("auth-server")
                .clientId("pba_user")
                .clientSecret("123456")
                .tokenUri("https://pba-auth-server.herokuapp.com/oauth/token")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        return new InMemoryClientRegistrationRepository(clientRegistration);

    }




}

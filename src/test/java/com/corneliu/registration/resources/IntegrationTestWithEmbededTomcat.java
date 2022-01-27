package com.corneliu.registration.resources;

import com.corneliu.registration.UserRegistrationServiceApp;
import com.corneliu.registration.model.dto.CreateUserRequest;
import com.corneliu.registration.model.dto.LoginRequest;
import com.corneliu.registration.model.dto.LoginResponse;
import com.corneliu.registration.model.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = UserRegistrationServiceApp.class
)
public class IntegrationTestWithEmbededTomcat {


    private static final String EMAIL = "corneliu@yahoo.com";
    private static final String NAME = "corneliu";
    private static final String PASS = "pass";

    @Test
    public void test() {
        WebClient client = WebClient.create("http://localhost:9991");

        ClientResponse response = createUser(client);
        assertTrue(response.statusCode().is2xxSuccessful());

        String token = login(client);
        User user = getUser(client, token);

        assertNotNull(user);
        assertEquals(EMAIL, user.getEmail());
        assertEquals(NAME, user.getName());
    }

    private ClientResponse createUser(WebClient client) {
        CreateUserRequest request = new CreateUserRequest(NAME, EMAIL, PASS);

        return client.post()
                .uri("/auth/sign-up")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), CreateUserRequest.class)
                .exchange()
                .block();
    }

    private String login(WebClient client) {
        LoginRequest loginRequest = new LoginRequest(EMAIL, PASS);
        LoginResponse block = client.post()
                .uri("/auth/log-in")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(LoginResponse.class)
                .block();

        return block.getToken();
    }

    private User getUser(WebClient client, String token) {
        return client.get()
                    .uri("/users/1")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("auth-token", token)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
    }
}

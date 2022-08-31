package guru.qa.springproject;

import guru.qa.springproject.domain.SignUpInfo;
import guru.qa.springproject.domain.UserInfo;
import guru.qa.springproject.domain.UserMessage;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.http.ContentType.JSON;

import static io.restassured.RestAssured.with;

public class MessengerControllerTest {

    private final RequestSpecification spec =
            with()
                    .baseUri("http://localhost:8081")
                    .basePath("/");

    @Test
    public void signUpTest() {
        SignUpInfo signUpInfo = SignUpInfo.builder()
                .name("Viktor")
                .surname("Rachynskyi")
                .build();

        UserInfo userInfos = spec
                .contentType(JSON)
                .body(signUpInfo)
                .when()
                .post("api/signup")
                .then()
                .statusCode(200)
                .extract()
                .as(UserInfo.class);
    }

    @Test
    public void addTestUsers() {
        SignUpInfo signUpInfo1 = SignUpInfo.builder()
                .name("Olga")
                .surname("Kot")
                .build();

        SignUpInfo signUpInfo2 = SignUpInfo.builder()
                .name("Dima")
                .surname("Kovalenko")
                .build();

        spec
                .contentType(JSON)
                .body(signUpInfo1)
                .when()
                .post("api/signup");

        spec
                .contentType(JSON)
                .body(signUpInfo2)
                .when()
                .post("api/signup");
    }

    @Test
    public void getUsers() {

        List<Integer> userIDs = List.of(spec
                .get("api/user")
                .then()
                .statusCode(200)
                .extract()
                .as(Integer[].class));
    }

    @Test
    public void getUsersWithQueryParams() {

        List<Integer> userIDs = List.of(spec
                .queryParam("name", "Olga")
                .queryParam("surname", "Kot")
                .when()
                .get("api/user")
                .then()
                .statusCode(200)
                .extract()
                .as(Integer[].class));
    }

    @Test
    public void sendMessage() {

        UserMessage requestMessage = UserMessage.builder()
                .message("Hello. How are you?")
                .userFrom(1)
                .userTo(2)
                .build();

        UserMessage responseMessage = spec
                .contentType(JSON)
                .body(requestMessage)
                .when()
                .post("api/user/message")
                .then()
                .statusCode(200)
                .extract()
                .as(UserMessage.class);
    }

    @Test
    public void getMessages() {
        List<UserMessage> messages = List.of(spec
                .get("api/user/messages/2")
                .then()
                .statusCode(200)
                .extract()
                .as(UserMessage[].class));
    }
}

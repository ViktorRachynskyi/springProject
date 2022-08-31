package guru.qa.springproject;

import guru.qa.springproject.domain.SignUpInfo;
import guru.qa.springproject.domain.UserInfo;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static io.restassured.http.ContentType.JSON;

import static io.restassured.RestAssured.with;

public class MessengerControllerTest {

    private RequestSpecification spec =
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
                .get("api/getUsers")
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
                .get("api/getUsers")
                .then()
                .statusCode(200)
                .extract()
                .as(Integer[].class));
    }
}

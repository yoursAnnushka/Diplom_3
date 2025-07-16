package API;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class UserSteps {

    public static final String ENDPOINT_CREATE_USER = "api/auth/register";
    public static final String ENDPOINT_USER_LOGIN = "api/auth/login";
    public static final String ENDPOINT_DELETE_USER = "api/auth/user";
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(ENDPOINT_CREATE_USER)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(String email, String password) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"email\": \""+ email +"\",\n" +
                        "\"password\": \""+ password +"\"\n" +
                        "}")
                .when()
                .post(ENDPOINT_USER_LOGIN)
                .then();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"accessToken\": \""+ accessToken +"\"\n" +
                        "}")
                .when()
                .delete(ENDPOINT_DELETE_USER)
                .then();
    }
}

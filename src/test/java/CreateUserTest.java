import API.User;
import API.UserSteps;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static API.User.getRandomUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreateUserTest {
    WebDriver driver;
    CreateUserPage createUserPage;
    User user;
    UserSteps userSteps;

    @Before
    public void startUpSecond() {
        driver = WebDriverCreator.createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(CreateUserPage.CREATE_USER_PAGE_URL);
        createUserPage = new CreateUserPage(driver);
        user = getRandomUser();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void createUserTest() {
        createUserPage.setNameField(user.getName());
        createUserPage.setEmailField(user.getEmail());
        createUserPage.setPasswordField(user.getPassword());
        createUserPage.clickRegisterButton();
        assertNull(createUserPage.getErrorMessage());
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля при регистрации")
    public void createUserWithInvalidPasswordTest() {
        createUserPage.setNameField(user.getName());
        createUserPage.setEmailField(user.getEmail());
        createUserPage.setPasswordField(RandomStringUtils.randomAlphabetic(4));
        createUserPage.clickRegisterButton();
        assertEquals("Некорректный пароль", createUserPage.getErrorMessage());
    }

    @After
    public void tearDown() {
        try {
            String accessToken = userSteps.userLogin(user.getEmail(), user.getPassword())
                    .extract().body().path("accessToken");

            if (accessToken != null) {
                String accessTokenWithoutBearer = accessToken.replace("Bearer ", "");
                userSteps.deleteUser(accessTokenWithoutBearer);
            }
        } catch (Exception exception) {
            System.out.println("Пользователь не был удалён");
        } finally {
            driver.quit();
        }
    }
}

import API.User;
import API.UserSteps;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static API.User.getRandomUser;
import static org.junit.Assert.assertTrue;

public class UserLoginTest {
    WebDriver driver;
    HomePage homePage;
    User user;
    UserSteps userSteps;

    @Before
    public void startUpSecond() {
        driver = WebDriverCreator.createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        user = getRandomUser();
        userSteps = new UserSteps();
        userSteps.createUser(user);
    }

    @Test
    @DisplayName("Проверка входа по кнопке Войти в аккаунт")
    public void clickHomeUserLoginButtonLoginUserTest() {
        driver.get(HomePage.HOME_PAGE_URL);
        HomePage homePage = new HomePage(driver);
        UserLoginPage userLoginPage = homePage.clickHomeUserLoginButton();
        userLoginPage.setEmailField(user.getEmail());
        userLoginPage.setPasswordField(user.getPassword());
        userLoginPage.clickLoginButton();
        assertTrue(homePage.isLogin());
    }

    @Test
    @DisplayName("Проверка входа через кнопку Личный кабинет")
    public void clickHomeMyAccountButtonLoginUserTest() {
        driver.get(HomePage.HOME_PAGE_URL);
        HomePage homePage = new HomePage(driver);
        UserLoginPage userLoginPage = homePage.clickHomeMyAccountButton();
        userLoginPage.setEmailField(user.getEmail());
        userLoginPage.setPasswordField(user.getPassword());
        userLoginPage.clickLoginButton();
        assertTrue(homePage.isLogin());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void clickRegisterButtonLoginUserTest() {
        driver.get(CreateUserPage.CREATE_USER_PAGE_URL);
        CreateUserPage createUserPage = new CreateUserPage(driver);
        UserLoginPage userLoginPage = createUserPage.clickCreateUserPageLoginButton();
        userLoginPage.setEmailField(user.getEmail());
        userLoginPage.setPasswordField(user.getPassword());
        homePage = userLoginPage.clickLoginButton();
        assertTrue(homePage.isLogin());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void clickPasswordRecoveryPageLoginButtonLoginUserTest() {
        driver.get(PasswordRecoveryPage.PASSWORD_RECOVERY_PAGE_URL);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        UserLoginPage userLoginPage = passwordRecoveryPage.clickPasswordRecoveryPageLoginButton();
        userLoginPage.setEmailField(user.getEmail());
        userLoginPage.setPasswordField(user.getPassword());
        homePage = userLoginPage.clickLoginButton();
        assertTrue(homePage.isLogin());
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

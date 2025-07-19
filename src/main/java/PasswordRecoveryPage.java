import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordRecoveryPage {
    private WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String PASSWORD_RECOVERY_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private By passwordRecoveryPageLoginButton = By.xpath("//a[@class=\"Auth_link__1fOlj\"]");

    @Step("Нажатие кнопки Войти")
    public final UserLoginPage clickPasswordRecoveryPageLoginButton() {
        WebElement element = driver.findElement(passwordRecoveryPageLoginButton);
        element.click();
        return new UserLoginPage(driver);
    }
}

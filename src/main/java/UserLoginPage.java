import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserLoginPage {
    private WebDriver driver;

    public UserLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    private By passwordField = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    private By loginButton = By.xpath("//form//button");

    @Step("Заполнение поля Email")
    public final void setEmailField(String email) {
        WebElement element = driver.findElement(emailField);
        element.sendKeys(email);
    }

    @Step("Заполнение поля пароль")
    public final void setPasswordField(String password) {
        WebElement element = driver.findElement(passwordField);
        element.sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public final HomePage clickLoginButton() {
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new HomePage(driver);
    }
}

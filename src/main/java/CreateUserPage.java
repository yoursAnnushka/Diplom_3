import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateUserPage {
    private WebDriver driver;

    public CreateUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String CREATE_USER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private By nameField = By.xpath("//label[contains(text(),\"Имя\")]/following-sibling::input");
    private By emailField = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    private By passwordField = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    private By registerButton = By.xpath("//form//button");
    private By errorMessage = By.xpath("//p[@class=\"input__error text_type_main-default\"]");
    private By createUserPageLoginButton = By.xpath("//a[@class=\"Auth_link__1fOlj\"]");

    @Step("Заполнение поля имя")
    public final void setNameField(String name) {
        WebElement element = driver.findElement(nameField);
        element.sendKeys(name);
    }

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

    @Step("Нажатие кнопки Зарегистрироваться")
    public final UserLoginPage clickRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        element.click();
        return new UserLoginPage(driver);
    }

    @Step("Нажатие кнопки Войти")
    public final UserLoginPage clickCreateUserPageLoginButton() {
        WebElement element = driver.findElement(createUserPageLoginButton);
        element.click();
        return new UserLoginPage(driver);
    }

    @Step("Проверяем сообщение об ошибке")
    public final String getErrorMessage() {
        try {
            WebElement element = driver.findElement(errorMessage);
            return element.getText();
        } catch (Exception exception) {
            return null;
        }
    }
}

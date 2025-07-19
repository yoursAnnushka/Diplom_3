import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private By createOrderButton = By.xpath("//button[text()=\"Оформить заказ\"]");
    private By homeMyAccountButton = By.xpath("//nav//*[contains(text(),\"Личный Кабинет\")]");
    private By homeUserLoginButton = By.xpath("//button[text()=\"Войти в аккаунт\"]");
    private By bun = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Булки\")]//parent::div");
    private By sauce = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Соусы\")]//parent::div");
    private By toppings = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Начинки\")]//parent::div");
    private By activeTab = By.xpath("//section[contains(@class, \"BurgerIngredients_ingredients\")]//div[contains(@class, \"tab_tab_type_current\")]");

    @Step("Нажатие кнопки Булки в конструкторе")
    public final void chooseBun() {
        WebElement element = driver.findElement(bun);
        element.click();
    }

    @Step("Нажатие кнопки Соусы в конструкторе")
    public final void chooseSauce() {
        WebElement element = driver.findElement(sauce);
        element.click();
    }

    @Step("Нажатие кнопки Начинки в конструкторе")
    public final void chooseToppings() {
        WebElement element = driver.findElement(toppings);
        element.click();
    }

    public final String getActiveTabText() {
        return driver.findElement(activeTab).getText();
    }

    @Step("Нажатие кнопки Личный Кабинет")
    public final UserLoginPage clickHomeMyAccountButton() {
        WebElement element = driver.findElement(homeMyAccountButton);
            element.click();
            return new UserLoginPage(driver);

    }

    @Step("Нажатие кнопки Войти в аккаунт")
    public UserLoginPage clickHomeUserLoginButton() {
        WebElement element = driver.findElement(homeUserLoginButton);
        element.click();
        return new UserLoginPage(driver);
    }

    public boolean isLogin() {
        List<WebElement> elements = driver.findElements(createOrderButton);
        return !elements.isEmpty();
    }
}

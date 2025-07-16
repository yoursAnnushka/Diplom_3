import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    WebDriver driver;
    HomePage homePage;

    @Before
    public void startUpSecond() {
        driver = WebDriverCreator.createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    public void clickChooseBunInHomePageIsActiveTabBunTest() {
        homePage.chooseToppings();
        homePage.chooseBun();
        assertEquals("Булки", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void clickChooseSauceInHomePageIsActiveTabSauceTest() {
        homePage.chooseSauce();
        assertEquals("Соусы", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Проверка переходов к разделу Начинки")
    public void clickChooseToppingsInHomePageIsActiveTabToppingsTest() {
        homePage.chooseToppings();
        assertEquals("Начинки", homePage.getActiveTabText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

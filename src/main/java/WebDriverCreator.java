import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }

        switch (browser.toLowerCase()) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createYandexDriver() {
        String driversPath = System.getenv("BROWSER_DRIVERS");
        String driverFilename = System.getenv("YANDEX_BROWSER_DRIVER_FILENAME");
        String browserPath = System.getenv("YANDEX_BROWSER_PATH");

        System.setProperty("webdriver.chrome.driver", String.format("%s/%s", driversPath, driverFilename));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(browserPath);
        return new ChromeDriver(options);
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }
}

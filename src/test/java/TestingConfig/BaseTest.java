package TestingConfig;
import configurations.ConfigManager;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Util.ExcelReader;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigManager configManager = new ConfigManager();
    protected ExcelReader excelReader = new ExcelReader();


    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        String browser = configManager.get("browser").toLowerCase().trim();
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "chrome":
            default: 
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }

        driver.manage().window().maximize();
        System.out.println(configManager.get("url"));
        driver.get(configManager.get("url"));
    }
    
    public void stepWithScreenshotOnFail(Runnable action) {
        try {
            action.run();
        } catch (Throwable t) {

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Step failure screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );

            throw t;
        }
    }

    

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
         try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

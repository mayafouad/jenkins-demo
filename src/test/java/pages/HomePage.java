package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By myAccount = By.xpath("//span[text()='My Account']");
    By register = By.linkText("Register");
    By login = By.linkText("Login");

    By desktops = By.linkText("Desktops");
    By showAllDesktops = By.linkText("Show AllDesktops");

    private void clickWhenReady(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(locator))
            .click();
    }
   

    @SuppressWarnings("null")
    public void goToRegister(){
        Allure.step("Navigating to the registration page by clicking on My Account and then Register.");
       
        clickWhenReady(myAccount);
        driver.findElement(register).click();
    }

    @SuppressWarnings("null")
    public void goToLogin(){
        Allure.step("Navigating to the login page by clicking on My Account and then Login.");
        clickWhenReady(myAccount);
        clickWhenReady(login);
    }

    
    @SuppressWarnings("null")
    public void goToDesktops(){
        Allure.step("Navigating to the desktops page.");
       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
            ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(desktops)
            )
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(showAllDesktops)
        ).click();
    }

    
}
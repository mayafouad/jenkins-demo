package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DTOs.RegisterUser;
import io.qameta.allure.Allure;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By email = By.id("input-email");
    By telephone = By.id("input-telephone");
    By password = By.id("input-password");
    By confirmPassword = By.id("input-confirm");
    By privacyPolicy = By.name("agree");
    By continueBtn = By.xpath("//input[@value='Continue']");

    By firstNameWarning = By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]");
    By lastNameWarning = By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]");
    By emailWarning = By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid')]");
    By passwordWarning = By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]");
    By confirmPasswordWarning = By.xpath("//div[contains(text(),'Password confirmation does not match password!')]");
    By telephoneWarning = By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]");
    By policyConfirmationWarning = By.xpath("//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]");

    public void register(RegisterUser user, String click){

        Allure.step("Fill registration form with user data: " + user.getEmail()
        + ", " + user.getFirstName() + ", " + user.getLastName() + ", "
        + user.getTelephone() + ", " + user.getPassword());

        waitUntilVisible(firstName).sendKeys(user.getFirstName());
        waitUntilVisible(lastName).sendKeys(user.getLastName());
        waitUntilVisible(email).sendKeys(user.getEmail());
        waitUntilVisible(telephone).sendKeys(user.getTelephone());
        waitUntilVisible(password).sendKeys(user.getPassword());
        waitUntilVisible(confirmPassword).sendKeys(user.getConfirmPassword());

        if (click.equalsIgnoreCase("TRUE")) {
            waitUntilClickable(privacyPolicy).click();
        }

        waitUntilClickable(continueBtn).click();
    }

    public boolean isEmailWarningDisplayed(){
        Allure.step("Check if email warning is displayed");
        return waitUntilVisible(emailWarning).isDisplayed();
    }

    public boolean isPasswordWarningDisplayed(){
        Allure.step("Check if password warning is displayed");
        return waitUntilVisible(passwordWarning).isDisplayed();
    }

    public boolean isTelephoneWarningDisplayed(){
        Allure.step("Check if telephone warning is displayed");
        return waitUntilVisible(telephoneWarning).isDisplayed();
    }

    public boolean isFirstNameWarningDisplayed() {
        Allure.step("Check if first name warning is displayed");
        return waitUntilVisible(firstNameWarning).isDisplayed();
    }

    public boolean isLastNameWarningDisplayed() {
        Allure.step("Check if last name warning is displayed");
        return waitUntilVisible(lastNameWarning).isDisplayed();
    }

    public boolean isConfirmPasswordWarningDisplayed() {
        Allure.step("Check if confirm password warning is displayed");
        return waitUntilVisible(confirmPasswordWarning).isDisplayed();
    }

    public boolean isPolicyConfirmationWarningDisplayed() {
        Allure.step("Check if policy confirmation warning is displayed");
        return waitUntilVisible(policyConfirmationWarning).isDisplayed();
    }

    // Helper Methods
    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
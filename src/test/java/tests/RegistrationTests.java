package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.commons.math3.util.Pair;

import DTOs.RegisterUser;
import TestingConfig.BaseTest;
import io.qameta.allure.Allure;
import pages.HomePage;
import pages.RegisterPage;

public class RegistrationTests extends BaseTest {

    @DataProvider(name = "registerData")
    public Object[][] getData() throws Exception {

        List<Pair<RegisterUser, String>> data =
                excelReader.readRegisterUsers(configManager.get("users"));

        Object[][] result = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).getKey();
            result[i][1] = data.get(i).getValue();
        }

        return result;
    }

    @Test(dataProvider = "registerData")
    public void registrationTest(RegisterUser user, String click) {

        HomePage home = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        stepWithScreenshotOnFail(() -> {
            home.goToRegister();
            registerPage.register(user, click);
        });

        SoftAssert softAssert = new SoftAssert();

        // First Name Validation
        if (user.getFirstName() == null
                || user.getFirstName().trim().isEmpty()
                || user.getFirstName().length() > 32) {

            Allure.step("Verify First Name warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isFirstNameWarningDisplayed()));
        }

        // Last Name Validation
        if (user.getLastName() == null
                || user.getLastName().trim().isEmpty()
                || user.getLastName().length() > 32) {

            Allure.step("Verify Last Name warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isLastNameWarningDisplayed()));
        }

        // Email Validation
        if (user.getEmail() == null
                || user.getEmail().trim().isEmpty()
                || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

            Allure.step("Verify Email warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isEmailWarningDisplayed()));
        }

        // Telephone Validation
        if (user.getTelephone() == null
                || user.getTelephone().trim().isEmpty()
                || user.getTelephone().length() < 3
                || user.getTelephone().length() > 32) {

            Allure.step("Verify Telephone warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isTelephoneWarningDisplayed()));
        }

        // Privacy Policy Validation
        if (!click.equalsIgnoreCase("TRUE")) {

            Allure.step("Verify Privacy Policy warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isPolicyConfirmationWarningDisplayed()));
        }

        // Password Validation
        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()
                || user.getPassword().length() < 4
                || user.getPassword().length() > 20) {

            Allure.step("Verify Password warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isPasswordWarningDisplayed()));

        }

        // Confirm Password Validation
        else if (user.getConfirmPassword() == null
                || user.getConfirmPassword().trim().isEmpty()
                || !user.getConfirmPassword().equals(user.getPassword())) {

            Allure.step("Verify Confirm Password warning");

            stepWithScreenshotOnFail(() ->
                    softAssert.assertTrue(registerPage.isConfirmPasswordWarningDisplayed()));
        }

        softAssert.assertAll();
    }
}
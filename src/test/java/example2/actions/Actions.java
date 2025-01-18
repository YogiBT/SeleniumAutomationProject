package example2.actions;

import example2.pages.AutomationExercisePage;
import example2.pages.ForgotPasswordPage;
import example2.pages.RegistrationFormPage;
import example2.pages.TableTaskPage;
import org.openqa.selenium.WebDriver;

public class Actions {

    TableTaskPage tableTaskPage;
    ForgotPasswordPage forgotPassword;

    RegistrationFormPage registrationForm;
    AutomationExercisePage automationExercisePage;

    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        forgotPassword = new ForgotPasswordPage(driver);
        registrationForm = new RegistrationFormPage(driver);
        automationExercisePage = new AutomationExercisePage(driver);
    }

    /**
     * Performs the forgot password action by typing the email, clicking submit, and validating the result.
     *
     * @return true if the forgot password functionality is validated successfully, false otherwise
     */
    public boolean doForgotPassword() {
        forgotPassword.typeEmail("test@test.com");
        forgotPassword.clickSubmit();
        return forgotPassword.validateForgotPassword();
    }

    public boolean doForgotPasswordFromExcel(String email) {
        forgotPassword.typeEmail(email);
        forgotPassword.clickSubmit();
        return forgotPassword.validateForgotPassword();
    }

    /**
     * Performs the registration form action by filling the contact name, date, and clicking submit.
     *
     * @param name the contact name to be filled
     * @param date the date to be filled
     */
    public boolean doRegistrationForm(String name, String date) {
        registrationForm.fillContactName(name);
        registrationForm.fillContactNumber("012-3456789");
        registrationForm.fillDate(date);
        registrationForm.selectPaymentMethod("cashondelivery");
        registrationForm.clickRegister();
        return registrationForm.validateFormSuccessSubmitted();
    }

    /**
     * doSubscription form action by filling the contact name, date, and clicking submit.
     *
     * @param email the contact email to be filled
     */
    public boolean doSubscription(String email) {
        return automationExercisePage.fillSubscription(email);
    }
}
package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DateComponent;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            inputAddress = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            sityInput = $("#react-select-4-input"),
            submitForm = $("#submit");


    DateComponent dateComponent = new DateComponent();
    CheckResultComponent checkResult = new CheckResultComponent();


    public RegistrationPage openPage() {
        open("/automation-practice-form");


        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        dateComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String pictureName) {
        uploadPicture.uploadFromClasspath(pictureName);

        return this;
    }

    public RegistrationPage inputAddress(String value) {
        inputAddress.setValue(value);

        return this;
    }

    public RegistrationPage stateInput(String value) {
        stateInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage sityInput(String value) {
        sityInput.setValue(value).pressEnter();

        return this;
    }


    public RegistrationPage submitForm() {
        submitForm.click();

        return this;
    }


    public RegistrationPage checkResult(String key, String value){
        checkResult.result(key,value);
        return this;
    }
}
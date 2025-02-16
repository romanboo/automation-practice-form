package tests;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CheckResultComponent;
import utils.RandomUtils;


public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();
    CheckResultComponent checkResultComponent = new CheckResultComponent();

    String firstName = randomUtils.getFirstName();
    String lastName = randomUtils.getLastName();
    String email = randomUtils.getEmail();
    String gender = randomUtils.getGender();
    String phone = randomUtils.getPhone();
    String day = randomUtils.getDay();
    String month = randomUtils.getMoth();
    String year = randomUtils.getYear();
    String subjects = randomUtils.getSubjects();
    String hobbies = randomUtils.getHobbies();
    String picture = randomUtils.getPicture();
    String address = randomUtils.getCurrentAddres();
    String state = randomUtils.getState();
    String city = randomUtils.getCity(state);

    @Test
    void successfulRegistrationTest() throws InterruptedException {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setDateOfBirth(day, month, year)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(picture)
                .inputAddress(address)
                .stateInput(state)
                .cityInput(city)
                .submitForm();

        checkResultComponent
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    void shortRegistrationFormTest() {
        registrationPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("userEmail@gmail.com")
                .setGender("Male")
                .setUserNumber("8908456342")
                .submitForm();
    }

    @Test
    void negativeShortRegistrationFormTest() {
        registrationPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("userEmail@gmailcom")
                .setGender("Male")
                .setUserNumber("8908456342")
                .submitForm()
                .checkPracticeForm();

    }
}
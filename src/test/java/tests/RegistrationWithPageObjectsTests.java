package tests;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;



public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("userEmail@gmail.com")
                .setGender("Male")
                .setUserNumber("8908456342")
                .setDateOfBirth("15", "May", "1980")
                .setSubjects("Chemistry")
                .setHobbies("Sports")
                .uploadPicture("photo_2025.jpg")
                .inputAddress("Ульяновск")
                .stateInput("haryana")
                .sityInput("karnal")
                .submitForm()
                .checkResult("Student Name", "Иван Иванов");

    }

    @Test
    void shortRegistrationFormTest() {
        registrationPage.openPage()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("userEmail@gmail.com")
                .setGender("Male")
                .setUserNumber("8908456342")
                .submitForm()
                .checkResult("Student Name", "Иван Иванов");

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
                .checkResult("Student Name", "Иван Иванов");

    }
}
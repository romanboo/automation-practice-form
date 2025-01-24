import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm {

    @AfterEach
    public void logout(){
        Selenide.closeWebDriver();
    }

    @BeforeEach
    public void beforeEach(){
        Configuration.pageLoadStrategy ="eager";
    }


    @Test
    void successfullRegistration() {
        open("https://demoqa.com/automation-practice-form");
        $("[id='firstName']").setValue("Иван");
        $("[id='lastName']").setValue("Иванов");
        $("[id='userEmail']").setValue("userEmail@gmail.com");
        $("[for = gender-radio-1]").click();
        $("[id ='userNumber']").setValue("8908456342");
        $("[id ='dateOfBirthInput']").click();
        $("[class ='react-datepicker__month-select']").selectOptionByValue("4");
        $("[class ='react-datepicker__year-select']").selectOptionByValue("1980");
        $(".react-datepicker__month .react-datepicker__day--015").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("[for ='hobbies-checkbox-1']").click();
        $("[id=uploadPicture]").uploadFromClasspath("photo_2025.jpg");
        $("[id=currentAddress]").click();
        $("[id=currentAddress]").setValue("Ульяновск");
        $("#react-select-3-input").setValue("haryana").pressEnter();
        $("#react-select-4-input").setValue("karnal").pressEnter();
        $("#submit").click();
        $("[class=table-responsive]").shouldHave(text("Иван"));
        $("[class=table-responsive]").shouldHave(text("Иванов"));
        $("[class=table-responsive]").shouldHave(text("userEmail@gmail.com"));
        $("[class=table-responsive]").shouldHave(text("Male"));
        $("[class=table-responsive]").shouldHave(text("8908456342"));
        $("[class=table-responsive]").shouldHave(text("15 May,1980"));
        $("[class=table-responsive]").shouldHave(text("Chemistry"));
        $("[class=table-responsive]").shouldHave(text("Sports"));
        $("[class=table-responsive]").shouldHave(text("photo_2025.jpg"));
        $("[class=table-responsive]").shouldHave(text("haryana"));
        $("[class=table-responsive]").shouldHave(text("karnal"));

    }

// - Откройте страницу Selenide в Github
// - Перейдите в раздел Wiki проекта
// - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
// - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

    @Test
    void wikiSelenideOnGithub() {
        open("https://github.com/selenide/selenide");
        $("[data-content = 'Wiki']").shouldNotBe(hidden).click();
        $("[class=markdown-body]").$(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));
//        $(byText("3. Using JUnit5 extend test class:")).shouldBe(visible);
//        $(byXpath("//h4[text()='3. Using JUnit5 extend test class:']")).shouldBe(visible);visible
    }

}

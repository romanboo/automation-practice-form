package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import helpers.Attach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@Epic("DemoQA Automation")
@Feature("Form Testing")
public class DemoQAParam extends TestBase{

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    @Story("Submit TextBox Form")
    @Tag("regress")
    @Description("Test verifies that TextBox form submits data correctly")
    @DisplayName("Проверка работы TextBox формы")
    @Link(name = "DemoQA TextBox", url = "https://demoqa.com/text-box")
    void textBoxFormShouldSubmitCorrectData() {
        step("Открываем страницу TextBox", () -> {
            open("/text-box");
            takeScreenshot();
        });

        step("Заполняем форму данными", () -> {
            $("#userName").setValue("Test User");
            $("#userEmail").setValue("test@example.com");
            $("#currentAddress").setValue("123 Test Street");
            $("#permanentAddress").setValue("456 Permanent Address");
            takeScreenshot();
        });

        step("Отправляем форму", () -> {
            $("#submit").click();
            takeScreenshot();
        });

        step("Проверяем результаты", () -> {
            $("#output").shouldBe(visible);
            $("#name").shouldHave(text("Test User"));
            $("#email").shouldHave(text("test@example.com"));
            $("#currentAddress", 1).shouldHave(text("123 Test Street"));
            $("#permanentAddress", 1).shouldHave(text("456 Permanent Address"));
            takeScreenshot();
        });
    }

    @Test
    @Story("CheckBox Selection")
    @Tag("regress")
    @Description("Test verifies that multiple options can be selected in CheckBox")
    @DisplayName("Проверка выбора чекбоксов")
    void checkBoxShouldAllowSelectingMultipleOptions() {
        step("Открываем страницу CheckBox", () -> {
            open("/checkbox");
            takeScreenshot();
        });

        step("Раскрываем все варианты", () -> {
            $(".rct-option-expand-all").click();
            takeScreenshot();
        });

        step("Выбираем несколько чекбоксов", () -> {
            $x("//label[contains(.,'Angular')]").click();
            $x("//label[contains(.,'Classified')]").click();
            $x("//label[contains(.,'Excel File.doc')]").click();
            takeScreenshot();
        });

        step("Проверяем выбранные значения", () -> {
            $("#result").shouldHave(
                    text("angular"),
                    text("classified"),
                    text("excelFile")
            );
            takeScreenshot();
        });
    }

    @Test
    @Story("RadioButton Selection")
    @Tag("smoke")
    @Description("Test verifies that only one RadioButton can be selected")
    @DisplayName("Проверка радиокнопок")
    void radioButtonsShouldAllowSingleSelection() {
        step("Открываем страницу Radio Button", () -> {
            open("/radio-button");
            takeScreenshot();
        });

        step("Выбираем вариант 'Yes'", () -> {
            $x("//label[contains(.,'Yes')]").click();
            $(".text-success").shouldHave(text("Yes"));
            takeScreenshot();
        });

        step("Выбираем вариант 'Impressive'", () -> {
            $x("//label[contains(.,'Impressive')]").click();
            $(".text-success").shouldHave(text("Impressive"));
            takeScreenshot();
        });

        step("Проверяем, что 'No' disabled", () -> {
            $("#noRadio").shouldBe(disabled);
            takeScreenshot();
        });
    }

    @Test
    @Story("WebTables CRUD")
    @Tag("smoke")
    @Description("Test verifies adding and deleting records in WebTables")
    @DisplayName("Проверка работы с таблицей")
    void webTablesShouldAllowAddingAndDeletingRecords() {
        step("Открываем страницу Web Tables", () -> {
            open("/webtables");
            takeScreenshot();
        });

        step("Добавляем новую запись", () -> {
            $("#addNewRecordButton").click();
            $("#firstName").setValue("Anna");
            $("#lastName").setValue("Kowalski");
            $("#userEmail").setValue("anna@example.com");
            $("#age").setValue("30");
            $("#salary").setValue("75000");
            $("#department").setValue("Engineering");
            $("#submit").click();
            takeScreenshot();
        });

        step("Проверяем добавление записи", () -> {
            $x("//div[text()='Anna']").shouldBe(visible);
            $x("//div[text()='Kowalski']").shouldBe(visible);
            takeScreenshot();
        });

        step("Удаляем запись", () -> {
            $x("//div[text()='Anna']/ancestor::div[contains(@class, 'rt-tr-group')]//span[@title='Delete']").click();
            $x("//div[text()='Anna']").shouldNotBe(visible);
            takeScreenshot();
        });
    }

    @Test
    @Story("Date Picker")
    @Tag("smoke")
    @Description("Test verifies date selection in date picker")
    @DisplayName("Проверка выбора даты")
    void datePickerShouldSelectCorrectDate() {
        step("Открываем страницу Date Picker", () -> {
            open("/date-picker");
            takeScreenshot();
        });

        step("Выбираем дату 15 декабря 1990 года", () -> {
            $("#datePickerMonthYearInput").click();
            $(".react-datepicker__month-select").selectOption("December");
            $(".react-datepicker__year-select").selectOption("1990");
            $(".react-datepicker__day--015:not(.react-datepicker__day--outside-month)").click();
            takeScreenshot();
        });

        step("Проверяем выбранную дату", () -> {
            $("#datePickerMonthYearInput").shouldHave(value("12/15/1990"));
            takeScreenshot();
        });
    }

    @Test
    @Story("Modal Dialogs")
    @Tag("regress")
    @Description("Test verifies opening and closing modal dialogs")
    @DisplayName("Проверка модальных окон")
    void modalDialogsShouldOpenAndClose() {
        step("Открываем страницу Modal Dialogs", () -> {
            open("/modal-dialogs");
            takeScreenshot();
        });

        step("Открываем и закрываем малое модальное окно", () -> {
            $("#showSmallModal").click();
            $("#example-modal-sizes-title-sm").shouldHave(text("Small Modal"));
            $(".modal-body").shouldHave(text("This is a small modal. It has very less content"));
            takeScreenshot();
            $("#closeSmallModal").click();
        });

        step("Открываем и закрываем большое модальное окно", () -> {
            $("#showLargeModal").click();
            $("#example-modal-sizes-title-lg").shouldHave(text("Large Modal"));
            $(".modal-body").shouldHave(text("Lorem Ipsum is simply dummy text"));
            takeScreenshot();
            $("#closeLargeModal").click();
        });
    }

    @Test
    @Story("Buttons Interactions")
    @Tag("regress")
    @Description("Test verifies different button interactions")
    @DisplayName("Проверка взаимодействия с кнопками")
    void buttonsShouldRespondToDifferentInteractions() {
        step("Открываем страницу Buttons", () -> {
            open("/buttons");
            takeScreenshot();
        });

        step("Двойной клик на кнопке", () -> {
            $("#doubleClickBtn").doubleClick();
            $("#doubleClickMessage").shouldBe(visible).shouldHave(text("You have done a double click"));
            takeScreenshot();
        });

        step("Правый клик на кнопке", () -> {
            $("#rightClickBtn").contextClick();
            $("#rightClickMessage").shouldBe(visible).shouldHave(text("You have done a right click"));
            takeScreenshot();
        });

        step("Обычный клик на кнопке", () -> {
            $x("//button[text()='Click Me']").click();
            $("#dynamicClickMessage").shouldBe(visible).shouldHave(text("You have done a dynamic click"));
            takeScreenshot();
        });
    }

    @Test
    @Story("Browser Windows")
    @Tag("regress")
    @Description("Test verifies opening new browser windows/tabs")
    @DisplayName("Проверка работы с новыми окнами")
    void shouldHandleNewBrowserWindows() {
        step("Открываем страницу Browser Windows", () -> {
            open("/browser-windows");
            takeScreenshot();
        });

        step("Открываем и закрываем новую вкладку", () -> {
            String originalWindow = webdriver().driver().getWebDriver().getWindowHandle();
            $("#tabButton").click();
            switchTo().window(1);
            $("body").shouldHave(text("This is a sample page"));
            takeScreenshot();
            closeWindow();
            switchTo().window(originalWindow);
        });

        step("Открываем и закрываем новое окно", () -> {
            String originalWindow = webdriver().driver().getWebDriver().getWindowHandle();
            $("#windowButton").click();
            switchTo().window(1);
            $("body").shouldHave(text("This is a sample page"));
            takeScreenshot();
            closeWindow();
            switchTo().window(originalWindow);
        });
    }



    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
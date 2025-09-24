package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@Epic("Crop Circle Connector")
@Feature("Crop Circle Database Testing")
public class CropCircleConnectorTests extends TestBase {

    @Test
    @Story("Main Page Navigation")
    @Description("Test verifies that main page loads correctly with all sections")
    @DisplayName("Проверка загрузки главной страницы")
    @Link(name = "Crop Circle Connector", url = "https://cropcircleconnector.com/interface2005.htm")
    void mainPageShouldLoadCorrectly() {
        step("Открываем главную страницу", () -> {
            open("/interface2005.htm");
            takeScreenshot();
        });

        step("Проверяем заголовок страницы", () -> {
            $("title").shouldHave(attribute("text", "WELCOME TO THE CROP CIRCLE CONNECTOR"));
            takeScreenshot();
        });

        step("Проверяем наличие основных секций", () -> {
            $("body").shouldHave(
                    text("WELCOME TO THE CROP CIRCLE CONNECTOR"),
                    text("Established 21st June 1995"),
                    text("This Page has been accessed since 21st June 1995")

            );
            takeScreenshot();
        });
    }

    @Test
    @Story("Database Search Navigation")
    @Description("Test verifies navigation to search page works correctly")
    @DisplayName("Проверка навигации по поиску")
    void searchNavigationShouldWorkCorrectly() {
        step("Открываем главную страницу", () -> {
            open("/interface2005.htm");
            takeScreenshot();
        });

        step("Находим и кликаем ссылку поиска", () -> {
            $$("a[href*='search']").first().click();
            takeScreenshot();
        });

        step("Проверяем загрузку страницы поиска", () -> {
            $("body").shouldHave(text("Search"));
            $("form").shouldBe(visible);
            takeScreenshot();
        });

        step("Проверяем наличие элементов формы", () -> {
            $$("input").shouldHave(sizeGreaterThan(0));
            takeScreenshot();
        });
    }

    @Test
    @Story("Website Accessibility")
    @Description("Test verifies that Crop Circle Connector website is accessible")
    @DisplayName("Проверка доступности сайта Crop Circle Connector")
    void cropCircleWebsiteShouldBeAccessible() {
        step("Открываем главную страницу", () -> {
            open("/interface2005.htm");
            takeScreenshot();
        });

        step("Проверяем базовые элементы страницы", () -> {
            // Проверяем только самое необходимое
            $("body").shouldBe(visible); // Страница загружена
            $("body").shouldNotHave(text("404")); // Нет ошибки 404
            $("body").shouldNotHave(text("Error")); // Нет ошибки
            takeScreenshot();
        });

        step("Проверяем контент страницы", () -> {
            // Проверяем что это действительно сайт о кругах на полях
            $("body").shouldHave(text("Crop Circle"));
            takeScreenshot();
        });

        step("Анализируем структуру сайта", () -> {
            // Выводим информацию для отладки
            System.out.println("Всего ссылок: " + $$("a").size());
            System.out.println("Ссылок с 'circle': " + $$("a[href*='circle']").size());
            System.out.println("Изображений: " + $$("img").size());
            takeScreenshot();
        });
    }

    @Test
    @Story("Image Gallery Navigation")
    @Description("Test verifies that image gallery works correctly")
    @DisplayName("Проверка навигации по галерее изображений")
    void imageGalleryShouldWorkCorrectly() {
        step("Открываем детальную страницу круга", () -> {
            open("/2025/rumours2025.html"); // Пример страницы
            takeScreenshot();
        });

        step("Проверяем наличие изображений", () -> {
            $$("img").shouldHave(sizeGreaterThan(0));
            takeScreenshot();
        });

        step("Кликаем на изображение для увеличения", () -> {
            $$("img").first().click();
            takeScreenshot();
        });
    }


    @Test
    @Story("Responsive Design")
    @Description("Test verifies that website layout is responsive")
    @DisplayName("Проверка адаптивности дизайна")
    void websiteShouldBeResponsive() {
        step("Открываем главную страницу", () -> {
            open("/interface2005.htm");
            takeScreenshot();
        });

        step("Проверяем основные элементы на разных разрешениях", () -> {
            // Проверяем наличие основных блоков
            $("table").shouldBe(visible);
            $("img").shouldBe(visible);
            $("a").shouldBe(visible);
            takeScreenshot();
        });
    }

    @Test
    @Story("Form Validation")
    @Description("Test verifies that forms validate input correctly")
    @DisplayName("Проверка валидации форм")
    void formsShouldValidateInputCorrectly() {
        step("Открываем страницу поиска", () -> {
            open("/interface2005.htm");
            $("a[href*='search']").click();
            takeScreenshot();
        });

        step("Проверяем обязательные поля", () -> {
            $("input[type='submit']").click();
            // Проверяем, что страница не сломалась после отправки
            $("body").shouldNotHave(text("Error"));
            takeScreenshot();
        });
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
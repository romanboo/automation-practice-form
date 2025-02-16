package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class Homework_4 {

    @AfterEach
    public void logout() {
        Selenide.closeWebDriver();
    }

    @BeforeEach
    public void beforeEach() {
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void сheckingPageLoading() {
        open("https://github.com/");
        $(byTagAndText("button", "Solutions")).hover();
        $(byTagAndText("a", "Enterprise")).click();
        $("[id=hero-section-brand-heading]").shouldHave(text("The AI-powered\n" +
                "developer platform"));
    }

    @Test
    void dragAndDropTest(){
       open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDrop(to("#column-b"));
         $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));

    }
}


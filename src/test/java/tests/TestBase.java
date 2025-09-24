package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "C";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://cropcircleconnector.com";
    }


    @BeforeEach
    public void beforeEach(){
        Configuration.pageLoadStrategy ="eager";
    }

}
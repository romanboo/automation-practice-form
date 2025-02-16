package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultComponent {
    private final SelenideElement result = $(".table-responsive");
    public void result(String key, String value){
        result.$(byText(key)).parent()
                .shouldHave(text(value));
    }

}
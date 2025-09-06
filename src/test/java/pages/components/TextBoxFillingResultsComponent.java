package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxFillingResultsComponent {
    SelenideElement output = $("#output");

    public TextBoxFillingResultsComponent checkOutputResult(String key, String value) {
        output.shouldHave(text(key), text(value));
        return this;
    }
}
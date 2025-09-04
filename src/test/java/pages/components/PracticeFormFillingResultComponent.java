package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormFillingResultComponent {
    SelenideElement tableInfo = $(".table-responsive");

    public PracticeFormFillingResultComponent checkingTableInfo(String key, String value) {
        tableInfo.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}

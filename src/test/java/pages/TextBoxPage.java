package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DeleteBannersComponent;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class TextBoxPage {

    public TextBoxPage openPage() {
        DeleteBannersComponent deleteBannersComponent = new DeleteBannersComponent();
        open("/text-box");
        mainHeaderText.shouldHave(text("Text Box"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    SelenideElement mainHeaderText = $(".text-center"),
            fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressTextInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $(".btn-primary");


    public TextBoxPage setUserName(String value) {
        fullNameInput.setValue(value);
        return this;
    }

    public TextBoxPage setUserEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressTextInput.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public void clickButton() {
        submitButton.click();
    }


}
package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import utils.JSUtils;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectSelect = $("#subjectsInput"),
            hobbiesSportSelect = $$("#hobbiesWrapper label").filterBy(text("Sports")).first(),
            hobbiesReadingSelect = $$("#hobbiesWrapper label").filterBy(text("Reading")).first(),
            hobbiesMusicSelect = $$("#hobbiesWrapper label").filterBy(text("Music")).first(),
            currentAddressInput = $("#currentAddress"),
            submitButton = $("#submit"),
            uploadPicture = $("#uploadPicture");




    CalendarComponent calendarComponent = new CalendarComponent();
    JSUtils jsUtils = new JSUtils();

    public void deleteBanners(){
        jsUtils.deleteBanners();
    }

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        deleteBanners();

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String subject) {
        subjectSelect.setValue(subject).pressEnter();
        return this;
    }

    public  RegistrationPage setHobbieSports(){
        hobbiesSportSelect.click();
        return this;
    }

    public  RegistrationPage setHobbieReading(){
        hobbiesReadingSelect.click();
        return this;
    }

    public  RegistrationPage setHobbieMusic(){
        hobbiesMusicSelect.click();
        return this;
    }

    public RegistrationPage setCurrentAddress(String address){
        currentAddressInput.setValue(address);
        return this;
    }

    public  RegistrationPage uploadImage(String fileName){
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city){
        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text(state)).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text(city)).click();
        return this;
    }

    public RegistrationPage submitButtonClick(){
        submitButton.click();
        return this;
    }

}
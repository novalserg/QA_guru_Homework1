package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SetValueOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    @Test
    void successfulSearchTest() {

        open("/automation-practice-form");

        $("[id=firstName]").setValue("Alexandra"); //заполняем имя
        $("[id=lastName]").setValue("Zabnenkova"); //заполняем фамилию
        $("[id=userEmail]").setValue("novalserg@soap.ru"); //заполняем почту
        $$("#genterWrapper label").filterBy(text("Female")).first().click(); //выбираем женский пол
        $("#userNumber").setValue("9031234567"); //заполняем телефон

        LocalDate birthday = LocalDate.parse("2000-12-31");
        $("#dateOfBirthInput").setValue(birthday.toString());

        $("#subjectsInput").setValue("Phy");
        $$(".subjects-auto-complete__option").findBy(text("Physics")).click();

        $$("#hobbiesWrapper label").filterBy(text("Sports")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Reading")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Music")).first().click();

        File pic = new File("src/test/resources/pic.png");
        $("#uploadPicture").uploadFile(pic);

        $("#currentAddress").setValue("Moscow, NY, UK");

        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Rajasthan")).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Jaipur")).click();

        $("[id=submit]").click();


    }
}

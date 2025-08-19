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
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {

    @BeforeAll
    static void setEnv() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterAll
    static void closeWebDriver() {
        closeWebDriver();
    }

    @Test
    void successfulSearchTest() {

        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Alexandra"); //заполняем имя
        $("#lastName").setValue("Zabnenkova"); //заполняем фамилию
        $("#userEmail").setValue("novalserg@soap.ru"); //заполняем почту
        $$("#genterWrapper label").filterBy(text("Female")).first().click(); //выбираем женский пол
        $("#userNumber").setValue("9031234567"); //заполняем телефон

        LocalDate birthday = LocalDate.parse("2000-12-31");
        $("#dateOfBirthInput").setValue(birthday.toString());

        $("#subjectsInput").setValue("Phy");
        $$(".subjects-auto-complete__option").findBy(text("Physics")).click();

        $$("#hobbiesWrapper label").filterBy(text("Sports")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Reading")).first().click();
        $$("#hobbiesWrapper label").filterBy(text("Music")).first().click();

      /*  File pic = new File("src/test/resources/pic.png");
        $("#uploadPicture").uploadFile(pic);*/

        $("#uploadPicture").uploadFromClasspath("pic.png"); //более простой вариант загрузки изображения

        $("#currentAddress").setValue("Moscow, NY, UK");

        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Rajasthan")).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Jaipur")).click();

        $("#submit").click();

        //проверка таблицы
        $(".table-responsive")
                .$(byText("Student Name")).parent().shouldHave(text("Alexandra Zabnenkova"));
        $(".table-responsive")
                .$(byText("Student Email")).parent().shouldHave(text("novalserg@soap.ru"));
        $(".table-responsive")
                .$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive")
                .$(byText("Mobile")).parent().shouldHave(text("9031234567"));
        $(".table-responsive")
                .$(byText("Date of Birth")).parent().shouldHave(text("31 December,2000"));
        $(".table-responsive")
                .$(byText("Subjects")).parent().shouldHave(text("Physics"));
        $(".table-responsive")
                .$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive")
                .$(byText("Picture")).parent().shouldHave(text("pic.png"));
        $(".table-responsive")
                .$(byText("Address")).parent().shouldHave(text("Moscow, NY, UK"));
        $(".table-responsive")
                .$(byText("State and City")).parent().shouldHave(text("Rajasthan Jaipur"));

    }
}

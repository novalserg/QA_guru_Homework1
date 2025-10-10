package tests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.PracticeFormFillingResultComponent;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPageObjTest {

    @BeforeAll
    @Tag("Main test")
    static void setEnv() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterAll
    @Tag("Main test")
    static void closeWB() {
        closeWebDriver();
    }

    @Test
    @Tag("Main test")
    void successfulSearchTest() {

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage
                .setFirstName("Alexandra")
                .setLastName("Zabnenkova")
                .setEmail("novalserg@soap.ru")
                .setGender("Female")
                .setUserNumber("9031234567")
                .setDateOfBirth("23", "August", "1955")
                .setSubjects("Phy")
                .setHobbieMusic()
                .setHobbieReading()
                .setHobbieSports()
                .uploadImage("pic.png")
                .setCurrentAddress("Moscow, NY, UK")
                .setStateAndCity("Rajasthan", "Jaipur")
                .submitButtonClick();


        //блок проверки таблицы заполненных данных

        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        practiceFormFillingResultComponent
                .checkingTableInfo("Student Name", "Alexandra Zabnenkova")
                .checkingTableInfo("Student Email", "novalserg@soap.ru")
                .checkingTableInfo("Gender", "Female")
                .checkingTableInfo("Mobile", "9031234567")
                .checkingTableInfo("Date of Birth", "23 August,1955")
                .checkingTableInfo("Subjects", "Physics")
                .checkingTableInfo("Hobbies", "Music, Reading, Sports")
                .checkingTableInfo("Address", "Moscow, NY, UK")
                .checkingTableInfo("State and City", "Rajasthan Jaipur");

    }

    @Test
    @Tag("Main test")
    void requiredFieldsFillTest(){

        RegistrationPage registrationPage = new RegistrationPage();
        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        registrationPage.openPage();

        registrationPage
                .setFirstName("Alexandra")
                .setLastName("Zabnenkova")
                .setEmail("novalserg@soap.ru")
                .setGender("Female")
                .setUserNumber("9031234567")
                .submitButtonClick();
        practiceFormFillingResultComponent.checkingTableInfo("Student Name", "Alexandra Zabnenkova")
                .checkingTableInfo("Student Email", "novalserg@soap.ru")
                .checkingTableInfo("Gender", "Female")
                .checkingTableInfo("Mobile", "9031234567");
    }

    @Test
    @Tag("Main test")
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

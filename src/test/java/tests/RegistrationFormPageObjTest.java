package tests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.PracticeFormFillingResultComponent;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPageObjTest {

    @BeforeAll
    static void setEnv() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterAll
    static void closeWB() {
        closeWebDriver();
    }

    @Test
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
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

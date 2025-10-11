package tests;

import attach.Attach;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.components.PracticeFormFillingResultComponent;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormPageObjTest {

    @BeforeAll
    static void setEnv() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterAll
    static void closeWB() {
        closeWebDriver();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @DisplayName("положительный тест с пэйдж обджект")
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
    @DisplayName("Заполнение обязательных полей")
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
    @DisplayName("Все поля оставляем пустыми")
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

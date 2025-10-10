package tests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import pages.components.PracticeFormFillingResultComponent;

import static com.codeborne.selenide.Selenide.*;
import static tests.RegistrationFormTest.closeWebDriver;

public class RegistrationWithFakerTest {

    TestDataWithFaker testDataWithFaker = new TestDataWithFaker();

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
    @DisplayName("")
    void successfulSearchTest() {

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage();

        registrationPage
                .setFirstName(testDataWithFaker.getFirstName())
                .setLastName(testDataWithFaker.getLastName())
                .setEmail(testDataWithFaker.getEmail())
                .setGender(testDataWithFaker.getUserGender())
                .setUserNumber(testDataWithFaker.getUserNumber())
                .setDateOfBirth(testDataWithFaker.getDayOfBirth(), testDataWithFaker.getMonthOfBirth(), testDataWithFaker.getYearOfBirth())
                .setSubjects(testDataWithFaker.getSubjectSelect())
                .setHobbieMusic()
                .setHobbieReading()
                .setHobbieSports()
                .uploadImage(testDataWithFaker.getFileName())
                .setCurrentAddress(testDataWithFaker.getCurrentAddress())
                .setStateAndCity(testDataWithFaker.getState(), testDataWithFaker.getCity())
                .submitButtonClick();


        //блок проверки таблицы заполненных данных

        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        practiceFormFillingResultComponent
                .checkingTableInfo("Student Name", testDataWithFaker.getFirstName() + " " + testDataWithFaker.getLastName())
                .checkingTableInfo("Student Email", testDataWithFaker.getEmail())
                .checkingTableInfo("Gender", testDataWithFaker.getUserGender())
                .checkingTableInfo("Mobile", testDataWithFaker.getUserNumber())
                .checkingTableInfo("Date of Birth",  testDataWithFaker.getDayOfBirth()+ " " + testDataWithFaker.getMonthOfBirth() +","+testDataWithFaker.getYearOfBirth())
                .checkingTableInfo("Subjects", testDataWithFaker.getSubjectSelect())
                .checkingTableInfo("Hobbies", "Music, Reading, Sports")
                .checkingTableInfo("Address", testDataWithFaker.getCurrentAddress())
                .checkingTableInfo("State and City", testDataWithFaker.getState() + " " + testDataWithFaker.getCity());

    }

    @Test
    @Tag("Main test")
    void requiredFieldsFillTest(){

        RegistrationPage registrationPage = new RegistrationPage();
        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        registrationPage.openPage();

        registrationPage
                .setFirstName(testDataWithFaker.getFirstName())
                .setLastName(testDataWithFaker.getLastName())
                .setEmail(testDataWithFaker.getEmail())
                .setGender(testDataWithFaker.getUserGender())
                .setUserNumber(testDataWithFaker.getUserNumber())
                .submitButtonClick();

        practiceFormFillingResultComponent.checkingTableInfo("Student Name", testDataWithFaker.getFirstName() + " " + testDataWithFaker.getLastName())
                .checkingTableInfo("Student Email", testDataWithFaker.getEmail())
                .checkingTableInfo("Gender", testDataWithFaker.getUserGender())
                .checkingTableInfo("Mobile", testDataWithFaker.getUserNumber());
    }

    @Test
    @Tag("Main test")
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

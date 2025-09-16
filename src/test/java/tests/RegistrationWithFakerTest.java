package tests;

import com.codeborne.selenide.Configuration;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.PracticeFormFillingResultComponent;

import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static tests.RegistrationFormTest.closeWebDriver;

public class RegistrationWithFakerTest {

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
        TestDataWithFaker testDataWithFaker = new TestDataWithFaker();
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
    void requiredFieldsFillTest(){

        RegistrationPage registrationPage = new RegistrationPage();
        TestDataWithFaker testDataWithFaker = new TestDataWithFaker();
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
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

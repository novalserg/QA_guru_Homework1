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

        Faker faker = new Faker(new Locale("en-GB"));
        RegistrationPage registrationPage = new RegistrationPage();

        //test data

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String userGender = faker.options().option();
        String userNumber = faker.number().digits(10);
        String dayOfBirth = String.valueOf(faker.number().numberBetween(1, 30));
        String monthOfBirth = faker.options().option();
        String yearOfBirth = String.valueOf(faker.number().numberBetween(1930, 2010));
        String currentAddress = faker.address().fullAddress();
        String subjectSelect = faker.options().option();
        String fileName = "pic.png";
        String state = faker.options().option();
        String city = faker.options().option();


        registrationPage.openPage();

        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjectSelect)
                .setHobbieMusic()
                .setHobbieReading()
                .setHobbieSports()
                .uploadImage(fileName)
                .setCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitButtonClick();


        //блок проверки таблицы заполненных данных

        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        practiceFormFillingResultComponent
                .checkingTableInfo("Student Name", firstName + " " + lastName)
                .checkingTableInfo("Student Email", email)
                .checkingTableInfo("Gender", userGender)
                .checkingTableInfo("Mobile", userNumber)
                .checkingTableInfo("Date of Birth", dayOfBirth + " " + monthOfBirth +","+yearOfBirth)
                .checkingTableInfo("Subjects", subjectSelect)
                .checkingTableInfo("Hobbies", "Music, Reading, Sports")
                .checkingTableInfo("Address", currentAddress)
                .checkingTableInfo("State and City", state + " " + city);

    }

    @Test
    void requiredFieldsFillTest(){

        RegistrationPage registrationPage = new RegistrationPage();
        PracticeFormFillingResultComponent practiceFormFillingResultComponent = new PracticeFormFillingResultComponent();

        registrationPage.openPage();
        Faker faker = new Faker(new Locale("en-GB"));

        //test data

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String userGender = faker.options().option();
        String userNumber = faker.number().digits(10);
        String dayOfBirth = String.valueOf(faker.number().numberBetween(1, 30));
        String monthOfBirth = faker.options().option();
        String yearOfBirth = String.valueOf(faker.number().numberBetween(1930, 2010));
        String currentAddress = faker.address().fullAddress();
        String subjectSelect = faker.options().option();
        String fileName = "pic.png";
        String state = faker.options().option();
        String city = faker.options().option();

        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .submitButtonClick();

        practiceFormFillingResultComponent.checkingTableInfo("Student Name", firstName + " " + lastName)
                .checkingTableInfo("Student Email", email)
                .checkingTableInfo("Gender", userGender)
                .checkingTableInfo("Mobile", userNumber);
    }

    @Test
    void emptyFormTest(){

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage();

        registrationPage.submitButtonClick();


    }
}

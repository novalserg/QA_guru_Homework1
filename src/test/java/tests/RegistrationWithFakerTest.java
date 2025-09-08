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

        String[] months = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };

        Random random = new Random();
        int index = random.nextInt(months.length - 1);
        String randomMonth = months[index];


        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String userGender = "Female";
        String userNumber = faker.number().digits(10);
        String dayOfBirth = String.valueOf(faker.number().numberBetween(1, 30));
        String yearOfBirth = String.valueOf(faker.number().numberBetween(1930, 2010));
        String currentAddress = faker.address().fullAddress();
        String subjectPhysicsSelect = "Phy";
        String fileName = "pic.png";
        String state = "Rajastan";
        String city = "Jaipur";


        registrationPage.openPage();

        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setDateOfBirth(dayOfBirth, randomMonth, yearOfBirth)
                .setSubjects(subjectPhysicsSelect)
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

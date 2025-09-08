package tests;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class TestData {
//класс создан для проверки сгенерированных данных
    public static void main(String[] args) {
    Faker faker = new Faker(new Locale("en-GB"));

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

        System.out.println(firstName + lastName + email + userGender + userNumber + dayOfBirth + yearOfBirth + currentAddress + subjectPhysicsSelect + fileName + state + city);
}
}

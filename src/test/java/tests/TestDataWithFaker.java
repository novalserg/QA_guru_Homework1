package tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataWithFaker {

    private static final Faker faker = new Faker(new Locale("en"));

    private String firstName;
    private String lastName;
    private String email;
    private String userGender;
    private String userNumber;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String subjectSelect;
    private String fileName;
    private String currentAddress;
    private String state;
    private String city;


    public TestDataWithFaker() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.userGender = faker.options().option("Male", "Female", "Other");
        this.userNumber = faker.phoneNumber().subscriberNumber(10);
        this.dayOfBirth = String.valueOf(faker.number().numberBetween(1, 28));
        this.monthOfBirth = faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        this.yearOfBirth = String.valueOf(faker.number().numberBetween(1980, 2005));
        this.subjectSelect = faker.options().option("Math", "Physics", "Chemistry");
        this.fileName = "pic.png";
        this.currentAddress = faker.address().fullAddress();

        this.state = getRandomState();
        this.city = selectCity(this.state);
    }


    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }


    public static String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Meerut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaisalmer");
            default -> "Unknown";
        };
    }

    // Геттеры
    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getSubjectSelect() {
        return subjectSelect;
    }

    public String getFileName() {
        return fileName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    // main для теста
    public static void main(String[] args) {
        TestDataWithFaker testData = new TestDataWithFaker();
        System.out.println(
                testData.getFirstName() + " " +
                        testData.getLastName() + ", " +
                        testData.getEmail() + ", " +
                        testData.getUserGender() + ", " +
                        testData.getUserNumber() + ", " +
                        testData.getDayOfBirth() + " " +
                        testData.getMonthOfBirth() + " " +
                        testData.getYearOfBirth() + ", " +
                        testData.getSubjectSelect() + ", " +
                        testData.getFileName() + ", " +
                        testData.getCurrentAddress() + ", " +
                        testData.getState() + ", " +
                        testData.getCity()
        );
    }
}
package utils;
import com.github.javafaker.Faker;

public class RandomUtils {
    private final Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

    public String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getDay() {
        return String.valueOf(faker.number().numberBetween(1,28));
    }

    public String getMoth() {
        return faker.options().option("December","January","February",
                "March","April","May","June", "July", "August","September",
                "October","November");
    }

    public String getYear() {
        return String.valueOf(faker.number().numberBetween(1970,2003));
    }

    public String getSubjects() {
        return faker.options().option("Maths","Chemistry","English", "Computer Science");
    }

    public String getHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String getPicture() {
        return faker.options().option("photo_2025.jpg", "IMG_2500.JPG", "IMG_2567.JPG");
    }

    public String getCurrentAddres() {
        return faker.address().country();
    }

    public String getState() {
        return faker.options().option("NCR", "Uttar Pradesh","Haryana", "Rajasthan");
    }

    public String getCity(String value) {
        if (value.equals( "NCR")) return faker.options().option("Delhi","Gurgaon","Noida");
        if (value.equals("Uttar Pradesh")) return faker.options().option("Agra","Lucknow","Merrut");
        if (value.equals("Haryana")) return faker.options().option("Karnal","Panipat");
        return faker.options().option("Jaipur","Jaiselmer");
    }
}

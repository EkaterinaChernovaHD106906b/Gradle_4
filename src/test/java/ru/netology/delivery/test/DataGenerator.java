package ru.netology.delivery.test;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {


    protected DataGenerator() {
    }


    public static String generateDate(int days) {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(3);

    public static String newGenerateDate(int days) {
        return LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String newDate = newGenerateDate(5);

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String city = faker.address().city();
        return city;

    }

    String city = generateCity("ru");


    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale("ru"));

        String name = faker.name().fullName();
        return name;
    }

    String name = generateName("ru");

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    String phone = generatePhone("ru");

    public static class Order {
        private Order() {
        }

        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new UserInfo(generateName("ru"), generateCity("ru"), generatePhone("ru"));


        }
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}




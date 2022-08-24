package ru.netology.delivery.test;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {


    private DataGenerator() {
    }


    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String city = faker.address().city();
        return city;

    }


    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale("ru"));

        String name = faker.name().fullName();
        return name;
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }


    public static class Order {
        private Order() {
        }

        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            UserInfo user = new UserInfo(generateName("ru"), generateCity("ru"), generatePhone("ru"));
            return user;


        }
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}




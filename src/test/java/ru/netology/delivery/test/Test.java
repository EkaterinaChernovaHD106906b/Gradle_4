package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class Test {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(3);

    String newDate = generateDate(5);


    @org.junit.jupiter.api.Test
    public void myTest() {
        //DataGenerator generator = new DataGenerator();


        Configuration.holdBrowserOpen = true;
        $("[data-test-id=\"city\"] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id='phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id= 'agreement']").click();
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(newDate);
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='replan-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//*[text()='Перепланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + newDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);


    }


}

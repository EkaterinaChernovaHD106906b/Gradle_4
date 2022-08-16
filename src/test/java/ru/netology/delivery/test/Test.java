package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class Test {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @org.junit.jupiter.api.Test
    public void myTest() {
        DataGenerator generator = new DataGenerator();

        Configuration.holdBrowserOpen = true;
        $("[data-test-id=\"city\"] input").setValue(generator.city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(generator.planningDate);
        $("[data-test-id='name'] input").setValue(generator.name);
        $("[data-test-id='phone'] input").setValue(generator.phone);
        $("[data-test-id= 'agreement']").click();
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + generator.planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(generator.newDate);
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='replan-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//*[text()='Перепланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + generator.newDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);


    }


}

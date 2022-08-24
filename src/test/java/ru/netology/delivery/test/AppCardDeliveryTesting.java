package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTesting {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    public void myTest() {


        Configuration.holdBrowserOpen = true;
        $("[data-test-id=\"city\"] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(DataGenerator.generateDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id='phone'] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id= 'agreement']").click();
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(3)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(DataGenerator.generateDate(5));
        $x("//*[text()='Запланировать']").click();
        $("[data-test-id='replan-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//*[text()='Перепланировать']").click();
        $("[data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(5)), Duration.ofSeconds(15)).shouldBe(Condition.visible);


    }


}

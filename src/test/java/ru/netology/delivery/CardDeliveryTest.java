package ru.netology.delivery;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldTest() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Самара");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void wrongCity() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Суздаль");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void englishCity() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Moscow");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void cityWithNumber() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Москва 2");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void withoutCity() {


        open("http://localhost:9999");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }

    @Test
    void lastDayMeeting() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Краснодар");
        String planningDate = generateDate(-1);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void todayMeeting() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Пенза");
        String planningDate = generateDate(0);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void tomorrowMeeting() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Биробиджан");
        String planningDate = generateDate(1);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void twoDaysMeeting() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Тамбов");
        String planningDate = generateDate(2);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void threeDaysMeeting() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Кемерово");
        String planningDate = generateDate(3);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void nameWithNumber() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Кострома");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Инесса 3");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();
        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.\"]");
    }

    @Test
    void englishName() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Салехард");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Inessa Vladimirovna");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.\"]");
    }

    @Test
    void doubleName() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Санкт-Петербург");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна-Мария Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void withoutName() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }

    @Test
    void withoutPhone() {


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }

    @Test
    void phoneNumberWithEight() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("89340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void phoneNumberMore() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+793402481975");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void phoneNumberLess() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+7934024819");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void withoutAgreement() {

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Киров");
        String planningDate = generateDate(5);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"button__content\"]").click();

        $("[data-test-id=\"agreement\"].input_invalid").should(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


}

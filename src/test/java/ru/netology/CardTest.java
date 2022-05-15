package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeEach // открытие страницы
    public void openPage() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnValidValue() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("Устинова Агнесса");
        $("[data-test-id=phone] input").setValue("+79035559988");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]")
                .shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldReturnValidValue2() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("Кара-Устинова Агнесса");
        $("[data-test-id=phone] input").setValue("+79035559988");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]")
                .shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void emptyNameField() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79035559988");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void nameFieldValidation() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79035559988");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void emptyPhoneField() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("Кара-Устинова Агнесса");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void phoneFieldValidation() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("Кара-Устинова Агнесса");
        $("[data-test-id=phone] input").setValue("+7903-555-99-88");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void emptyForm() {
        $(".form-field_theme_alfa_on_white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
}

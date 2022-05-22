package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    @DisplayName("тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)")
    void locale() {
        Country country = Country.USA;
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);
        String expected = "Welcome";
        String expected2 = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(expected2, actual);
    }
}
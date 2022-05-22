package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    @DisplayName("Тесты для проверки определения локации по ip (класс GeoServiceImpl)")
    void byIp() {
        String ip = "96.44.183.149";
        GeoService geoService = new GeoServiceImpl();
        Location actualTest = geoService.byIp(ip);
        String actual = actualTest.getStreet();
        Location expectedTest = new Location("New York", Country.USA, " 10th Avenue", 32);
        String expected = expectedTest.getStreet();

        Assertions.assertEquals(expected, actual);

        Location expectedTest2 = new Location("New York", Country.USA, null, 0);
        String expected2 = expectedTest2.getStreet();

        Assertions.assertNotEquals(expected2, actual);
    }
}
package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    @DisplayName("Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов")
    void sendRu() {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when((localizationService.locale(Country.RUSSIA)))
                .thenReturn("Добро пожаловать");

        String expected = "Добро пожаловать";

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String actual = messageSenderImpl.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов")
    void sendUsa() {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96")))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when((localizationService.locale(Country.USA)))
                .thenReturn("Welcome");

        String expected = "Welcome";

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String actual = messageSenderImpl.send(headers);

        Assertions.assertEquals(expected, actual);
    }
}
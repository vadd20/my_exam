package ru.croc.exam.domain;

import java.time.ZoneOffset;

/**
 * Временная зоны города.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public record CityTimezone(String city, ZoneOffset zoneOffset) {

    /**
     * Конструктор.
     *
     * @param city   название города
     * @param zoneId идентификатор часового пояса
     */
    public CityTimezone(String city, String zoneId) {
        this(city, ZoneOffset.of(zoneId.replace("UTC", "")));
    }
}

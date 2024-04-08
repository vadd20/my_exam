package ru.croc.exam.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Мероприятие.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class Event {

    private String name;
    private OffsetDateTime startDateTime;
    private long duration;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Конструктор.
     *
     * @param name         название
     * @param startDate    дата начала
     * @param startTime    время начала
     * @param duration     длительность в минутах
     * @param cityTimezone временная зона
     */
    public Event(String name, String startDate, String startTime, String duration, CityTimezone cityTimezone) {
        this.name = name;
        LocalDate date = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(startTime);
        this.startDateTime = OffsetDateTime.of(date, time, cityTimezone.zoneOffset());
        this.duration = Long.parseLong(duration);
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getStartDateTime() {
        return startDateTime;
    }
}

package ru.croc.exam.domain;

import java.time.LocalTime;
import java.time.OffsetTime;

/**
 * Пользователь.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class User {

    private String name;
    private OffsetTime workingDayStartTime;
    private OffsetTime workingDayEndTime;

    /**
     * Конструктор.
     *
     * @param name                имя
     * @param cityTimezone        временная зона
     * @param workingDayStartTime время начала рабочего дня
     * @param workingDayEndTime   время конца рабочего дня
     */
    public User(String name, CityTimezone cityTimezone, String workingDayStartTime, String workingDayEndTime) {
        this.name = name;
        LocalTime startTime = LocalTime.parse(workingDayStartTime);
        LocalTime endTime = LocalTime.parse(workingDayEndTime);
        this.workingDayStartTime = OffsetTime.of(startTime, cityTimezone.zoneOffset());
        this.workingDayEndTime = OffsetTime.of(endTime, cityTimezone.zoneOffset());
    }

    public String getName() {
        return name;
    }

    public OffsetTime getWorkingDayStartTime() {
        return workingDayStartTime;
    }

    public OffsetTime getWorkingDayEndTime() {
        return workingDayEndTime;
    }
}

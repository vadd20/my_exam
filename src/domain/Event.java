package domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class Event {

    private String name;
    private ZonedDateTime startDateTime;
    private long duration;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z");
    public Event(String name, String startDate, String startTime, String duration, CityTimezone cityTimezone) {
        this.name = name;
        String startDateTimeString = new StringBuilder()
                .append(startDate).append(" ")
                .append(startTime).append(" ")
                .append(cityTimezone.timezone()).toString();
        this.startDateTime = ZonedDateTime.parse(startDateTimeString, formatter);
        this.duration = Long.parseLong(duration);
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", startDateTime=" + startDateTime +
                ", duration=" + duration +
                '}';
    }
}

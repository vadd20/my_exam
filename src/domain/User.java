package domain;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class User {

    private String name;
    private OffsetTime workingDayStartTime;
    private OffsetTime workingDayEndTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm z");

    public User(String name, CityTimezone cityTimezone, String workingDayStartTime, String workingDayEndTime) {
        this.name = name;
        String workingDayStartTimeWithOffsetString = new StringBuilder()
                .append(workingDayStartTime).append(" ")
                .append(cityTimezone.timezone().replace("UTC", "")).toString();
        String workingDayEndTimeWithOffsetString = new StringBuilder()
                .append(workingDayEndTime).append(" ")
                .append(cityTimezone.timezone().replace("UTC", "")).toString();
        this.workingDayStartTime = OffsetTime.parse(workingDayStartTimeWithOffsetString, formatter);
        this.workingDayEndTime = OffsetTime.parse(workingDayEndTimeWithOffsetString, formatter);
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


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", workingDayStartTime=" + workingDayStartTime +
                ", workingDayEndTime=" + workingDayEndTime +
                '}';
    }
}

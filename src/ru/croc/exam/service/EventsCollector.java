package ru.croc.exam.service;

import ru.croc.exam.domain.Event;
import ru.croc.exam.domain.User;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Коллектор мероприятий.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class EventsCollector {

    /**
     * Получение списка возможных пользователей для каждого мероприятия.
     *
     * @param users  список пользователей
     * @param events список мероприятий
     * @return отображение мероприятия к списку возможных пользователей
     */
    public Map<String, Set<String>> collectEventToPossibleUsers(List<User> users, List<Event> events) {
        var eventToAvailableUsers = new HashMap<String, Set<String>>();

        events.forEach(event -> {
            var possibleUsers = new HashSet<String>();
            OffsetTime eventStartTimeUtcOffset = event.getStartDateTime().toOffsetTime()
                    .withOffsetSameInstant(ZoneOffset.UTC);
            users.forEach(user -> {
                if (isEventStartIncludedInUserWorkingDay(eventStartTimeUtcOffset, user)) {
                    possibleUsers.add(user.getName());
                }
            });
            eventToAvailableUsers.put(event.getName(), possibleUsers);
        });

        return eventToAvailableUsers;
    }

    private boolean isEventStartIncludedInUserWorkingDay(OffsetTime eventStartTimeUtcOffset, User user) {
        OffsetTime userWorkingDayStartTimeUtcOffset = user.getWorkingDayStartTime()
                .withOffsetSameInstant(ZoneOffset.UTC);
        OffsetTime userWorkingDayEndTimeUtcOffset = user.getWorkingDayEndTime()
                .withOffsetSameInstant(ZoneOffset.UTC);
        if (userWorkingDayStartTimeUtcOffset.isBefore(userWorkingDayEndTimeUtcOffset)) {
            return !userWorkingDayStartTimeUtcOffset.isAfter(eventStartTimeUtcOffset)
                    && !userWorkingDayEndTimeUtcOffset.isBefore(eventStartTimeUtcOffset);
        } else {
            return !userWorkingDayStartTimeUtcOffset.isAfter(eventStartTimeUtcOffset)
                    || !userWorkingDayEndTimeUtcOffset.isBefore(eventStartTimeUtcOffset);
        }
    }
}

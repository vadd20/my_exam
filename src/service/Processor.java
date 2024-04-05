package service;

import domain.Event;
import domain.User;

import java.util.*;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class Processor {

    public Map<String, Set<String>> collectEventToAvailableUsers(List<User> users, List<Event> events) {
        var eventToAvailableUsers = new HashMap<String, Set<String>>();
        events.forEach(event -> {
            users.forEach(user -> {
                var availableUsers = new HashSet<String>();

                if (user.getWorkingDayStartTime().isBefore(user.getWorkingDayEndTime())) {
                    if (!user.getWorkingDayStartTime().isAfter(
                            event.getStartDateTime().toOffsetDateTime().toOffsetTime())
                            && !user.getWorkingDayEndTime().isBefore(
                            event.getStartDateTime().toOffsetDateTime().toOffsetTime())) {
                        availableUsers.add(user.getName());
                    }
                } else {
                    if (!user.getWorkingDayStartTime().isAfter(
                            event.getStartDateTime().toOffsetDateTime().toOffsetTime())
                            || !user.getWorkingDayEndTime().isBefore(
                                    event.getStartDateTime().toOffsetDateTime().toOffsetTime())) {
                        availableUsers.add(user.getName());
                    }
                }
                eventToAvailableUsers.put(event.getName(), availableUsers);
            });
        });

        return eventToAvailableUsers;
    }
}

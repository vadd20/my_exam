package io;

import domain.CityTimezone;
import domain.Event;
import domain.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class DataLoader {

    private Map<String, CityTimezone> readCityTimeZones() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("data", "city-timezone.csv"))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(data -> new CityTimezone(
                            data[0],
                            data[1])
                    )
                    .collect(Collectors.toMap(CityTimezone::city, Function.identity()));
        }
    }

    public List<Event> readEvents() throws IOException {
        var cityToCityTimeZone = readCityTimeZones();
        try (Stream<String> lines = Files.lines(Path.of("data", "events.csv"))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(data -> new Event(
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    cityToCityTimeZone.getOrDefault(data[4], null)
                            )
                    )
                    .toList();
        }
    }

    public List<User> readUsers() throws IOException {
        var cityToCityTimeZone = readCityTimeZones();
        try (Stream<String> lines = Files.lines(Path.of("data", "users.csv"))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(data -> new User(
                                    data[0],
                                    cityToCityTimeZone.getOrDefault(data[1], null),
                                    data[2],
                                    data[3]
                            )
                    )
                    .toList();
        }
    }
}

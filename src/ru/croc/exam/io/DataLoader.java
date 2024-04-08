package ru.croc.exam.io;

import ru.croc.exam.domain.CityTimezone;
import ru.croc.exam.domain.Event;
import ru.croc.exam.domain.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Чтение данных.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class DataLoader {
    private static final String SEPARATOR = ";";

    /**
     * Чтение временных зон городов из файла.
     *
     * @param path путь к файлу
     * @return отображение названия города к городу с временной зоной
     * @throws IOException исключение ввода/вывода
     */
    public Map<String, CityTimezone> readCityTimeZones(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(SEPARATOR))
                    .map(data -> new CityTimezone(
                            data[0],
                            data[1])
                    )
                    .collect(Collectors.toMap(CityTimezone::city, Function.identity()));
        }
    }

    /**
     * Чтение мероприятий из файла.
     *
     * @param path               путь к файлу
     * @param cityToCityTimezone отображение названия города к городу с временной зоной
     * @return список мероприятий
     * @throws IOException исключение ввода/вывода
     */
    public List<Event> readEvents(String path, Map<String, CityTimezone> cityToCityTimezone) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(SEPARATOR))
                    .filter(data -> data.length == 5)
                    .map(data -> {
                        var cityTimeZone = cityToCityTimezone.getOrDefault(data[4], null);
                        if (cityTimeZone == null) {
                            return null;
                        }
                        return new Event(
                                data[0],
                                data[1],
                                data[2],
                                data[3],
                                cityTimeZone
                        );
                    })
                    .filter(Objects::nonNull)
                    .toList();
        }
    }

    /**
     * Чтение пользователей из файла.
     *
     * @param path               путь к файлу
     * @param cityToCityTimezone отображение названия города к городу с временной зоной
     * @return список пользователей
     * @throws IOException исключение ввода/вывода
     */
    public List<User> readUsers(String path, Map<String, CityTimezone> cityToCityTimezone) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(SEPARATOR))
                    .filter(data -> data.length == 4)
                    .map(data -> {
                                var cityTimeZone = cityToCityTimezone.getOrDefault(data[1], null);
                                if (cityTimeZone == null) {
                                    return null;
                                }
                                return new User(
                                        data[0],
                                        cityTimeZone,
                                        data[2],
                                        data[3]
                                );
                            }
                    )
                    .filter(Objects::nonNull)
                    .toList();
        }
    }
}

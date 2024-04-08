package ru.croc.exam;

import ru.croc.exam.io.DataLoader;
import ru.croc.exam.io.DataWriter;
import ru.croc.exam.service.EventsCollector;

import java.io.IOException;

/**
 * Основной класс приложения.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class Main {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     * @throws IOException исключение ввода/вывода
     */
    public static void main(String[] args) throws IOException {
        process();
    }

    private static void process() throws IOException {
        String cityTimezonePath = "data\\city-timezone.csv";
        String eventsPath = "data\\events.csv";
        String usersPath = "data\\users.csv";

        DataLoader dataLoader = new DataLoader();
        var cityToCityTimezone = dataLoader.readCityTimeZones(cityTimezonePath);
        var users = dataLoader.readUsers(usersPath, cityToCityTimezone);
        var events = dataLoader.readEvents(eventsPath, cityToCityTimezone);

        EventsCollector eventsCollector = new EventsCollector();
        var eventToAvailableUsers = eventsCollector.collectEventToPossibleUsers(users, events);

        String resultPath = "data\\result.txt";
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeEventToUsers(resultPath, eventToAvailableUsers);
    }
}

package ru.croc.exam.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Запись данных.
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class DataWriter {

    /**
     * Запись мероприятий к списку пользователей.
     *
     * @param path                 путь к файлу
     * @param eventToPossibleUsers отображение мероприятия к списку возможных пользователей
     * @throws IOException исключение ввода/вывода
     */
    public void writeEventToUsers(String path, Map<String, Set<String>> eventToPossibleUsers) throws IOException {
        try (var bw = new BufferedWriter(new FileWriter(path))) {
            eventToPossibleUsers.forEach((event, users) -> {
                String resultString = new StringBuilder()
                        .append(event)
                        .append(" - ")
                        .append(String.join(", ", users))
                        .toString();
                try {
                    bw.append(resultString);
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}

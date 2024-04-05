package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class DataWriter {

    public void writeData(Map<String, Set<String>> eventToAvailableUsers) throws IOException {
        try (var bw = new BufferedWriter(new FileWriter("data\\result.txt"))) {
            eventToAvailableUsers.forEach((event, users) -> {
                String usersString = String.join(", ", users);
                String resultString = new StringBuilder().append(event).append(" - ")
                        .append(usersString).toString();
                try {
                    bw.write(resultString);
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}

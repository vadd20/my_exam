import io.DataLoader;
import io.DataWriter;
import service.Processor;

import java.io.IOException;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.04.05
 */
public class Main {

    public static void main(String[] args) throws IOException {
        DataLoader dataLoader = new DataLoader();
        var users = dataLoader.readUsers();
        var events = dataLoader.readEvents();
        Processor processor = new Processor();
        var eventToAvailableUsers = processor.collectEventToAvailableUsers(users, events);
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeData(eventToAvailableUsers);
    }
}

package duke;

import duke.data.exception.DukeException;
import duke.parser.Parser;

/**
 * Entry point of Duke Chatbot.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Won Ye Ji
 */
public class Duke {
    private Parser parser;

    /**
     * Runs the program until termination.
     */
    public Duke() {
        parser = new Parser();
        parser.initialiseDuke();
    }

    public String getResponse(String input) {
        try {
            return parser.runDuke(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

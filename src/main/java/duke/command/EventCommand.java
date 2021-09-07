package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Class that encapsulates the "Event" Command.
 *
 * @author Won Ye Ji
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "EVENT";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds an event task to the list";

    /**
     * Constructor for EventCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public EventCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Event" Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        int minCommandLength = 7;
        if (cmd.length() < minCommandLength) {
            throw new DukeException(Ui.emptyDescription("event"));
        } else {
            String[] split = cmd.split("/at ");
            if (split.length <= 1) {
                throw new DukeException(Ui.dateMissing());
            } else {
                Event event = new Event(split[0].substring(6), split[1]);
                if (event.dateFormatter(split[1]) == "Format error") {
                    throw new DukeException(Ui.dateFormatError());
                } else {
                    String toPrint = taskHandler.addTask(event);
                    toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                    storage.updateFile(taskHandler.formatTasksToSave("storage"));
                    return toPrint;
                }
            }
        }
    }
}

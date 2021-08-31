package duke.parser;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.*;
import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that makes sense of the user commands to Duke.
 *
 * @author Won Ye Ji
 */
public class Parser {
    private static boolean isTerminated = false;
    private TaskHandler taskHandler;
    private Storage storage;

    /**
     * Initialise variables to run Duke.
     */
    public void initialiseDuke() {
        try {
            storage = new Storage("./data/taskList.txt");
            ArrayList<Task> list = storage.loadTasks();
            taskHandler = new TaskHandler(list);
        } catch (DukeException e) {
            System.err.println("Error: Unable to initialise duke");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs Duke.
     */
    public void runDuke() {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        while (!isTerminated) {
            String cmd = sc.nextLine();
            String commandWord = cmd.split(" ")[0].toUpperCase();
            try {
                switch (commandWord) {
                case ListCommand.COMMAND_WORD:
                    ListCommand lc = new ListCommand(taskHandler, storage);
                    lc.execute(cmd);
                    break;
                case DoneCommand.COMMAND_WORD:
                    DoneCommand dc = new DoneCommand(taskHandler, storage);
                    dc.execute(cmd);
                    break;
                case DeleteCommand.COMMAND_WORD:
                    DeleteCommand dlc = new DeleteCommand(taskHandler, storage);
                    dlc.execute(cmd);
                    break;
                case ToDoCommand.COMMAND_WORD:
                    ToDoCommand tc = new ToDoCommand(taskHandler, storage);
                    tc.execute(cmd);
                    break;
                case DeadlineCommand.COMMAND_WORD:
                    DeadlineCommand deadlinec = new DeadlineCommand(taskHandler, storage);
                    deadlinec.execute(cmd);
                    break;
                case EventCommand.COMMAND_WORD:
                    EventCommand ec = new EventCommand(taskHandler, storage);
                    ec.execute(cmd);
                    break;
                case FindCommand.COMMAND_WORD:
                    FindCommand fc = new FindCommand(taskHandler, storage);
                    fc.execute(cmd);
                    break;
                case ByeCommand.COMMAND_WORD:
                    isTerminated = true;
                    ByeCommand bc = new ByeCommand(taskHandler, storage);
                    bc.execute(cmd);
                    break;
                default:
                    throw new DukeException(Ui.inputUnknown());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

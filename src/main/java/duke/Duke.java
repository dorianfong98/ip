package duke;

import duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Duke is a CLI chatbot to help you manage your tasks
 */
public class Duke {
    //Task identifiers and connectors
    public static final String DEADLINE_IDENTIFIER = "deadline";
    public static final String EVENT_IDENTIFIER = "event";
    public static final String DEADLINE_CONNECTOR = "/by";
    public static final String EVENT_CONNECTOR = "/at";

    //Confirmation messages for the marking and deleting of tasks
    public static final String MESSAGE_TASK_MARKED = "Nice! I've marked the task as done!:";
    public static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task from the list:";

    //Exception Messages
    public static final String EXCEPTION_INVALID_COMMAND = "I don't understand what that means, please try again!";

    //Extraction of the following classes so as to make the program more Object-oriented
    private Storage storage; //deals with loading tasks from the file and saving tasks in the file
    private TaskList tasks; //contains the task list, has operations to add/delete tasks in the list
    private Ui ui; //deals with interactions with the user
    private Parser parse; //deals with making sense of the user command

    // @@author dorianfong98-reused
    // Reused from https://github.com/dojh111/ip/tree/master/src/main/java/walter with minor modifications
    public Duke(String filePath) {
        ui = new Ui();
        parse = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        String userInput;
        String command;
        String[] splitUserInput;
        String details;
        boolean isFinished = false;

        ui.printStartupSequence();

        //Loop infinitely until user enters "bye"
        while (!isFinished) {
            userInput = ui.readUserCommand();
            splitUserInput = parse.divideUserCommand(userInput);
            command = parse.determineCommand(splitUserInput);
            try {
                switch (command) {
                case "help":
                    ui.showHelpMenu();
                    break;
                case "bye":
                    isFinished = true;
                    break;
                case "list":
                    ui.printTaskList(tasks.getTaskList());
                    break;
                case "todos":
                    ui.printTodoList(tasks.getTaskList());
                    break;
                case "deadlines":
                    ui.printDeadlineList(tasks.getTaskList());
                    break;
                case "events":
                    ui.printEventList(tasks.getTaskList());
                    break;
                case "done":
                    details = tasks.setTaskAsDone(splitUserInput);
                    ui.printSetDeleteConfirmMessage(MESSAGE_TASK_MARKED, details);
                    storage.writeToFile(tasks.getTaskList());
                    break;
                case "delete":
                    details = tasks.deleteTask(splitUserInput);
                    ui.printSetDeleteConfirmMessage(MESSAGE_TASK_DELETED, details);
                    storage.writeToFile(tasks.getTaskList());
                    break;
                case "todo":
                    tasks.addTodoTask(userInput);
                    ui.printTaskAddedConfirmation(tasks.getTaskList());
                    storage.writeToFile(tasks.getTaskList());
                    break;
                case "deadline":
                    tasks.addNewTimedEvent(userInput, DEADLINE_IDENTIFIER, DEADLINE_CONNECTOR);
                    ui.printTaskAddedConfirmation(tasks.getTaskList());
                    storage.writeToFile(tasks.getTaskList());
                    break;
                case "event":
                    tasks.addNewTimedEvent(userInput, EVENT_IDENTIFIER, EVENT_CONNECTOR);
                    ui.printTaskAddedConfirmation(tasks.getTaskList());
                    storage.writeToFile(tasks.getTaskList());
                    break;
                case "schedule":
                    tasks.getSchedule(splitUserInput);
                    break;
                case "find":
                    tasks.findTask(userInput);
                    break;
                case "clear":
                    tasks.clearTaskList();
                    storage.clearFile();
                    ui.printClearTaskListConfirmation();
                    break;
                default:
                    throw new DukeException(EXCEPTION_INVALID_COMMAND);
                }

            } catch (DukeException e) {
                ui.showDukeError(e.getErrorMessage());

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                ui.showInvalidNumberError();

            } catch (NumberFormatException e) {
                ui.showInvalidInputError();
            } catch (IOException e) {
                ui.showFileSaveError();
            }
        }

        ui.printExitSequence();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
    //@@author

}

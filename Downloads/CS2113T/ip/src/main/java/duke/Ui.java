package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    //ASCII art
    public static final String BYE_ART =
            "$$$$$$$\\ $$\\     $$\\ $$$$$$$$\\ \n"
            + "$$  __$$\\\\$$\\   $$  |$$  _____|\n"
            + "$$ |  $$ |\\$$\\ $$  / $$ |      \n"
            + "$$$$$$$\\ | \\$$$$  /  $$$$$\\    \n"
            + "$$  __$$\\   \\$$  /   $$  __|   \n"
            + "$$ |  $$ |   $$ |    $$ |      \n"
            + "$$$$$$$  |   $$ |    $$$$$$$$\\ \n"
            + "\\_______/    \\__|    \\________|\n";

    public static final String DUKE_ART =
            "$$$$$$$\\            $$\\                 \n"
            + "$$  __$$\\           $$ |                \n"
            + "$$ |  $$ |$$\\   $$\\ $$ |  $$\\  $$$$$$\\  \n"
            + "$$ |  $$ |$$ |  $$ |$$ | $$  |$$  __$$\\ \n"
            + "$$ |  $$ |$$ |  $$ |$$$$$$  / $$$$$$$$ |\n"
            + "$$ |  $$ |$$ |  $$ |$$  _$$<  $$   ____|\n"
            + "$$$$$$$  |\\$$$$$$  |$$ | \\$$\\ \\$$$$$$$\\ \n"
            + "\\_______/  \\______/ \\__|  \\__| \\_______|\n";

    //General UI messages
    public static final String MESSAGE_TASK_ADDED_CONFIRM = " Got it, I've added this task: ";
    public static final String MESSAGE_WELCOME_TO = "Hello, welcome to\n";
    public static final String MESSAGE_INTRO_GREETING = " Hello! I'm Duke";
    public static final String MESSAGE_INTRO_DUKE_QUERY = " What can I do for you?";
    public static final String MESSAGE_CLOSING = " Bye. Hope to see you again soon!";
    public static final String MESSAGE_DOUBLE_WHITESPACE = "   ";
    public static final String MESSAGE_LINE_SEPARATOR =
            "______________________________________________________________________________";
    public static final String MESSAGE_NOW_YOU_HAVE = " Now you have ";
    public static final String MESSAGE_IN_THE_LIST = " in the list.";
    public static final String MESSAGE_ERROR_TASK_UNAVAILABLE = "There are no tasks available for now. Add a task to continue.";
    public static final String MESSAGE_TASKS_IN_LIST = " Here are the tasks in your list: ";

    //Exception error messages
    public static final String EXCEPTION_FILE_ERROR = "Oops! Something went wrong while creating a save file!";
    public static final String EXCEPTION_INVALID_TASK_NUMBER = "Invalid task number entered... Please try again!";
    public static final String EXCEPTION_DONE_EXPECTED_INTEGER =
            "I'm sorry, I don't understand that ;-;. Please enter a number instead!";
    public static final String EXCEPTION_FILE_WRITE_ERROR = "Oh no, something went wrong while saving!";
    public static final String EXCEPTION_INVALID_DATE_FORMAT = "Please enter date in this format:\n[YYYY-MM-DD]";

    /** Prints separator component after text is printed */
    public static void printSeparator() {
        System.out.println(MESSAGE_LINE_SEPARATOR);
    }

    /** Prints startup greet sequence */
    public void printStartupSequence() {
        System.out.println(MESSAGE_WELCOME_TO + DUKE_ART);
        printSeparator();
        System.out.println(MESSAGE_INTRO_GREETING);
        System.out.println(MESSAGE_INTRO_DUKE_QUERY);
        printSeparator();
    }

    /** Prints closing sequence */
    public void printClosingSequence() {
        printSeparator();
        System.out.println(MESSAGE_CLOSING);
        System.out.println(BYE_ART);
        printSeparator();
    }

    /** Reads user command and returns command */
    public String readUserCommand() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }

    /**
     * Prints the confirmation messages for setTaskAsDone and deleteTask
     *
     * @param message  The header message to inform the user whether action is set or delete
     * @param itemDetails  Details of the item that was set or deleted
     */
    public void printSetDeleteConfirmMessage(String message, String itemDetails) {
        printSeparator();
        System.out.println(message);
        System.out.println(" " + itemDetails);
        printSeparator();
    }

    /**
     * Print confirmation text when a new task is added
     *
     * @params tasks  Array of current stored tasks
     * @params taskCount  Current count of tasks stored
     */
    public void printTaskAddedConfirmation(ArrayList<Task> tasks) {
        printSeparator();
        System.out.println(MESSAGE_TASK_ADDED_CONFIRM);
        System.out.println(MESSAGE_DOUBLE_WHITESPACE + tasks.get(tasks.size() - 1).toString());
        System.out.println(MESSAGE_NOW_YOU_HAVE + tasks.size() + MESSAGE_IN_THE_LIST);
        printSeparator();
    }

    /**
     * Print list of tasks when user requests
     *
     * @params tasks  Array of current stored tasks
     */
    public void printTaskList(ArrayList<Task> tasks) {
        int taskNumber = 1;
        printSeparator();
        if (tasks.size() == 0) {
            System.out.println(MESSAGE_ERROR_TASK_UNAVAILABLE);
            return;
        }
        System.out.println(MESSAGE_TASKS_IN_LIST);
        for (Task task : tasks) {
            System.out.println(" " + taskNumber + "." + task);
            taskNumber++;
        }
        printSeparator();
    }

    public static void printScheduleForDay(ArrayList<Task> tasksOnDay, String formattedDate) {
        int taskCount = 1;
        printSeparator();
        System.out.println("Hi! You have the following events on " + formattedDate + ":");
        for (Task task : tasksOnDay) {
            String taskNumber = " " + taskCount + ". ";
            System.out.println(taskNumber + task.toString());
            taskCount++;
        }
        printSeparator();
    }
    public static void printSearchResults(ArrayList<Task> searchResults, String searchTerm) {
        int taskCount = 1;
        printSeparator();
        System.out.println("Here are your search resultst for: " + searchTerm);
        for (Task task : searchResults) {
            String taskNumber = " " + taskCount + ". ";
            System.out.println(taskNumber + task.toString());
            taskCount++;
        }
        printSeparator();
    }

    public void showLoadingError() {
        System.out.println(EXCEPTION_FILE_ERROR);
    }

    public void showDukeError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showInvalidNumberError() {
        System.out.println(EXCEPTION_INVALID_TASK_NUMBER);
    }

    public void showInvalidInputError() {
        System.out.println(EXCEPTION_DONE_EXPECTED_INTEGER);
    }

    public void showFileSaveError() {
        System.out.println(EXCEPTION_FILE_WRITE_ERROR);
    }

    public static void showInvalidDateFormatError() {
        System.out.println(EXCEPTION_INVALID_DATE_FORMAT);
    }

}

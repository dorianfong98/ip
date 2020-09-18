package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * Duke is a chat bot which can help the user do multiple tasks
 */
public class Duke {
    //Identifiers and values
    public static final String DEADLINE_IDENTIFIER = "/by";
    public static final String EVENT_IDENTIFIER = "/at";
    public static final String WHITESPACE_IDENTIFIER = " ";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String BLANK_SPACE = "";

    //File Path and other constants
    public static final String FILE_PATH = "duke.txt";
    public static final String SAVE_DELIMITER = "--";
    public static final String FILE_MESSAGE_CREATED_SUCCESS = "Save file creation successful!";
    public static final String FILE_MESSAGE_NO_SAVE_DETECTED = "No previous saves detected! Creating save file...";

    //Printed Messages
    public static final String MESSAGE_DOUBLE_WHITESPACE = "   ";
    public static final String MESSAGE_LINE_SEPARATOR =
            "______________________________________________________________________________";
    public static final String MESSAGE_NOW_YOU_HAVE = " Now you have ";
    public static final String MESSAGE_IN_THE_LIST = " in the list.";
    public static final String MESSAGE_ERROR_TASK_UNAVAILABLE = "No tasks to complete for now. Add a task to begin.";
    public static final String MESSAGE_TASKS_IN_LIST = " Here are the tasks in your list: ";
    public static final String MESSAGE_TASK_MARKED = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:  ";
    public static final String MESSAGE_TASK_ADDED_CONFIRM = " Got it, I've added this task: ";
    public static final String MESSAGE_INTRO_GREETING = " Hello! I'm Duke";
    public static final String MESSAGE_INTRO_DUKE_QUERY = " What can I do for you?";
    public static final String MESSAGE_CLOSING = " Bye. Hope to see you again soon! :)";

    //Exception Messages
    public static final String EXCEPTION_INVALID_COMMAND = "I don't know what that means, please try again!";
    public static final String EXCEPTION_EMPTY_TODO = "Description of a todo cannot be empty!";
    public static final String EXCEPTION_INVALID_TASK_NUMBER = "Invalid task number entered... Please try again!";
    public static final String EXCEPTION_EMPTY_DONE = "You have to enter a task number! Please try again!";
    public static final String EXCEPTION_TIMEDEVENT =
            "The command requires both description and time information in the format of: \n";
    public static final String EXCEPTION_TIMEDEVENT_DESCRIPTION = "[description] ";
    public static final String EXCEPTION_TIMEDEVENT_TIMEINFO = " [time information]";
    public static final String EXCEPTION_DONE_EXPECTED_INTEGER =
            "I'm sorry, I don't understand that. Please enter a number instead!";
    public static final String EXCEPTION_FILE_ERROR = "Oops! something went wrong while creating a save file";
    public static final String EXCEPTION_FILE_WRITE_ERROR = "Oops! something went wrong while saving!";


    public static final String TODO_ICON = "[T]";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";

    /** Prints separator component after text is printed */
    public static void printSeparator() {
        System.out.println(MESSAGE_LINE_SEPARATOR);
    }

    /**
     * Prints startup greet sequence
     *
     */
    public static void printStartupSequence() {
        printSeparator();
        System.out.println(MESSAGE_INTRO_GREETING);
        System.out.println(MESSAGE_INTRO_DUKE_QUERY);
        printSeparator();
    }

    /**
     * Prints closing sequence
     *
     */
    public static void printClosingSequence() {
        printSeparator();
        System.out.println(MESSAGE_CLOSING);
        printSeparator();
    }

    /**
     * Removes the command passed into the method and replaces it with white space
     *
     * @params userInput  Original string typed by user and to be modified
     * @params commandToRemove  Command to be removed from string
     */
    public static String removeCommandFromInput(String userInput, String commandToRemove) {
        String modifiedUserInput = userInput.replace(commandToRemove, BLANK_SPACE);
        return modifiedUserInput.trim();
    }

    /**
     * Returns a string array with the task's description and additional information
     *
     * @params userInput Original string typed by user and to be modified
     * @params commandToRemove  Command to be removed from string
     * @params identifier  Identifier token to split the string with
     **/
    public static String[] getTaskInfo(String userInput, String commandToRemove, String identifier) {
        String modifiedString = removeCommandFromInput(userInput, commandToRemove);
        return modifiedString.split(identifier);
    }

    /**
     * Adds user input into the task list
     *
     * @params tasks  Array of current stored tasks
     * @params userInput  Original input by user
     * @params taskCount  Current count of tasks stored
     */

    public static void addTodoTask(ArrayList<Task> tasks, String userInput) throws DukeException, IOException {
        String taskDescription = removeCommandFromInput(userInput, COMMAND_TODO);

        //Check for exception where user input for task is empty
        if (taskDescription.equals(BLANK_SPACE)) {
            throw new DukeException(EXCEPTION_EMPTY_TODO);
        }

        tasks.add(new Todo(taskDescription));
        writeToFile(tasks);
    }

    /**
     * Adds new timed event tasks such as events or deadlines into the task list
     *
     * @params tasks  Array of current stored tasks
     * @params userInput  Original input by user
     * @params taskCount  Current count of tasks stored
     * @params command  The command entered - Either event or deadline
     * @params eventIdentifier  Identifier to determine string information - Either /at or /by
     * */
    public static void addNewTimedEvent(ArrayList<Task> tasks, String userInput, String command,
            String eventIdentifier) throws DukeException, IOException {
        String description;
        String timeInformation;
        boolean fieldsArePresent = true;

        String[] informationStrings = getTaskInfo(userInput, command, eventIdentifier);

        //Check if both fields have been fulfilled
        for (String information : informationStrings) {
            if (information.equals(BLANK_SPACE)) {
                fieldsArePresent = false;
                break;
            }
        }

        //Check if additional information was given
        if (informationStrings.length < 2 || !fieldsArePresent) {
            String exceptionMessage = command +
                    EXCEPTION_TIMEDEVENT +
                    EXCEPTION_TIMEDEVENT_DESCRIPTION + eventIdentifier + EXCEPTION_TIMEDEVENT_TIMEINFO;
            throw new DukeException(exceptionMessage);
        }

        //Set variables
        description = informationStrings[0].trim();
        timeInformation = informationStrings[1].trim();

        //Create new task objects
        switch (command) {
        case "event":
            tasks.add(new Event(description, timeInformation));
            break;
        case "deadline":
            tasks.add(new Deadline(description, timeInformation));
            break;
        default:
            break;
        }
        writeToFile(tasks);
    }

    /**
     * Print confirmation text when a new task is added
     *
     * @params tasks  Array of current stored tasks
     * @params taskCount  Current count of tasks stored
     */
    public static void printTaskAddedConfirmation(ArrayList<Task> tasks) {
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
    public static void printTaskList(ArrayList<Task> tasks) {
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

    /**
     * Checks for invalid command and throws DukeException
     */
    public static void checkForValidInput(String[] splitUserInput) throws DukeException {
        if (splitUserInput.length == 1) {
            throw new DukeException(EXCEPTION_EMPTY_DONE);
        }
    }

    /**
     * Sets isDone of selected task to true
     *
     * @params tasks  Array of current stored tasks
     * @params splitUserInput  Array of strings after original user input has been split by whitespace
     * @params taskCount  Current count of tasks stored
     */
    public static void setTaskAsDone(ArrayList<Task> tasks, String[] splitUserInput)
            throws DukeException, IOException {
        checkForValidInput(splitUserInput);
        if (splitUserInput.length == 1) {
            throw new DukeException(EXCEPTION_EMPTY_DONE);
        }
        int taskNumber = Integer.parseInt(splitUserInput[1]) - 1;
        String markedItemDetails;

        //TaskNumber is valid
        tasks.get(taskNumber).setAsDone();
        markedItemDetails = tasks.get(taskNumber).toString();

        printSetOrDeleteConfirmation(MESSAGE_TASK_MARKED, markedItemDetails);
        writeToFile(tasks);
    }

    /**
     * Removes task from the tasks ArrayList
     *
     * @param tasks  Array of current stored tasks
     * @param splitUserInput  Array of strings after original user input has been split by whitespace
     */
    public static void deleteTask(ArrayList<Task> tasks, String[] splitUserInput) throws DukeException, IOException {
        checkForValidInput(splitUserInput);
        int taskToDelete = Integer.parseInt(splitUserInput[1]) - 1;
        String deleteItemDetails = tasks.get(taskToDelete).toString();

        //TaskNumber is valid
        tasks.remove(taskToDelete);
        printSetOrDeleteConfirmation(MESSAGE_TASK_DELETED, deleteItemDetails);
        writeToFile(tasks);
    }

    /**
     * Prints the confirmation messages for setTaskAsDone and deleteTask
     *
     * @param message  The initial message to inform the user whether they are setting or deleting
     * @param itemDetails  Details of the item that was set or deleted
     */
    public static void printSetOrDeleteConfirmation(String message, String itemDetails) {
        printSeparator();
        System.out.println(message);
        System.out.println(" " + itemDetails);
        printSeparator();
    }

    /**
     * Writes data from the tasks array onto a file, so that data can be saved
     *
     * @param tasks  ArrayList of tasks to be written onto the file
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        //Clearing file before writing
        FileWriter fwClear = new FileWriter(FILE_PATH);
        fwClear.write("");
        fwClear.close();

        //Append information into file
        FileWriter fw = new FileWriter(FILE_PATH, true);
        for (Task task : tasks) {
            int taskStatus;
            //Determine status to write to file based on task status
            if (task.getStatus()) {
                taskStatus = 1;
            } else {
                taskStatus = 0;
            }
            String taskToSave = task.getTaskIcon() + " | " + taskStatus + " | "
                    + task.getDescription() + SAVE_DELIMITER + task.getTimingInformation() + System.lineSeparator();
            fw.write(taskToSave);
        }
        fw.close();
    }

    /**
     * Recreates tasks array by reading data from file. If no file is available, create new file
     */
    public static void readFileContents(ArrayList<Task> tasks) throws IOException {
        File f = new File(FILE_PATH);

        //Read from file if exists, else create new directory and files
        if (f.exists()) {
            Scanner s = new Scanner(f);
            //Re-create task objects in the array
            while (s.hasNext()) {
                String taskInformation = s.nextLine();
                String[] taskComponents = taskInformation.split(SAVE_DELIMITER);
                switch (taskComponents[0]) {
                case TODO_ICON:
                    tasks.add(new Todo(taskComponents[2]));
                    break;
                case DEADLINE_ICON:
                    tasks.add(new Deadline(taskComponents[2], taskComponents[3]));
                    break;
                case EVENT_ICON:
                    tasks.add(new Event(taskComponents[2], taskComponents[3]));
                    break;
                }
                //Set status of task to done if required
                if (Integer.parseInt(taskComponents[1]) == 1) {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
            }
        } else {
            //No existing file detected. Create new save file
            System.out.println(FILE_MESSAGE_NO_SAVE_DETECTED);
            boolean fileCreated = f.createNewFile();
            if (fileCreated) {
                System.out.println(FILE_MESSAGE_CREATED_SUCCESS);
            }
        }
    }

    public static void main(String[] args) {
        //Initialise variables
        String userInput;
        String[] splitUserInput;
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isFinished = true;
        Scanner in = new Scanner(System.in);

        //Print startup sequence
        printStartupSequence();

        //Read information from saved files
        try {
            readFileContents(tasks);
        } catch (IOException e) {
            System.out.println(EXCEPTION_FILE_ERROR);
        }

        //Loop infinitely until user enters "bye"
        while (isFinished) {
            userInput = in.nextLine();
            splitUserInput = userInput.split(WHITESPACE_IDENTIFIER);
            try {
                switch (splitUserInput[0]) {
                case "bye":
                    isFinished = false;
                    break;
                case "list":
                    printTaskList(tasks);
                    break;
                case "done":
                    setTaskAsDone(tasks, splitUserInput);
                    break;
                case "delete":
                    deleteTask(tasks, splitUserInput);
                    break;
                case "todo":
                    addTodoTask(tasks, userInput);
                    printTaskAddedConfirmation(tasks);
                    break;
                case "deadline":
                    addNewTimedEvent(tasks, userInput, COMMAND_DEADLINE, DEADLINE_IDENTIFIER);
                    printTaskAddedConfirmation(tasks);
                    break;
                case "event":
                    addNewTimedEvent(tasks, userInput, COMMAND_EVENT, EVENT_IDENTIFIER);
                    printTaskAddedConfirmation(tasks);
                    break;
                default:
                    //Throw exception for invalid command - Break statement unreachable
                    throw new DukeException(EXCEPTION_INVALID_COMMAND);
                }

            } catch (DukeException e) {
                //Catch exceptions and print error messages unique to Duke
                System.out.println(e.getErrorMessage());

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                //Catch exceptions when index given is out of bounds or invalid
                System.out.println(EXCEPTION_INVALID_TASK_NUMBER);

            } catch (NumberFormatException e) {
                //Catch exception when string is given for a field which requires number
                System.out.println(EXCEPTION_DONE_EXPECTED_INTEGER);
            } catch (IOException e) {
                System.out.println(EXCEPTION_FILE_WRITE_ERROR);
            }
        }

        //Print closing sequence
        printClosingSequence();
    }

}

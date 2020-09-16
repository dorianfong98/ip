package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Duke is a CLI chatbot for managing and tracking tasks.
 * Currently, there are 3 types of tasks Duke can handle:
 * 1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
 * 2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 * 3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Duke {
    //Prints separator line
    public static void printSeparator() {
        System.out.println("______________________________________________________________________________");
    }

    // Removes command from userInput and replaces it with a blank space
    public static String removeCommandFromInput(String userInput, String commandToRemove) {
        String modifiedUserInput = userInput.replace(commandToRemove, "");
        return modifiedUserInput.trim();
    }

    //Returns an array with the task's description and additional information
    public static String[] getTaskInfo(String userInput, String commandToRemove, String identifier) {
        String modifiedString = removeCommandFromInput(userInput, commandToRemove);
        return modifiedString.split(identifier);
    }

    //adds new todo task
    public static void addTodoTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        String taskDescription = removeCommandFromInput(userInput, "todo");

        //Check for exception where user input for task is empty
        if (taskDescription.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        tasks.add(new Todo(taskDescription));
    }

    //adds new timed event, i.e. Deadline or Event
    public static void addNewTimedEvent(ArrayList<Task> tasks, String userInput, String command,
                                        String eventIdentifier) throws DukeException {
        String description;
        String timeInformation;
        boolean fieldsFilled = true;

        String[] informationStrings = getTaskInfo(userInput, command, eventIdentifier);

        //Check if both fields have been fulfilled
        for (String information : informationStrings) {
            if (information.equals("")) {
                fieldsFilled = false;
                break;
            }
        }

        //Check if additional information was given
        if (informationStrings.length < 2 || !fieldsFilled) {
            String exceptionMessage = "☹ OOPS!!! The " + command +
                    " command requires both description and time information in the format of: \n" +
                    "[description] " + eventIdentifier + " [time]";
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
    }

    //Prints a confirmation message once a new task is added successfully
    public static void printTaskAddedConfirmation(ArrayList<Task> tasks) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        printSeparator();
    }

    //Prints a list of tasks upon request
    public static void printTaskList(ArrayList<Task> tasks) {
        int taskNumber = 1;
        printSeparator();
        if (tasks.size() == 0) {
            System.out.println("No tasks available! Add a task to continue.");
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (Task task : tasks) {
            System.out.println(" " + taskNumber + "." + task);
            taskNumber++;
        }
        printSeparator();
    }

    //checks input validity, throws DukeException otherwise
    public static void checkInputValidity(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please enter a task number!");
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, String[] splitInput) throws DukeException {
        checkInputValidity(splitInput);
        int taskToDelete = Integer.parseInt(splitInput[1]) - 1;
        String deleteItemDetails = tasks.get(taskToDelete).toString();

        //TaskNumber is valid
        tasks.remove(taskToDelete);

        printSetOrDeleteConfirmation("Noted. I've removed this task from the list:", deleteItemDetails);
    }

    public static void setTaskAsDone(ArrayList<Task> tasks, String[] splitInput) throws DukeException {
        checkInputValidity(splitInput);
        int taskNumber = Integer.parseInt(splitInput[1]) - 1;
        String markedItemDetails;

        //TaskNumber is valid
        tasks.get(taskNumber).markAsDone();
        markedItemDetails = tasks.get(taskNumber).toString();

        printSetOrDeleteConfirmation("Nice! I've marked this task as done: ", markedItemDetails);
    }


    public static void printSetOrDeleteConfirmation(String message, String itemDetails) {
        printSeparator();
        System.out.println(message);
        System.out.println(" " + itemDetails);
        printSeparator();
    }

    public static void main(String[] args) {
        //Variable declarations
        String userInput;
        String[] splitInput;
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isFinished = true;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello from\n");
        printSeparator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printSeparator();

        //Loops infinitely until user inputs "bye"
        while (isFinished) {
            userInput = in.nextLine();
            splitInput = userInput.split(" ");
            try {
                switch (splitInput[0]) {
                case "bye":
                    isFinished = false;
                    break;
                case "list":
                    printTaskList(tasks);
                    break;
                case "done":
                    setTaskAsDone(tasks, splitInput);
                    break;
                case "delete":
                    deleteTask(tasks, splitInput);
                    break;
                case "todo":
                    addTodoTask(tasks, userInput);
                    printTaskAddedConfirmation(tasks);
                    break;
                case "deadline":
                    addNewTimedEvent(tasks, userInput, "deadline", "/by");
                    printTaskAddedConfirmation(tasks);
                    break;
                case "event":
                    addNewTimedEvent(tasks, userInput, "event", "/at");
                    printTaskAddedConfirmation(tasks);
                    break;
                default:
                    //Throw exception for invalid command - Break statement unreachable
                    throw new DukeException("Invalid Command! Try again.");
                }

            } catch (DukeException e) {
                //Catch exceptions and print error messages unique to Duke
                System.out.println(e.getErrorMessage());

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                //Catch exceptions when index given is out of bounds or invalid
                System.out.println("Invalid task number! Try again.");

            } catch (NumberFormatException e) {
                //Catch exception when string is given for a field which requires number
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }


        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }
}

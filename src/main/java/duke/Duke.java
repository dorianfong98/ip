package duke;
import duke.exceptions.WalterException;
import walter.tasks.Deadline;
import walter.tasks.Event;
import walter.tasks.Task;
import walter.tasks.Todo;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Duke is a CLI chatbot for managing and tracking tasks.
 * Currently, there are 3 types of tasks Duke can handle:
 * 1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
 * 2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 * 3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Duke {

    //Prints a list of tasks upon request
    public static void printList(Task[] tasks) {
        int taskNumber = 1;
        printSeparator();
        if(tasks.length == 0) {
            System.out.println("No tasks to complete for now.");
        }
        System.out.println(" Here are the tasks in your list: ");
        for(Task task : tasks) {
            System.out.println(" " + taskNumber + "." + task);
            taskNumber++;
        }
        printSeparator();
    }

   //LEVEL 4 FUNCTIONS
    public static void addNewTodo(Task[] tasks, String[] splitInput, int taskCount) throws DukeException {
        String description = "";
        for (int i = 1; i < splitInput.length; i++) {
            description += splitInput[i] + " ";
        }
        tasks[taskCount] = new Todo(description);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addNewDeadline(Task[] tasks, String[] splitInput, int taskCount) {
        String description;
        String by;
        int indexOfBy = 0;
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by")) {
                indexOfBy = i;
                break;
            }
        }
        description = getDescription(splitInput, indexOfBy);
        by = getDetails(splitInput, indexOfBy);
        tasks[taskCount] = new Deadline(description, by);
    }

    public static void addNewEvent(Task[] tasks, String[] splitInput, int taskCount){
        String at;
        int indexOfAt = 0;
        String description = getDescription(splitInput, indexOfAt);

        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/at")) {
                indexOfAt = i;
                break;
            }
        }
        description = getDescription(splitInput, indexOfAt);
        at = getDetails(splitInput, indexOfAt);
        tasks[taskCount] = new Event(description, at);
    }

    public static String getDescription(String[] splitInput, int indexOfBy) {
        String description = "";
        for (int i = 0; i < indexOfBy; i++) {
            description += splitInput[i] + " ";
        }
        return description;
    }
    public static String getDetails(String[] splitInput, int indexOfBy) {
        String details = "";
        for (int i = indexOfBy + 1; i < splitInput.length; i++) {
            details += splitInput[i] + " ";
        }
        return details;
    }

    public static void printConfirmation(Task[] tasks, int taskCount) {
        int numberOfTasks = taskCount + 1;
        printSeparator();
        System.out.println(" Got it, I've added this task: ");
        System.out.println("   " + tasks[taskCount]);
        System.out.println(" Now you have " + numberOfTasks + " task(s) in the list.");
        printSeparator();
    }


    //Adds new task
    public static void taskAdder(Task[] tasks, String description, int taskCount) {
        Task newTask = new Task(description);
        tasks[taskCount] = newTask;
        printSeparator();
        System.out.println(" added: " + description);
        printSeparator();
    }

    //Sets isDone of Task object to be true
    public static void markTaskAsDone(Task[] tasks, String description, int taskCount) {
        //Determine index of task to be marked as done
        String[] splitDescription = description.split(" ");
        if(splitDescription.length == 1) {
            System.out.println("Invalid command!");
            printSeparator();
            return;
        }
        int taskNumber = Integer.parseInt(splitDescription[1]) - 1;
        printSeparator();
        //Check if taskNumber is out of bounds
        if(taskNumber < 0 || taskNumber > taskCount - 1) {
            System.out.println("Invalid task number!");
            printSeparator();
            return;
        }
        //the taskNumber is valid
        tasks[taskNumber].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [" + "\u2713" + "] " + tasks[taskNumber].description);
        printSeparator();
    }
    public static void deleteTask(ArrayList<Task> tasks, String[] splitInput) throws DukeException {
        checkInputValidity(splitInput);
        int taskToDelete = Integer.parseInt(splitInput[1]) - 1;
        String deleteItemDetails = tasks.get(taskToDelete).toString();

        //TaskNumber is valid
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + );
        System.out.println(" Now you have " + numberOfTasks + " task(s) in the list.");
        printSeparator();
        tasks.remove(taskToDelete);


    }

    //Prints separator line
    public static void printSeparator() {
        System.out.println("______________________________________________________________________________");
    }
    public static void checkInputValidity(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please enter a task number!");
        }
    }

    public static void main(String[] args) {
        String userInput;
        String[] splitInput;
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isFinished = true;
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello from\n");
        printSeparator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printSeparator();

        //Loops infinitely until user inputs "bye"
        while(isFinished) {
            userInput = in.nextLine();
            splitInput = userInput.split(" ");
            try {
                switch (splitInput[0]) {
                case "bye":
                    isFinished = false;
                    break;
                case "list":
                    printList(Arrays.copyOf(tasks, taskCount));
                    break;
                case "done":
                    markTaskAsDone(tasks, userInput, taskCount);
                    break;
                case "todo":
                    addNewTodo(tasks, splitInput, taskCount);
                    printConfirmation(tasks, taskCount);
                    taskCount++;
                    break;
                case "deadline":
                    addNewDeadline(tasks, Arrays.copyOfRange(splitInput, 1, splitInput.length), taskCount);
                    printConfirmation(tasks, taskCount);
                    taskCount++;
                    break;
                case "event":
                    addNewEvent(tasks, Arrays.copyOfRange(splitInput, 1, splitInput.length), taskCount);
                    printConfirmation(tasks, taskCount);
                    taskCount++;
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.errorMessage);
            }
        }


        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }
}

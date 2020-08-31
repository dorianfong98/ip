import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    //Prints list of tasks upon request
    public static void printList(Task[] tasks) {
        int taskNumber = 1;
        printSeparator();
        if(tasks.length == 0) {
            System.out.println("No tasks available for now. Add tasks to continue.");
        }
        for(Task task : tasks) {
            System.out.println(" " + taskNumber + ":[" + task.getStatusIcon() + "] " + task.description);
            taskNumber++;
        }
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

    //Prints separator line
    public static void printSeparator() {
        System.out.println("______________________________________________________________________________");
    }

    public static void main(String[] args) {
        String userInput;
        String[] command;
        boolean isFinished = true;
        Task[] tasks = new Task[100];
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
            command = userInput.split(" ");
            switch (command[0]) {
            case "bye":
                isFinished = false;
                break;
            case "list":
                printList(Arrays.copyOf(tasks, taskCount));
                break;
            case "done":
                markTaskAsDone(tasks, userInput, taskCount);
                break;
            default:
                //Add new task into tasks array
                taskAdder(tasks, userInput, taskCount);
                taskCount++;
                break;
            }
        }

        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }
}

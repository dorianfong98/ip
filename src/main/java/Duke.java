import java.util.Scanner;

public class Duke {
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
        } else {
            System.out.print( "____________________________________________________________\n");
            System.out.println(line);
            System.out.print( "____________________________________________________________\n");
            echo();
        }
    }

    public static void main(String[] args) {
        System.out.print( "____________________________________________________________\n");
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        System.out.print( "\n" );
        System.out.print( "____________________________________________________________\n");
        Scanner in = new Scanner(System.in);
        echo();
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int i = 0;
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
        }
        else if (line.equals("list")) {
            String[] result = Arrays.copyOf(list, i);
            for (String item: result) {
                System.out.println(item);
            }
            echo();
        }
        else {
            System.out.print( "____________________________________________________________\n");
            System.out.println("added: " + line);
            list[i] =i+1 +". " + line;
            i++;
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
        echo();
    }
}

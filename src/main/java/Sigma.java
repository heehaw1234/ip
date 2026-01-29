import java.util.Scanner; // object to take in user input

public class Sigma {
    /** size of the list of tasks */
    private static final int LIST_SIZE = 100;
    /** list of tasks added */
    private static Task[] taskList = new Task[LIST_SIZE]; // initialise an array of size 100
    /** head of the taskList */
    private static int taskListHead = 0;

    /** driver code, entry point of programme */
    public static void main(String[] args) {
        sayIntro();

        // initialise scanner object
        Scanner in = new Scanner(System.in);

        while (true){
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                return;
            }
            if (userInput.equals("list")) {
                printList();
                continue;
            }
            if (userInput.startsWith("mark")) {
                setTaskStatus(userInput.substring(5, 6), true);
                continue;
            }
            if (userInput.startsWith("unmark")) {
                setTaskStatus(userInput.substring(7, 8), false);
                continue;
            }

            if (userInput.equals("sigma")) {
                echo("SIGMA INDEED!!!!");
            }
            addTask(userInput);
        }
    }

    /** this function sets whether or not a task is marked */
    private static void setTaskStatus(String index, boolean isMark) {
        int targetIndex = Integer.parseInt(index);
        String isDoneString = (isMark) ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";

        if (isMark) {
            taskList[targetIndex - 1].markAsDone();
        }
        else if (!isMark) {
            taskList[targetIndex - 1].markAsNotDone();
        }

        System.out.println("____________________________________________________________");
        System.out.println(isDoneString);
        System.out.println(taskList[targetIndex - 1].returnTaskString());
        System.out.println("____________________________________________________________");
    }

    /** this function greets the user */
    private static void sayIntro() {
        String logo = " ____  ___  ____  __  __    _    \n"
                + "/ ___||_ _|/ ___||  \\/  |  / \\   \n"
                + "\\___ \\ | || |  _ | |\\/| | / _ \\  \n"
                + " ___) || || |_| || |  | |/ ___ \\ \n"
                + "|____/|___|\\____||_|  |_/_/   \\_\\\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can i do for you?\n");
        System.out.println("____________________________________________________________");
    }

    /** this function echoes back what the user input*/
    private static void echo(String userInput) {
        System.out.println("____________________________________________________________");
        System.out.println(userInput);
        System.out.println("____________________________________________________________");
    }

    /** this method says goodbye to the user */
    private static void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /** this function adds a task to the list of tasks */
    private static void addTask(String userInput) {
        Task toAdd = new Task(userInput);
        taskList[taskListHead] = toAdd;
        taskListHead += 1;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + userInput);
        System.out.println("____________________________________________________________");
    }

    /** this function prints the list of tasks a user has added with the checkbox status displayed */
    private static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskListHead; i += 1) {
            System.out.println((i+1) + "." + taskList[i].returnTaskString());
        }
        System.out.println("____________________________________________________________");
    }
}
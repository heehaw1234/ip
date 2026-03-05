import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a horizontal divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows the welcome/intro message with logo.
     */
    public void showWelcome() {
        String logo = " ____  ___  ____  __  __    _    \n"
                + "/ ___||_ _|/ ___||  \\/  |  / \\   \n"
                + "\\___ \\ | || |  _ | |\\/| | / _ \\  \n"
                + " ___) || || |_| || |  | |/ ___ \\ \n"
                + "|____/|___|\\____||_|  |_/_/   \\_\\\n";

        showLine();
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can i do for you?\n");
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Echoes a message back to the user.
     */
    public void echo(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Shows an error message.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Shows a loading error when saved tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("    Note: Could not load saved tasks. Starting fresh.");
    }

    /**
     * Shows the message after a task has been added.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Shows the message after a task has been marked or unmarked.
     */
    public void showTaskStatus(String statusMessage, Task task) {
        showLine();
        System.out.println(statusMessage);
        System.out.println(" " + task);
        showLine();
    }

    /**
     * Shows the message after a task has been deleted.
     */
    public void showDeletedTask(String statusMessage, Task task, int totalTasks) {
        showLine();
        System.out.println(statusMessage);
        System.out.println(" " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Shows the full task list.
     */
    public void showTaskList(ArrayList<Task> taskList, int taskListHead) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        if (taskListHead == 0) {
            System.out.println(" List is empty, add tasks");
        }
        for (int i = 0; i < taskListHead; i += 1) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        showLine();
    }

    /**
     * Shows matching tasks from a find query.
     */
    public void showMatchingTasks(ArrayList<Task> taskList, ArrayList<Integer> matchingIndices) {
        showLine();
        if (matchingIndices.isEmpty()) {
            System.out.println(" There are no matching tasks in your list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
        }
        for (int i = 0; i < matchingIndices.size(); i += 1) {
            System.out.println((i + 1) + "." + taskList.get(matchingIndices.get(i)));
        }
        showLine();
    }
}

import java.util.Scanner; // object to take in user input
import java.util.ArrayList; // for A-Collections level

public class Sigma {
    /**
     * list of tasks added
     */
    private static ArrayList<Task> taskList = new ArrayList<>();
    /**
     * head of the taskList
     */
    private static int taskListHead = 0;

    /**
     * driver code, entry point of programme
     */
    public static void main(String[] args) {
        taskListHead = FileHandler.loadTasks(taskList); // load tasks on startup into the current tasklist from sigma.txt, returns size of curr list
        sayIntro();

        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                String userInput = in.nextLine();
                if (userInput.startsWith("bye")) {
                    if (userInput.length() > 3) {
                        throw new SigmaExceptions.DidYouMeanToException("bye");
                    }
                    sayBye();
                    return;
                }
                if (userInput.startsWith("list")) {
                    if (userInput.length() > 4) {
                        throw new SigmaExceptions.DidYouMeanToException("list");
                    }
                    printList();
                } else if (userInput.startsWith("delete")) {
                    if (userInput.length() <= 6) {
                        throw new SigmaExceptions.EmptyDescriptionException("delete command");
                    }
                    setTaskStatus(userInput.substring(7), TaskStatus.DELETED);
                } else if (userInput.startsWith("mark")) {
                    if (userInput.length() <= 4) {
                        throw new SigmaExceptions.EmptyDescriptionException("mark command");
                    }
                    setTaskStatus(userInput.substring(5), TaskStatus.MARKED);
                } else if (userInput.startsWith("unmark")) {
                    if (userInput.length() <= 6) {
                        throw new SigmaExceptions.EmptyDescriptionException("unmark command");
                    }
                    setTaskStatus(userInput.substring(7), TaskStatus.UNMARKED);
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 4) {
                        throw new SigmaExceptions.EmptyDescriptionException("todo");
                    }
                    addTask(userInput.substring(5), TaskType.TODO);
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 8) {
                        throw new SigmaExceptions.EmptyDescriptionException("deadline");
                    }
                    addTask(userInput.substring(9), TaskType.DEADLINE);
                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 5) {
                        throw new SigmaExceptions.EmptyDescriptionException("event");
                    }
                    addTask(userInput.substring(6), TaskType.EVENT);
                } else if (userInput.equals("sigma")) {
                    echo("SIGMA INDEED!!!!");
                } else {
                    throw new SigmaExceptions.UnknownCommandException(userInput);
                }
            } catch (SigmaExceptions.UnknownCommandException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage()); // use the getMessage method from Exceptions class
                System.out.println("____________________________________________________________");
            } catch (SigmaExceptions.EmptyDescriptionException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage()); // use the getMessage method from Exceptions class
                System.out.println("____________________________________________________________");
            } catch (SigmaExceptions.InvalidTaskListIndexException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage()); // use the getMessage method from Exceptions class
                System.out.println("____________________________________________________________");
            } catch (SigmaExceptions.DidYouMeanToException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage()); // use the getMessage method from Exceptions class
                System.out.println("____________________________________________________________");
            } catch (SigmaExceptions.TaskHasInvalidArgsException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage()); // use the getMessage method from Exceptions class
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println("    OOPS!!! something went wrong"); //fallback
                System.out.println("____________________________________________________________");
            }
        }
    }

    /**
     * this function sets whether or not a task is marked or deleted
     */
    private static void setTaskStatus(String index, TaskStatus status) throws SigmaExceptions.InvalidTaskListIndexException {
        try {
            int targetIndex = Integer.parseInt(index);

            if (targetIndex > taskListHead || taskListHead == 0 || targetIndex < 0) {
                throw new SigmaExceptions.InvalidTaskListIndexException(targetIndex, taskListHead);
            }

            String isDoneString = (status == TaskStatus.MARKED) ? "Nice! I've marked this task as done:" : (status == TaskStatus.UNMARKED) ? "OK, I've marked this task as not done yet:" : "Noted. I've removed this task:";

            System.out.println("____________________________________________________________");
            System.out.println(isDoneString);
            System.out.println(" " + taskList.get(targetIndex - 1));

            if (status == TaskStatus.MARKED) {
                taskList.get(targetIndex - 1).markAsDone();
            } else if (status == TaskStatus.UNMARKED) {
                taskList.get(targetIndex - 1).markAsNotDone();
            } else {
                taskList.remove(targetIndex - 1);
                taskListHead -= 1;
                System.out.println("Now you have " + taskListHead + " tasks in the list.");
            }

            System.out.println("____________________________________________________________");

            FileHandler.saveTasks(taskList, taskListHead); // save arrayList current state

        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println("    OOPS!!! I'm sorry, please provide an integer when using mark/unmark/delete\n    e.g. \'mark 9\'");
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * this function greets the user
     */
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

    /**
     * this function echoes back what the user input
     */
    private static void echo(String userInput) {
        System.out.println("____________________________________________________________");
        System.out.println(userInput);
        System.out.println("____________________________________________________________");
    }

    /**
     * this method says goodbye to the user
     */
    private static void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * this function adds a task to the list of tasks
     */
    private static void addTask(String userInput, TaskType typeOfTask) throws SigmaExceptions.TaskHasInvalidArgsException {
        Task toAdd; // later on uses polymorphism to store the subtask object in the taskList array

        switch (typeOfTask) {
        case TODO:
            toAdd = new Todo(userInput, taskListHead);
            break;
        case DEADLINE:
            toAdd = new Deadline(userInput, taskListHead);
            break;
        case EVENT:
            toAdd = new Event(userInput, taskListHead);
            break;
        default:
            toAdd = new Task(userInput, taskListHead);
        }

        taskList.add(toAdd);
        taskListHead += 1;

        FileHandler.saveTasks(taskList, taskListHead); // save arrayList current state

        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + toAdd);
        System.out.println("Now you have " + taskListHead + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * this function prints the list of tasks a user has added with the checkbox status displayed
     */
    private static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        if (taskListHead == 0) {
            System.out.println(" List is empty, add tasks");
        }
        for (int i = 0; i < taskListHead; i += 1) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
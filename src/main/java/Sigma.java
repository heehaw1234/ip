import java.util.ArrayList;

/**
 * Represents the main chatbot application.
 * Initialises the necessary components & runs the command loop.
 */
public class Sigma {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Sigma chatbot instance.
     * Loads existing tasks from the file at the given path.
     *
     * @param filePath Path to the file used for saving/loading tasks.
     */
    public Sigma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop, reading and executing user commands
     * until the "bye" command is given.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();
                String[] parsed = Parser.parse(userInput);
                String commandWord = parsed[0];
                String arguments = parsed[1];

                switch (commandWord) {
                    case "bye":
                        ui.showBye();
                        return;
                    case "list":
                        ui.showTaskList(tasks.getTasks(), tasks.getSize());
                        break;
                    case "delete":
                        setTaskStatus(arguments, TaskStatus.DELETED);
                        break;
                    case "mark":
                        setTaskStatus(arguments, TaskStatus.MARKED);
                        break;
                    case "unmark":
                        setTaskStatus(arguments, TaskStatus.UNMARKED);
                        break;
                    case "find":
                        findTask(arguments.toLowerCase());
                        break;
                    case "todo":
                        addTask(arguments, TaskType.TODO);
                        break;
                    case "deadline":
                        addTask(arguments, TaskType.DEADLINE);
                        break;
                    case "event":
                        addTask(arguments, TaskType.EVENT);
                        break;
                    case "sigma":
                        ui.echo("SIGMA INDEED!!!!");
                        break;
                    default:
                        break;
                }
            } catch (SigmaExceptions.UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (SigmaExceptions.EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (SigmaExceptions.InvalidTaskListIndexException e) {
                ui.showError(e.getMessage());
            } catch (SigmaExceptions.DidYouMeanToException e) {
                ui.showError(e.getMessage());
            } catch (SigmaExceptions.TaskHasInvalidArgsException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("    OOPS!!! something went wrong");
            }
        }
    }

    /**
     * Finds tasks matching the given description and displays the results.
     */
    private void findTask(String taskDescription) {
        ArrayList<Integer> matchingIndices = tasks.findTasks(taskDescription);
        ui.showMatchingTasks(tasks.getTasks(), matchingIndices);
    }

    /**
     * Sets the status of a task (mark, unmark, or delete).
     */
    private void setTaskStatus(String index, TaskStatus status)
            throws SigmaExceptions.InvalidTaskListIndexException {
        try {
            int targetIndex = Integer.parseInt(index);

            if (targetIndex > tasks.getSize() || tasks.getSize() == 0 || targetIndex < 0) {
                throw new SigmaExceptions.InvalidTaskListIndexException(targetIndex, tasks.getSize());
            }

            String isDoneString = (status == TaskStatus.MARKED) ? "Nice! I've marked this task as done:"
                    : (status == TaskStatus.UNMARKED) ? "OK, I've marked this task as not done yet:"
                            : "Noted. I've removed this task:";

            if (status == TaskStatus.DELETED) {
                Task deletedTask = tasks.getTask(targetIndex - 1);
                tasks.deleteTask(targetIndex - 1);
                ui.showDeletedTask(isDoneString, deletedTask, tasks.getSize());
            } else {
                if (status == TaskStatus.MARKED) {
                    tasks.getTask(targetIndex - 1).markAsDone();
                } else {
                    tasks.getTask(targetIndex - 1).markAsNotDone();
                }
                ui.showTaskStatus(isDoneString, tasks.getTask(targetIndex - 1));
            }

            storage.save(tasks.getTasks());

        } catch (NumberFormatException e) {
            ui.showError(
                    "    OOPS!!! I'm sorry, please provide an integer when using mark/unmark/delete\n    e.g. \'mark 9\'");
        }
    }

    /**
     * Adds a new task to the task list.
     */
    private void addTask(String userInput, TaskType typeOfTask)
            throws SigmaExceptions.TaskHasInvalidArgsException {
        Task toAdd;

        switch (typeOfTask) {
            case TODO:
                toAdd = new Todo(userInput, tasks.getSize());
                break;
            case DEADLINE:
                toAdd = new Deadline(userInput, tasks.getSize());
                break;
            case EVENT:
                toAdd = new Event(userInput, tasks.getSize());
                break;
            default:
                toAdd = new Task(userInput, tasks.getSize());
                break;
        }

        tasks.addTask(toAdd);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(toAdd, tasks.getSize());
    }

    /**
     * Starts the Sigma chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sigma("./data/sigma.txt").run();
    }
}
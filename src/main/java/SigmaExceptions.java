/**
 * Contains all custom exception classes used by Sigma.
 */
public class SigmaExceptions {
    /**
     * Thrown when a command is given without a required description.
     */
    public static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String userInput) {
            super("    OOPS!!! The description of a/an " + userInput + " cannot be empty.");
        }
    }

    /**
     * Thrown when the user enters an unrecognised command.
     */
    public static class UnknownCommandException extends Exception {
        public UnknownCommandException(String userInput) {
            super("    OOPS!!! I'm sorry, but i dont know what " + userInput + " means.");
        }
    }

    /**
     * Thrown when the user provides an invalid task list index.
     */
    public static class InvalidTaskListIndexException extends Exception {
        public InvalidTaskListIndexException(int indexUserTriedToAccess, int sizeOfList) {
            super("    OOPS!!! U tried to access index " + indexUserTriedToAccess
                    + "\n    but the current list is of length " + sizeOfList);
        }
    }

    /**
     * Thrown when the user input resembles a known command with extra characters.
     */
    public static class DidYouMeanToException extends Exception {
        public DidYouMeanToException(String desiredCommand) {
            super("    OOPS!!! did you mean to type \'" + desiredCommand + "\'?");
        }
    }

    /**
     * Thrown when a task is created with invalid or missing arguments.
     */
    public static class TaskHasInvalidArgsException extends Exception {
        public TaskHasInvalidArgsException(String taskType) {
            super("    OOPS!!! I'm sorry, the arguments u have provided for the task type " + taskType
                    + "\n    is in the wrong format, it should be "
                    + (taskType.equals("events") ? "\'events /from today 6pm /to tomorrow 5pm\'"
                            : "\'deadline /by friday 0700\'"));
        }
    }
}
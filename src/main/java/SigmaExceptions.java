// wrapper class
public class SigmaExceptions {
    /**
     * exception to catch if a command does not have any arguments passed in
     */
    public static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String userInput) {
            super("    OOPS!!! The description of a/an " + userInput + " cannot be empty.");
        }
    }

    /**
     * exception to catch if a user has entered an unknown command
     */
    public static class UnknownCommandException extends Exception {
        public UnknownCommandException(String userInput) {
            super("    OOPS!!! I'm sorry, but i dont know what " + userInput + " means.");
        }
    }

    /**
     * exception to catch if a user has tried to mark/unmark with an invalid taskList Index
     */
    public static class InvalidTaskListIndexException extends Exception {
        public InvalidTaskListIndexException(int indexUserTriedToAccess, int sizeOfList) {
            super("    OOPS!!! U tried to access index " + indexUserTriedToAccess + "\n    but the current list is of length " + sizeOfList);
        }
    }

    /**
     * exception to catch if a user has tried to mark/unmark with an invalid taskList Index
     */
    public static class didYouMeanToException extends Exception {
        public didYouMeanToException(String desiredCommand) {
            super("    OOPS!!! did you mean to type \'" + desiredCommand + "\'?");
        }
    }
}
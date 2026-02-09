public class SigmaExceptions{
    /**
     * exception to catch if a task type does not have correct arguments being passed in
     */
    public static class EmptyDescriptionException extends Exception{
        public EmptyDescriptionException(Task task) {
            super("    OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    /**
     * exception to catch if a user has entered an unknown command
     */
    public static class UnknownCommandException extends Exception{
        public UnknownCommandException(String userInput) {
            super("    OOPS!!! I'm sorry, but i dont know what " + userInput + " means.");
        }
    }
}
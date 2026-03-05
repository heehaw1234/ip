public class Parser {

    /**
     * Parses the user input into a command word and its arguments.
     *
     * @param userInput the full user input string
     * @return a String array where [0] is the command word and [1] is the arguments
     *         (may be empty)
     * @throws SigmaExceptions.DidYouMeanToException     if the input looks like a
     *                                                   known command with extra
     *                                                   chars
     * @throws SigmaExceptions.EmptyDescriptionException if a command is missing its
     *                                                   required arguments
     * @throws SigmaExceptions.UnknownCommandException   if the command is not
     *                                                   recognised
     */
    public static String[] parse(String userInput)
            throws SigmaExceptions.DidYouMeanToException,
            SigmaExceptions.EmptyDescriptionException,
            SigmaExceptions.UnknownCommandException {

        if (userInput.startsWith("bye")) {
            if (userInput.length() > 3) {
                throw new SigmaExceptions.DidYouMeanToException("bye");
            }
            return new String[] { "bye", "" };
        }

        if (userInput.startsWith("list")) {
            if (userInput.length() > 4) {
                throw new SigmaExceptions.DidYouMeanToException("list");
            }
            return new String[] { "list", "" };
        }

        if (userInput.startsWith("delete")) {
            if (userInput.length() <= 6) {
                throw new SigmaExceptions.EmptyDescriptionException("delete command");
            }
            return new String[] { "delete", userInput.substring(7) };
        }

        if (userInput.startsWith("mark")) {
            if (userInput.length() <= 4) {
                throw new SigmaExceptions.EmptyDescriptionException("mark command");
            }
            return new String[] { "mark", userInput.substring(5) };
        }

        if (userInput.startsWith("unmark")) {
            if (userInput.length() <= 6) {
                throw new SigmaExceptions.EmptyDescriptionException("unmark command");
            }
            return new String[] { "unmark", userInput.substring(7) };
        }

        if (userInput.startsWith("find")) {
            if (userInput.length() <= 4) {
                throw new SigmaExceptions.EmptyDescriptionException("find command");
            }
            return new String[] { "find", userInput.substring(5) };
        }

        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 4) {
                throw new SigmaExceptions.EmptyDescriptionException("todo");
            }
            return new String[] { "todo", userInput.substring(5) };
        }

        if (userInput.startsWith("deadline")) {
            if (userInput.length() <= 8) {
                throw new SigmaExceptions.EmptyDescriptionException("deadline");
            }
            return new String[] { "deadline", userInput.substring(9) };
        }

        if (userInput.startsWith("event")) {
            if (userInput.length() <= 5) {
                throw new SigmaExceptions.EmptyDescriptionException("event");
            }
            return new String[] { "event", userInput.substring(6) };
        }

        if (userInput.equals("sigma")) {
            return new String[] { "sigma", "" };
        }

        throw new SigmaExceptions.UnknownCommandException(userInput);
    }
}

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    /** The deadline date/time string. */
    protected String by;

    /**
     * Returns the type label for this task.
     *
     * @return "[D]" indicating a deadline task.
     */
    public String typeOf() {
        return "[" + "D" + "]";
    }

    /**
     * parses '/by' field entered by user for current deadline object
     */
    private void parseBy(String userInput) throws SigmaExceptions.TaskHasInvalidArgsException {
        int indexOfBy = userInput.indexOf("/by");

        if (indexOfBy == -1) {
            throw new SigmaExceptions.TaskHasInvalidArgsException("deadline");
        }

        by = userInput.substring(indexOfBy + 3).trim();
        super.description = userInput.substring(0, indexOfBy).trim();
    }

    /**
     * Constructs a Deadline task by parsing the user input for the /by field.
     *
     * @param userInput The user input string containing the description and /by
     *                  clause.
     * @param index     The index of this task in the task list.
     * @throws SigmaExceptions.TaskHasInvalidArgsException If the /by field is
     *                                                     missing.
     */
    public Deadline(String userInput, int index) throws SigmaExceptions.TaskHasInvalidArgsException {
        super(userInput, index);
        parseBy(userInput);
    }

    /**
     * Returns a string representation of this deadline task.
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description + " (by: " + by + ")";
    }
}

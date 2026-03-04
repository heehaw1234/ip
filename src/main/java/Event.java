/**
 * Represents a task that spans a time period (from-to).
 */
public class Event extends Task {

    /** The start date/time string. */
    protected String from;
    /** The end date/time string. */
    protected String to;

    /**
     * Returns the type label for this task.
     *
     * @return "[E]" indicating an event task.
     */
    public String typeOf() {
        return "[" + "E" + "]";
    }

    /**
     * parses '/from' and '/to' field entered by user for current event object
     */
    private void parseDescFromTo(String userInput) throws SigmaExceptions.TaskHasInvalidArgsException {
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");

        if (indexOfFrom == -1) {
            throw new SigmaExceptions.TaskHasInvalidArgsException("events");
        } else if (indexOfTo == -1) {
            throw new SigmaExceptions.TaskHasInvalidArgsException("events");
        } else if (indexOfTo < indexOfFrom) {
            throw new SigmaExceptions.TaskHasInvalidArgsException("events");
        }

        from = userInput.substring(indexOfFrom + 5, indexOfTo).trim();
        to = userInput.substring(indexOfTo + 3).trim();

        super.description = userInput.substring(0, indexOfFrom).trim();
    }

    /**
     * Constructs an Event task by parsing the user input for /from and /to fields.
     *
     * @param userInput The user input string containing the description, /from, and
     *                  /to clauses.
     * @param index     The index of this task in the task list.
     * @throws SigmaExceptions.TaskHasInvalidArgsException If /from or /to fields
     *                                                     are missing or invalid.
     */
    public Event(String userInput, int index) throws SigmaExceptions.TaskHasInvalidArgsException {
        super(userInput, index);
        parseDescFromTo(userInput);
    }

    /**
     * Returns a string representation of this event task.
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description + " (from: " + from + " to: " + to + ")";
    }
}

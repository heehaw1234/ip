public class Event extends Task {

    /**
     * start date of current event object
     */
    protected String from;
    /**
     * end date of current event object
     */
    protected String to;

    /**
     * returns the type of current subtask object
     */
    public String typeOf() {
        return "[" + "E" + "]";
    }

    /**
     * parses '/from' and '/to' field entered by user for current event object
     */
    private void parseDescFromTo(String userInput) throws SigmaExceptions.taskHasInvalidArgsException {
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");

        if (indexOfFrom == -1) {
            throw new SigmaExceptions.taskHasInvalidArgsException("events");
        } else if (indexOfTo == -1) {
            throw new SigmaExceptions.taskHasInvalidArgsException("events");
        } else if (indexOfTo < indexOfFrom) {
            throw new SigmaExceptions.taskHasInvalidArgsException("events");
        }

        from = userInput.substring(indexOfFrom + 5, indexOfTo).trim();
        to = userInput.substring(indexOfTo + 3).trim();

        super.description = userInput.substring(0, indexOfFrom).trim();
    }

    /**
     * constructor for event object
     */
    public Event(String userInput, int index) throws SigmaExceptions.taskHasInvalidArgsException {
        super(userInput, index);
        parseDescFromTo(userInput);
    }

    /**
     * toString method overriding
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description + " (from: " + from + " to: " + to + ")";
    }
}

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
    private void parseFromTo(String userInput) {
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");

        from = userInput.substring(indexOfFrom + 5, indexOfTo).trim();
        to = userInput.substring(indexOfTo + 3).trim();

        super.description = userInput.substring(0, indexOfFrom).trim();
    }

    /**
     * constructor for event object
     */
    public Event(String userInput, int index) {
        super(userInput, index);
        parseFromTo(userInput);
    }

    /**
     * toString method overriding
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description + " (from: " + from + " to: " + to + ")";
    }
}

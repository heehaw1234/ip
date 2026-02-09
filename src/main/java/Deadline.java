public class Deadline extends Task {

    /**
     * deadline of current deadline object
     */
    protected String by;

    /**
     * returns the type of current subtask object
     */
    public String typeOf() {
        return "[" + "D" + "]";
    }

    /**
     * parses '/by' field entered by user for current deadline object
     */
    private void parseBy(String userInput) throws SigmaExceptions.taskHasInvalidArgsException{
        int indexOfBy = userInput.indexOf("/by");

        if (indexOfBy == -1) {
            throw new SigmaExceptions.taskHasInvalidArgsException("deadline");
        }

        by = userInput.substring(indexOfBy + 3).trim();
        super.description = userInput.substring(0, indexOfBy).trim();
    }

    /**
     * constructor for deadline object
     */
    public Deadline(String userInput, int index) throws SigmaExceptions.taskHasInvalidArgsException {
        super(userInput, index);
        parseBy(userInput);
    }

    /**
     * toString method overriding
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description + " (by: " + by + ")";
    }
}

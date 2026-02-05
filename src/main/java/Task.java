public class Task {

    /** description of the task itself */
    protected String description;
    /** boolean flag to set if task has been marked 'X' or not */
    protected boolean isDone;
    /** index of current task in the list */
    protected int index;

    /** constructor to construct a new task */
    public Task(String description, int index) {
        this.description = description;
        this.isDone = false; // defaults to undone
        this.index = index;
    }

    /** returns the status of whether checkbox is marked or not */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** returns a string of the task with checkbox */
    public String returnMarkedString() {
        return "[" + getStatusIcon() + "] ";
    }

    /** sets isDone to true */
    public void markAsDone() {
        isDone = true;
    }

    /** sets isDone to false */
    public void markAsNotDone() {
        isDone = false;
    }

    /** toString method overriding */
    @Override
    public String toString() {
        return returnMarkedString() + description;
    }
}
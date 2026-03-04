/**
 * Represents a task with a description and completion status.
 */
public class Task {
    /** Description of the task. */
    protected String description;
    /** Whether the task has been marked as done. */
    protected boolean isDone;
    /** Index of the task in the list. */
    protected int index;

    /**
     * Constructs a new Task with the given description and index.
     *
     * @param description Description of the task.
     * @param index       Index of the task in the list.
     */
    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task's checkbox status.
     *
     * @return Checkbox string e.g. "[X] " or "[ ] ".
     */
    public String returnMarkedString() {
        return "[" + getStatusIcon() + "] ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task with its checkbox status.
     */
    @Override
    public String toString() {
        return returnMarkedString() + description;
    }
}
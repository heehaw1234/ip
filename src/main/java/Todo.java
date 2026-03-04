/**
 * Represents a simple to-do task without any date/time attached.
 */
public class Todo extends Task {

    /**
     * Returns the type label for this task.
     *
     * @return "[T]" indicating a to-do task.
     */
    public String typeOf() {
        return "[" + "T" + "]";
    }

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description Description of the to-do task.
     * @param index       Index of the task in the list.
     */
    public Todo(String description, int index) {
        super(description, index);
    }

    /**
     * Returns a string representation of this to-do task.
     */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description;
    }
}
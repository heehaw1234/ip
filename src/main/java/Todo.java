public class Todo extends Task {

    /** returns the type of current subtask object */
    public String TypeOf() {
        return "[" + "T" + "]";
    }

    /** constructor for todo object */
    public Todo(String description, int idx) {
        super(description, idx);
    }

    /** toString method overriding */
    @Override
    public String toString() {
        return TypeOf() + super.returnMarkedString() + super.description;
    }
}
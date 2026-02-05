public class Todo extends Task {

    /** returns the type of current subtask object */
    public String typeOf() {
        return "[" + "T" + "]";
    }

    /** constructor for todo object */
    public Todo(String description, int index) {
        super(description, index);
    }

    /** toString method overriding */
    @Override
    public String toString() {
        return typeOf() + super.returnMarkedString() + super.description;
    }
}
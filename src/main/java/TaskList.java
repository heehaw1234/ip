import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with pre-loaded tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given 0-based index.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the given 0-based index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the underlying task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks whose description contains the query string.
     * Returns the list of matching 0-based indices.
     */
    public ArrayList<Integer> findTasks(String query) {
        ArrayList<Integer> matchingIndices = new ArrayList<>();
        String lowerQuery = query.trim().toLowerCase();

        for (int i = 0; i < tasks.size(); i += 1) {
            String currTaskDesc = tasks.get(i).description.toLowerCase();
            if (!lowerQuery.isEmpty() && currTaskDesc.contains(lowerQuery)) {
                matchingIndices.add(i);
            }
        }

        return matchingIndices;
    }
}

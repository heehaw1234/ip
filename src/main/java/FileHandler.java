import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileHandler {

    private static final String FILE_PATH = "./data/sigma.txt";

    /**
     *
     * @param taskList
     * @param taskListHead
     *
     * saves Tasks from arrayList in Sigma.java
     * to the sigma.txt file
     * for persistent local storage
     */
    public static void saveTasks(ArrayList<Task> taskList, int taskListHead) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) { // if file path does not exist, create it
                dir.mkdirs();
            }

            FileWriter fw = new FileWriter(FILE_PATH); // create a new file at the file path
            for (int i = 0; i < taskListHead; i += 1) {
                Task t = taskList.get(i);
                String marked = t.isDone ? "1" : "0";

                if (t instanceof Event) {
                    Event e = (Event) t;
                    fw.write(i + " | " + TaskType.EVENT + " | " + marked + " | " + e.description + " | " + e.from + " | " + e.to);
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    fw.write(i + " | " + TaskType.DEADLINE + " | " + marked + " | " + d.description + " | " + d.by);
                } else if (t instanceof Todo) {
                    fw.write(i + " | " + TaskType.TODO + " | " + marked + " | " + t.description);
                } else {
                    fw.write(i + " | " + TaskType.TASK + " | " + marked + " | " + t.description);
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("    Warning: Could not save tasks to file.");
        }
    }

    /**
     *
     * @param taskList
     * @return
     *
     * loads tasks from the sigma.txt file
     * to the arrayList in Sigma.java
     * on programme startup
     */
    public static int loadTasks(ArrayList<Task> taskList) {
        int count = 0;
        try {
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file); // throws FileNotFoundException if file doesn't exist

            // read to the current arrayList
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                String taskType = parts[1];
                boolean isDone = parts[2].equals("1");
                String description = parts[3];

                Task task = null;
                switch (taskType) {
                case "TODO":
                    task = new Todo(description, count);
                    break;
                case "DEADLINE":
                    task = new Deadline(description + " /by " + parts[4], count);
                    break;
                case "EVENT":
                    task = new Event(description + " /from " + parts[4] + " /to " + parts[5], count);
                    break;
                default:
                    task = new Task(description, count);
                    break;
                }

                if (isDone) {
                    task.markAsDone();
                }
                taskList.add(task);
                count++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("    Save file not found. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("    Note: Could not load saved tasks. Starting fresh.");
        }

        return count;
    }
}

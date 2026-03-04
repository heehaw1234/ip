import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * Creates a Storage object with the given file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file and returns them as an ArrayList.
     *
     * @return ArrayList of tasks loaded from the file
     * @throws Exception if the file cannot be read or parsed
     */
    public ArrayList<Task> load() throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();
        int count = 0;

        File file = new File(filePath);
        Scanner s = new Scanner(file); // throws FileNotFoundException if file doesn't exist

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

        return taskList;
    }

    /**
     * Saves the given task list to the file.
     *
     * @param taskList the list of tasks to save
     */
    public void save(ArrayList<Task> taskList) {
        try {
            // ensure parent directory exists
            File file = new File(filePath);
            File dir = file.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i += 1) {
                Task t = taskList.get(i);
                String marked = t.isDone ? "1" : "0";

                if (t instanceof Event) {
                    Event e = (Event) t;
                    fw.write(i + " | " + TaskType.EVENT + " | " + marked + " | " + e.description + " | " + e.from
                            + " | " + e.to);
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
}

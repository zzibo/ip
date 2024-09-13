package hinlok.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hinlok.exceptions.HinlokException;
import hinlok.tasks.Deadline;
import hinlok.tasks.Event;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;
import hinlok.tasks.Todo;


/**
 * Represents a TaskFile that stores the task list when the user leaves
 */
public class TaskFile {
    private final String savedPath;

    /**
     * Constructor for TaskFile that contains a specific path to the file
     *
     * @param savedPath path to the file with all the saved data
     */
    public TaskFile(String savedPath) {
        this.savedPath = savedPath;
    }

    /**
     * Returns a ArrayList of Tasks stored in the file
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> loadTaskFromFile() {
        try {
            File file = new File(savedPath);
            if (!file.exists()) {
                boolean isDirCreated = file.getParentFile().mkdirs();
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("hinlok.file created");
                }
                if (isDirCreated) {
                    System.out.println("Dir created");
                }
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<Task> loadedList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                loadedList.add(readTask(line));
            }
            reader.close();
            return loadedList;
        } catch (Exception e) {
            System.out.println("An error occurred while loading hinlok.tasks: " + e.getMessage());
        }
        return null;
    }

    /**
     * Returns a task based on the string
     *
     * @param task task description
     * @return task
     * @throws Exception
     */
    public Task readTask(String task) throws Exception {
        String regex = "\\[(T|D|E)\\]\\[( |X)\\]\\s*(.*?)\\s*(\\(.*?\\))?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(task);
        if (!matcher.matches()) {
            throw new Exception("Your task description is in the wrong format");
        }
        String type = matcher.group(1);
        String status = matcher.group(2);
        String name = matcher.group(3);
        String extra = matcher.group(4);
        switch (type) {
        case "T":
            return processTodo(name, status);
        case "D":
            return processDeadline(name, status, extra);
        case "E":
            return processEvent(name, status, extra);
        default:
            throw new HinlokException("Unknown type in the saved file bro");
        }
    }

    /**
     * Writes into a txt file all the tasks in ArrayList
     *
     * @param taskList tasklist that needs to be saved
     */
    public void saveTasks(TaskList taskList) {
        try {
            File file = new File(savedPath);
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getAllTasks()) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving hinlok.tasks: " + e.getMessage());
        }
    }

    private Task processTodo(String name, String status) {
        return new Todo(name, status.equals("X"));
    }

    private Task processDeadline(String name, String status, String extra) throws HinlokException {
        String regexD = "\\(by: (.*?)\\)";
        Pattern patternD = Pattern.compile(regexD);
        Matcher matcherD = patternD.matcher(extra);
        if (!matcherD.find()) {
            throw new HinlokException("The format in the saved file is wrong bro");
        }
        String by = matcherD.group(1);
        LocalDate deadline = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Deadline(name, deadline, status.equals("X"));
    }

    private Task processEvent(String name, String status, String extra) throws HinlokException {
        String regexE = "\\(from: (.*?) to: (.*?)\\)";
        Pattern patternE = Pattern.compile(regexE);
        Matcher matcherE = patternE.matcher(extra);
        if (!matcherE.find()) {
            throw new HinlokException("your event format in saved file is wrong bro");
        }
        String from = matcherE.group(1);
        String to = matcherE.group(2);
        return new Event(name, from, to, status.equals("X"));
    }
}


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

public class TaskFile {
    private final String savedPath;
    public TaskFile(String savedPath){
        this.savedPath = savedPath;
    }

    public ArrayList<Task> loadTaskFromFile(){
        try {
            File file = new File(savedPath);
            if (!file.exists()) {
                boolean isDirCreated = file.getParentFile().mkdirs();
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("file created");
                }
                if(isDirCreated) {
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
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
        return null;
    }

    public Task readTask(String task) throws Exception {
        String regex = "\\[(T|D|E)\\]\\[( |X)\\]\\s*(.*?)\\s*(\\(.*?\\))?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String type = matcher.group(1);
            String status = matcher.group(2);
            String name = matcher.group(3);
            String extra = matcher.group(4);
            switch (type) {
                case "T":
                    System.out.println(task + " added");
                    return new Todo(name, status.equals("X"));

                case "D":
                    String regexD = "\\(by: (.*?)\\)";
                    Pattern patternD = Pattern.compile(regexD);
                    Matcher matcherD = patternD.matcher(extra);

                    if (matcherD.find()) {
                        String by = matcherD.group(1);
                        LocalDate deadline = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        System.out.println(task + " added");
                        return new Deadline(name, deadline, status.equals("X"));
                    } else {
                        System.out.println(task + " does not follow the format");
                    }
                    break;
                case "E":
                    String regexE = "\\(from: (.*?) to: (.*?)\\)";
                    Pattern patternE = Pattern.compile(regexE);
                    Matcher matcherE = patternE.matcher(extra);

                    if (matcherE.find()) {
                        String from = matcherE.group(1);
                        String to = matcherE.group(2);
                        System.out.println(task + " added");
                        return new Event(name, from, to, status.equals("X"));
                    } else {
                        System.out.println(task + " does not follow the format");
                    }
                    break;
                default:
                    System.out.println(task + " does not follow format");
                    break;
            }
        }else{
            throw new Exception("Your task description is in the wrong format");
        }
        return null;
    }

    public void saveTasks( ArrayList<Task> taskList){
        try {
            File file = new File(savedPath);
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}

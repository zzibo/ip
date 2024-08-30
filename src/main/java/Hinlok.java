import exceptions.HinlokException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import file.TaskFile;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Hinlok{
    private static ArrayList<Task> taskList;
    private static final String SAVED_PATH = "./data/hinlok.txt";
    private static TaskFile taskFile;

    public Hinlok(){
        taskFile = new TaskFile(SAVED_PATH);
        taskList = taskFile.loadTaskFromFile();
    }

    private String getNumberInList(){
        return "The task list has " + Hinlok.taskList.size() + " task currently";
    }
    private void addTodo(String taskDetails) throws HinlokException {
        if (taskDetails.trim().isEmpty()){
            throw new HinlokException("You did not give me a todo task bro");
        }
        taskList.add(new Todo(taskDetails, false));
        System.out.println(getNumberInList());
    }

    private void addDeadline(String taskDetails) throws HinlokException {
        String regex = "^(.*?) /by (\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()) {
            throw new HinlokException("your Tasks.Deadline format is wrong bro");
        } else {
                String[] deadlineDetails = taskDetails.split(" /by ", 2);
                String deadlineName = deadlineDetails[0];
                LocalDate date = LocalDate.parse(deadlineDetails[1]);
                taskList.add(new Deadline(deadlineName, date, false));
                System.out.println("Added a Tasks.Deadline: " + deadlineName);
                System.out.println(getNumberInList());
            }

        }

    private void addEvent(String taskDetails) throws HinlokException{
        String regex = "^(.*?)/from (.*?) /to (.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()){
            throw new HinlokException("your Tasks.Event format is wrong bro");
        } else {
            String[] eventDetails = taskDetails.split(" /from | /to ", 3);
            String eventName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            taskList.add(new Event(eventName, from, to, false));
            System.out.println("Added a Tasks.Event: " + eventName);
            System.out.println(getNumberInList());
        }
    }

    private void showTask(){
        for (int i = 0; i < taskList.size(); i++){
            System.out.println( (i+1) + "." + taskList.get(i).toString());
        }
    }

    private void markTask(int i){
        Task markedTask = taskList.get(i);
        markedTask.markAsDone();
        System.out.println("Roger sir, I marked this todo as done:");
        System.out.println(markedTask);
    }

    private void unmarkTask(int i){
        Task markedTask = taskList.get(i);
        markedTask.markAsUndone();
        System.out.println("Roger sir, I marked this task as undone:");
        System.out.println(markedTask);
    }

    private void deleteTask(int i){
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        System.out.println("Roger sir, I removed " + temp );
        System.out.println(getNumberInList());
    }

    public void findDeadlineByDate( String date ){
        System.out.println("These are the deadline due on that day:\n");
        for (Task task : taskList){
            if (task instanceof Deadline){
                System.out.println(((Deadline) task).getBy());
                if(date.equals(((Deadline) task).getBy())){
                    System.out.println(task);

                }
            }

        }
    }

    private void chat() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wassup, I am Hinlok and I love chinese songs. How can I help you today.\n" +
                "Type 'bye' to exit");
        while (true) {
            String reply = scanner.nextLine();

            if (reply.equalsIgnoreCase("bye")){
                taskFile.saveTasks(taskList);
                System.out.println("see ya");
                break;
            }
            else if(reply.equalsIgnoreCase("list")){
                showTask();
            }
            else if (!reply.contains(" ")) {
                System.out.println("What the sigma! I don't recognise this command");
            } else {

                String command = reply.split(" ", 2)[0];
                String taskDetails = reply.split(" ", 2)[1];
                try {
                    switch (command.toLowerCase()) {
                        case "mark":
                            int taskIndexMark = Integer.parseInt(reply.split(" ")[1]) - 1;
                            markTask(taskIndexMark);
                            break;

                        case "unmark":
                            int taskIndexUnmark = Integer.parseInt(reply.split(" ")[1]) - 1;
                            unmarkTask(taskIndexUnmark);
                            break;

                        case "todo":
                            addTodo(taskDetails);
                            break;

                        case "deadline":
                            addDeadline(taskDetails);
                            break;

                        case "event":
                            addEvent(taskDetails);
                            break;

                        case "delete":
                            int taskIndexDelete = Integer.parseInt(reply.split(" ")[1]) - 1;
                            deleteTask(taskIndexDelete);
                            break;

                        case "find":
                            findDeadlineByDate(taskDetails);
                            break;

                        default:
                            System.out.println("What the skibbidi are you talking about bro");
                            break;
                    }
                } catch (HinlokException e){
                    System.out.println(e.getMessage());
                }
            }

        }

    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok();
        hinlok.chat();
    }
}

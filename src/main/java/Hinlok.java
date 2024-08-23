import java.util.Scanner;
import java.util.ArrayList;

public class Hinlok{
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private void addTodo(String taskDetails) throws HinlokException {
        if (taskDetails.trim().isEmpty()){
            throw new HinlokException("You did not give me a todo task bro");
        }
        taskList.add(new Todo(taskDetails));
        System.out.println(getNumberInList());
    }

    private String getNumberInList(){
        return "The task list has " + Hinlok.taskList.size() + " task currently";
    }

    private void addDeadline(String taskDetails) throws HinlokException {
        if (!taskDetails.contains("/by")) {
            throw new HinlokException("your Deadline format is wrong bro");
        } else {
            String[] deadlineDetails = taskDetails.split(" /by ", 2);
            String deadlineName = deadlineDetails[0];
            String date = deadlineDetails[1];
            taskList.add(new Deadline(deadlineName, date));
            System.out.println("Added a Deadline: " + deadlineName);
            System.out.println(getNumberInList());
        }

    }

    private void addEvent(String taskDetails) throws HinlokException{
        if (!taskDetails.contains("/from") || !taskDetails.contains("/to")) {
            throw new HinlokException("your Event format is wrong bro");
        } else {
            String[] eventDetails = taskDetails.split(" /from | /to ", 3);
            String eventName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            taskList.add(new Event(eventName, from, to));
            System.out.println("Added a Event: " + eventName);
            System.out.println(getNumberInList());
        }
    }

    public void showTask(){
        for (int i = 0; i < taskList.size(); i++){
            System.out.println( (i+1) + "." + taskList.get(i).toString());
        }
    }

    public void markTask(int i){
        Task markedTask = taskList.get(i);
        markedTask.markAsDone();
        System.out.println("Roger sir, I marked this todo as done:");
        System.out.println(markedTask);
    }

    public void unmarkTask(int i){
        Task markedTask = taskList.get(i);
        markedTask.markAsUndone();
        System.out.println("Roger sir, I marked this task as undone:");
        System.out.println(markedTask);
    }

    public void deleteTask(int i){
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        System.out.println("Roger sir, I removed " + temp );
        System.out.println(getNumberInList());
    }



    public void chat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wassup, I am Hinlok and I love chinese songs. How can I help you today.\n" +
                "Type 'bye' to exit");
        while (true) {
            String reply = scanner.nextLine();

            if (reply.equalsIgnoreCase("bye")){
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
                            System.out.println("Added a Todo item: " + taskDetails);
                            break;

                        case "deadline":
                            addDeadline(taskDetails);
                            System.out.println("Added a Deadline item: " + taskDetails);
                            break;

                        case "event":
                            addEvent(taskDetails);
                            System.out.println("Added a Event item: " + taskDetails);
                            break;

                        case "delete":
                            int taskIndexDelete = Integer.parseInt(reply.split(" ")[1]) - 1;
                            deleteTask(taskIndexDelete);
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

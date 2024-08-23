import java.util.Scanner;
import java.util.ArrayList;

public class Hinlok{
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task taskItem){
        taskList.add(taskItem);
        System.out.println("Your task list has " + taskList.size() + " tasks currently!");
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
        System.out.println(markedTask.toString());
    }

    public void unmarkTask(int i){
        Task markedTask = taskList.get(i);
        markedTask.markAsUndone();
        System.out.println("Roger sir, I marked this task as undone:");
        System.out.println(markedTask.toString());
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
                System.out.println("What the sigma!");
            } else {

                String command = reply.split(" ", 2)[0];
                String taskDetails = reply.split(" ", 2)[1];

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
                        addTask(new Todo(taskDetails));
                        System.out.println("Added a Todo item: " + taskDetails);
                        break;

                    case "deadline":
                        if (!taskDetails.contains("/by")){
                            System.out.println("your format is wrong bro");
                        } else {
                            String[] deadlineDetails = taskDetails.split(" /by ", 2);
                            String deadlineName = deadlineDetails[0];
                            String date = deadlineDetails[1];
                            addTask(new Deadline(deadlineName, date));
                            System.out.println("Added a Deadline: " + deadlineName);
                        }
                        break;

                    case "event":
                        if (!taskDetails.contains("/from") || !taskDetails.contains("/to")){
                            System.out.println("your format is wrong bro");
                        } else {
                            String[] eventDetails = taskDetails.split(" /from | /to ", 3);
                            String eventName = eventDetails[0];
                            String from = eventDetails[1];
                            String to = eventDetails[2];
                            addTask(new Event(eventName, from, to));
                            System.out.println("Added a Event: " + eventName);
                        }
                        break;

                    default:
                        System.out.println("What the skibbidi are you talking about bro");
                        break;
                }
            }

        }

    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok();
        hinlok.chat();
    }
}

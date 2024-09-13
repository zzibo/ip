package hinlok.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hinlok.exceptions.HinlokException;

/**
 * Represents a task list that contains todo, deadline and event tasks
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }


    public int getSize() {
        return taskList.size();
    }

    public Task getTaskByIndex(int idx) {
        return taskList.get(idx);
    }

    public String getNumberInList() {
        return "The task list has " + taskList.size() + " task currently";
    }

    /**
     * Adds a new Todo object into the task list.
     *
     * @param taskDetails details of the task
     * @throws HinlokException exception when wrong inputs are given
     */
    public String addTodo(String taskDetails) throws HinlokException {
        if (taskDetails.trim().isEmpty()) {
            throw new HinlokException("You did not give me a todo task bro");
        }
        taskList.add(new Todo(taskDetails, false));
        return "Roger bro, I added a todo: " + taskDetails + "\n" + getNumberInList();
    }

    /**
     * Adds a new deadline object into task list.
     *
     * @param taskDetails details of the task
     * @throws HinlokException exception when wrong inputs are given
     */
    public String addDeadline(String taskDetails) throws HinlokException {
        String regex = "^(.*?) /by (\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()) {
            throw new HinlokException("your Deadline format is wrong bro");
        } else {
            String[] deadlineDetails = taskDetails.split(" /by ", 2);
            String deadlineName = deadlineDetails[0];
            LocalDate date = LocalDate.parse(deadlineDetails[1]);
            taskList.add(new Deadline(deadlineName, date, false));
            return "Roger bro, I added a Deadline: " + deadlineName + "\n" + getNumberInList();
        }

    }

    /**
     * Adds an Event object to task list.
     *
     * @param taskDetails details of the task
     * @throws HinlokException exception when wrong inputs are given
     */
    public String addEvent(String taskDetails) throws HinlokException {
        String regex = "^(.*?)/from (.*?) /to (.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()) {
            throw new HinlokException("your Event format is wrong bro");
        } else {
            String[] eventDetails = taskDetails.split(" /from | /to ", 3);
            String eventName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            taskList.add(new Event(eventName, from, to, false));
            return "Roger bro, I added a Event: " + eventName + "\n" + getNumberInList();
        }
    }

    /**
     * Shows all the task stored in the task list currently
     * @return All stored tasks
     */
    public String showTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < this.getSize(); i++) {
            listOfTasks.append((i + 1) + ". " + this.getTaskByIndex(i).toString() + "\n");
        }
        return listOfTasks.toString();
    }


    /**
     * Change the isDone of the task to true
     * @param i index of task to unmark
     * @return a String that confirms the task is marked
     */
    public String markTask(int i) {
        Task markedTask = taskList.get(i);
        markedTask.markAsDone();
        return "Roger bro, I marked this task as done:\n " + markedTask.getName();
    }

    /**
     * Change the isDone of the task to false
     * @param i index of task to mark
     * @return a String that confirms a String is unmarked
     */
    public String unmarkTask(int i) {
        Task markedTask = taskList.get(i);
        markedTask.markAsUndone();
        return "Roger bro, I marked this task as undone:\n " + markedTask.getName();
    }

    /**
     * Remove a task from the task list
     * @param i index of task to remove
     * @return a String that confirms the task is deleted
     */
    public String deleteTask(int i) {
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        return "Roger bro, I removed " + temp;
    }
}

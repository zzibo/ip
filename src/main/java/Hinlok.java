import java.util.Scanner;
import java.util.ArrayList;

public class Hinlok {
    private static final ArrayList<Todo> todoList = new ArrayList<>();

    private void addTodo(Todo todoItem){
        todoList.add(todoItem);
    }

    public void showTodo(){
        for (int i = 0; i < todoList.size(); i++){
            System.out.println( (i+1) + "." + todoList.get(i).showStatus());
        }
    }

    public void markTodo(int i){
        Todo markedTodo = todoList.get(i);
        markedTodo.markAsDone();
        System.out.println("Roger sir, I marked this todo as done:");
        System.out.println(markedTodo.showStatus());
    }

    public void unmarkTodo(int i){
        Todo markedTodo = todoList.get(i);
        markedTodo.markAsUndone();
        System.out.println("Roger sir, I marked this todo as undone:");
        System.out.println(markedTodo.showStatus());
    }



    public void chat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wassup, I am Hinlok and I love chinese songs. How can I help  you today.\n" +
                "Type 'bye' to exit");
        while (true) {
            String reply = scanner.nextLine();
            if (reply.equalsIgnoreCase("bye")){
                System.out.println("See ya");
                break;
            }
            else if (reply.equalsIgnoreCase("list")){
                showTodo();

            } else if (reply.startsWith("mark")) {
                int todoIndex = Integer.parseInt(reply.split(" ")[1]) - 1;
                markTodo(todoIndex);
                
            } else if (reply.startsWith("unmark")) {
                int todoIndex = Integer.parseInt(reply.split(" ")[1]) - 1;
                unmarkTodo(todoIndex);

            } else{
                addTodo(new Todo(reply));
                System.out.println("added:" + reply);
            }
        }

    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok();
        hinlok.chat();
    }
}

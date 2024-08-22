import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Hinlok {
    private static ArrayList<String> todo = new ArrayList<>();

    private void addTodo(String todoItem){
        todo.add(todoItem);
    }

    public void showTodo(){
        for (int i = 0; i < todo.size(); i++){
            System.out.println((i+1) + ". " + todo.get(i));
        }
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
            }
            else{
                addTodo(reply);
                System.out.println("added:" + reply);
            }
        }

    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok();
        hinlok.chat();
    }
}

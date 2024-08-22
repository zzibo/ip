import java.util.Scanner;

public class Hinlok {


    public static void chat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wassup, I am Hinlok and I love chinese songs. How can I help  you today.\nhi" +
                "Type 'bye' to exit");
        while (true) {
            String reply = scanner.nextLine();
            System.out.println(reply);
            if (reply.equalsIgnoreCase("bye")){
                System.out.println("See ya");
                break;
            }
        }

    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok();
        hinlok.chat();
    }
}

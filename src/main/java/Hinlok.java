import command.Command;
import exceptions.HinlokException;
import parser.Parser;
import tasks.TaskList;
import file.TaskFile;
import ui.Ui;

public class Hinlok {
    private Ui ui;
    private TaskList taskList;
    private TaskFile taskFile;

    public Hinlok(String savedFilePath) {
        ui = new Ui();
        taskFile = new TaskFile(savedFilePath);
        taskList = new TaskList(taskFile.loadTaskFromFile());
    }


    private void chat() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, taskFile);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        Hinlok hinlok = new Hinlok("./data/hinlok.txt");
        hinlok.chat();
    }
}

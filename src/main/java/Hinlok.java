import command.Command;
import exceptions.HinlokException;
import parser.Parser;
import tasks.*;
import file.TaskFile;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
            } catch (HinlokException e) {
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

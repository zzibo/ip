package hinlok;

import java.awt.*;
import java.io.IOException;

import hinlok.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Hinlok hinlok = new Hinlok("./data/hinlok.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHinlok(hinlok); // inject the Hinlok instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

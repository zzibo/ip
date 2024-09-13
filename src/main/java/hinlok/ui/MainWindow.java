package hinlok.ui;

import hinlok.Hinlok;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hinlok hinlok;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image hinlokImage = new Image(this.getClass().getResourceAsStream("/images/DaHinlok.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /** Injects the Duke instance */
    public void setHinlok(Hinlok d) {
        hinlok = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            exitChat("bye");
        } else {
            String response = hinlok.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getHinlokDialog(response, hinlokImage)
            );
        }
        userInput.clear();
    }

    public void exitChat(String input) {
        String exitMessage = hinlok.getResponse("bye");
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getHinlokDialog(exitMessage, hinlokImage));
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }

    public void showWelcomeMessage() {
        String welcome = "Wassup I'm Hinlok, your bro who loves chinese songs and takes care of all your task!";
        dialogContainer.getChildren().add(DialogBox.getHinlokDialog(welcome, hinlokImage));
    }
}

package hinlok.ui;

import java.util.Objects;

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

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private final Image hinlokImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaHinlok.png")));
    private final Image backgroundImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/background.png")));

    /**
     * Initialize the bot and display the MainWindow and show welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.setStyle("-fx-background-image: url('/images/background.png'); -fx-background-size: cover;");
        showWelcomeMessage();
    }

    /**
     * Injects the Hinlok instance
     * */
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
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(input, userImage)
            );
            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(event -> dialogContainer.getChildren().add(
                    DialogBox.getHinlokDialog(response, hinlokImage)
            ));
            pause.play();
        }
        userInput.clear();
    }

    /**
     * Exits the chatbot and close the MainWindow
     * @param input String input typed by the user
     */
    public void exitChat(String input) {
        String exitMessage = hinlok.getResponse("bye");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHinlokDialog(exitMessage, hinlokImage));
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }

    /**
     * Displays the welcome message of the chatbot
     */
    public void showWelcomeMessage() {
        String welcome = "Wassup I'm Hinlok, your bro who loves chinese songs and takes care of all your task!";
        dialogContainer.getChildren().add(DialogBox.getHinlokDialog(welcome, hinlokImage));
    }
}

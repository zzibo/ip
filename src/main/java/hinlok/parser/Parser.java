package hinlok.parser;

import hinlok.command.AddCommand;
import hinlok.command.Command;
import hinlok.command.DeleteCommand;
import hinlok.command.DetectDuplicateCommand;
import hinlok.command.ExitCommand;
import hinlok.command.FindCommand;
import hinlok.command.ListCommand;
import hinlok.command.MarkCommand;
import hinlok.command.UnmarkCommand;
import hinlok.exceptions.HinlokException;

/**
 * Represents a parser that return the correct Command based on input
 */
public class Parser {
    /**
     * Represents the types of Commands
     */
    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
    }

    /**
     * Returns a specific Command Object
     * based on the command given
     * If command is unknown, null is returned
     *
     * @param fullCommand Command given to Hinlok
     * @return Command
     * @throws HinlokException
     */
    public static Command parse(String fullCommand) throws HinlokException {
        String[] splitCommand = fullCommand.trim().split(" ", 2);

        if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (splitCommand.length <= 1) {
            throw new HinlokException("What the sigma, I don't recognise this command");
        }
        String command = splitCommand[0].toLowerCase();
        String taskDetails = splitCommand[1];

        switch (command.toLowerCase()) {
        case "mark":
            int taskIndexMark = Integer.parseInt(taskDetails) - 1;
            return new MarkCommand(taskIndexMark);
        case "unmark":
            int taskIndexUnmark = Integer.parseInt(taskDetails) - 1;
            return new UnmarkCommand(taskIndexUnmark);
        case "todo":
            return new AddCommand(taskDetails, CommandType.TODO);
        case "deadline":
            return new AddCommand(taskDetails, CommandType.DEADLINE);
        case "event":
            return new AddCommand(taskDetails, CommandType.EVENT);
        case "delete":
            int taskIndexDelete = Integer.parseInt(taskDetails) - 1;
            return new DeleteCommand(taskIndexDelete);
        case "find":
            return new FindCommand(taskDetails);
        case "duplicate":
            return new DetectDuplicateCommand(taskDetails);
        default:
            throw new HinlokException("What the skibbidi are you talking about bro");
        }
    }
}



package hinlok.parser;

import hinlok.command.*;
import hinlok.exceptions.HinlokException;

public class Parser {
    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    public static Command parse(String fullCommand) throws HinlokException {
        String[] splitCommand = fullCommand.trim().split(" ", 2);

        if(fullCommand.equalsIgnoreCase("list")){
            return new ListCommand();
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();

        } else if (splitCommand.length <= 1 ) {
            throw new HinlokException("What the sigma, I dont recognise this command");

        } else {
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

//                case "find":
//                    return new FindCommand(taskDetails, CommandType.FIND);

                default:
                    System.out.println("What the skibbidi are you talking about bro");
                    break;
        }
        return null;
        }
    }

}


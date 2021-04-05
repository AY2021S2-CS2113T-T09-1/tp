package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.MoveStorageCommand;
import seedu.duke.exceptions.CheckInException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidIdException;
import seedu.duke.exceptions.InvalidIntegerException;
import seedu.duke.exceptions.InvalidMaxCapacityException;
import seedu.duke.exceptions.InvalidNameFormatException;
import seedu.duke.exceptions.InvalidPhoneNumberException;
import seedu.duke.exceptions.NoArgumentPassedException;
import seedu.duke.exceptions.PersonNotFoundException;
import seedu.duke.exceptions.StorageOperationException;
import seedu.duke.exceptions.WrongFlagException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveStorageParserTest {

    Parser parser = new Parser();

    @Test
    public void parseMoveStorageCommand_testCommand() throws StorageOperationException, InvalidIntegerException,
            InvalidIdException, InvalidCommandException, InvalidPhoneNumberException,
            PersonNotFoundException, NoArgumentPassedException, WrongFlagException, InvalidNameFormatException,
            InvalidMaxCapacityException, CheckInException {
        MoveStorageCommand moveStorageCommand = (MoveStorageCommand) parser.parseCommand("movestorage abc");
        assertEquals("movestorage",moveStorageCommand.COMMAND);
    }
}

package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.EditMaxCommand;
import seedu.duke.common.Messages;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditMaxParserTest {

    Parser parser = new Parser();

    @Test
    public void parseEditMax_testCommand() throws StorageOperationException, InvalidIntegerException,
            InvalidIdException, InvalidCommandException, InvalidPhoneNumberException,
            PersonNotFoundException, NoArgumentPassedException, WrongFlagException,
            InvalidNameFormatException, InvalidMaxCapacityException, CheckInException {
        EditMaxCommand editMaxCommand = (EditMaxCommand) parser.parseCommand("editmax 1000");
        assertEquals("editmax",editMaxCommand.COMMAND);

        Throwable exception = assertThrows(InvalidIntegerException.class, () ->
                parser.parseCommand("editmax abc"));
        assertEquals(exception.getMessage(), Messages.INVALID_INTEGER);
    }
}

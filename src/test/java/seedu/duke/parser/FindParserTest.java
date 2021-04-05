package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.FindCommand;
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

public class FindParserTest {

    Parser parser = new Parser();

    @Test
    public void parseFind_testCommand() throws NoArgumentPassedException,

            WrongFlagException, InvalidCommandException, InvalidIdException,
            InvalidPhoneNumberException, InvalidNameFormatException,
            StorageOperationException, InvalidIntegerException, PersonNotFoundException, InvalidMaxCapacityException,
            CheckInException {

        parser.parseCommand("checkin i/123A n/John");
        FindCommand findCommand = (FindCommand) parser.parseCommand("find i/123A");
        assertEquals("find", findCommand.COMMAND);
    }

    @Test
    public void parseFindException_checker() {
        Throwable exception = assertThrows(WrongFlagException.class, () ->
                FindParser.parseFind(""));
        assertEquals(exception.getMessage(), Messages.WRONG_FLAG);
    }
}

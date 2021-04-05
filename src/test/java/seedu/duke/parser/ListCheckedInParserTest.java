package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.ListCheckedInCommand;
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

public class ListCheckedInParserTest {

    Parser parser = new Parser();

    @Test
    public void parseListCheckedIn_testCommand() throws NoArgumentPassedException,

            WrongFlagException, InvalidCommandException, InvalidIdException,
            InvalidPhoneNumberException, InvalidNameFormatException,
            StorageOperationException, InvalidIntegerException, PersonNotFoundException, InvalidMaxCapacityException,
            CheckInException {

        ListCheckedInCommand listCheckedInCommand = (ListCheckedInCommand) parser.parseCommand(
                "listcheckedin");
        assertEquals("listcheckedin", listCheckedInCommand.COMMAND);
    }
}

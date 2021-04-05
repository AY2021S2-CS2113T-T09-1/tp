package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.CheckoutCommand;
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

public class CheckOutParserTest {

    Parser parser = new Parser();

    @Test
    public void parseCheckOut_testCommand() throws NoArgumentPassedException,

            WrongFlagException, InvalidCommandException,
            InvalidIdException, InvalidPhoneNumberException, InvalidNameFormatException,
            StorageOperationException, InvalidIntegerException, PersonNotFoundException, InvalidMaxCapacityException,
            CheckInException {

        CheckoutCommand checkoutCommand = (CheckoutCommand) parser.parseCommand(
                "checkout n/John i/123A");
        assertEquals("checkout", checkoutCommand.COMMAND);
        checkoutCommand = (CheckoutCommand) parser.parseCommand(
                "checkout i/123A");
        assertEquals("checkout", checkoutCommand.COMMAND);
    }

    @Test
    public void parseCheckOut_Exceptions() {
        Throwable exception = assertThrows(NoArgumentPassedException.class, () ->
                CheckoutParser.parseCheckOut(""));
        assertEquals(exception.getMessage(), Messages.NO_ARGUMENT);

        exception = assertThrows(WrongFlagException.class, () ->
                CheckoutParser.parseCheckOut("j/ 123A"));
        assertEquals(exception.getMessage(), Messages.WRONG_FLAG);

        exception = assertThrows(InvalidIdException.class, () ->
                CheckoutParser.parseCheckOut("i/a23A"));
        assertEquals(exception.getMessage(), Messages.ID_ERROR);

        exception = assertThrows(NoArgumentPassedException.class, () ->
                CheckoutParser.parseCheckOut("i/"));
        assertEquals(exception.getMessage(), Messages.NO_ARGUMENT);


    }
}

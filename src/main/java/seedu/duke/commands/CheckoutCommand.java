package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.CheckOutException;
import seedu.duke.exceptions.HistoryStorageException;
import seedu.duke.exceptions.PersonNotFoundException;

import seedu.duke.location.Location;
import seedu.duke.person.Id;
import seedu.duke.person.Name;
import seedu.duke.person.Person;
import seedu.duke.person.TrackingList;
import seedu.duke.history.HistoryFile;

import java.util.logging.Logger;

/**
 * Check out a visitor.
 */
public class CheckoutCommand extends Command {

    public static final String COMMAND = "checkout";
    private final Location location = Duke.getInstance().getLocation();
    private static int CURRENT_CAPACITY;
    private static int MAXIMUM_CAPACITY;
    public static final String CURRENT_AND_MAXIMUM_MESSAGE = "Current capacity: %d out of %d";
    public static final String CHECKOUT_MESSAGE = "%s has been successfully checked-out!" + System.lineSeparator();
    private final Id id;
    private Person toCheckout;
    private HistoryFile historyFile;

    /**
     * Creates a CheckoutCommand to checkout a visitor.
     *
     * @param idString ID of the visitor who wants to check out
     */
    public CheckoutCommand(String idString) {
        this.id = new Id(idString);
        historyFile = Duke.getInstance().getHistoryFile();
    }

    public Person getToCheckout() {
        return toCheckout;
    }

    /**
     * Executes the CheckoutCommand.
     *
     * @param trackingList list of visitors
     * @return checkout message with the information about the current capacity in the venue
     * @throws PersonNotFoundException if the visitor cannot be found in the trackingList with the ID
     * @throws HistoryStorageException if there are problems saving into the file
     * @throws CheckOutException when trying to check out a visitor who has already checked out
     */
    @Override
    public CommandOutput execute(TrackingList trackingList) throws PersonNotFoundException, HistoryStorageException,
            CheckOutException {
        toCheckout = trackingList.findExactPerson(id);
        Name toCheckoutName = toCheckout.getName();
        if (!toCheckout.getCheckedIn()) {
            throw new CheckOutException(String.format(Messages.ALREADY_CHECKEDOUT, toCheckoutName));
        }
        if (toCheckout == null) {
            throw new PersonNotFoundException(Messages.PERSON_NOT_FOUND);
        }
        toCheckout.setCheckedIn(false);
        historyFile.saveToHistory(toCheckout, " checked out at ");
        MAXIMUM_CAPACITY = location.getMaxCapacity();
        CURRENT_CAPACITY = trackingList.getCurrentCapacity();
        return new CommandOutput(String.format(CHECKOUT_MESSAGE, toCheckoutName)
                + String.format(CURRENT_AND_MAXIMUM_MESSAGE, CURRENT_CAPACITY, MAXIMUM_CAPACITY), COMMAND);
    }

}

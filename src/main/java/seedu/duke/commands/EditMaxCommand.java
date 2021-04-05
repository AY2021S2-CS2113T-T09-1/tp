package seedu.duke.commands;

import com.sun.javafx.scene.shape.MeshHelper;
import seedu.duke.Duke;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvalidMaxCapacityException;
import seedu.duke.location.Location;
import seedu.duke.person.TrackingList;

/**
 * Set a new maximum capacity for the venue.
 */
public class EditMaxCommand extends Command {

    public static final String COMMAND = "editmax";
    private static final String NEW_CAPACITY_REGEX = "^[0-9]{1,6}$";
    public static final String EDIT_MAX_MESSAGE = "New max capacity: %d";
    private final int newMaxCapacity;
    private final Location location;

    /**
     * Creates a EditMax Command to edit the maximum capacity of the venue.
     *
     * @param newMaxCapacity new capacity limit provided by user
     * @throws InvalidMaxCapacityException if the new capacity provided does not match the requirements
     */
    public EditMaxCommand(String newMaxCapacity) throws InvalidMaxCapacityException {
        checkCapacityValidity(newMaxCapacity);
        this.newMaxCapacity = Integer.parseInt(newMaxCapacity);
        location = Duke.getInstance().getLocation();
    }

    /**
     * Checks if the new maximum capacity matches the requirements.
     *
     * @param newMaxCapacity new capacity limit provided by user
     * @throws InvalidMaxCapacityException if the new capacity provided does not match the requirements
     */
    private void checkCapacityValidity(String newMaxCapacity) throws InvalidMaxCapacityException {
        if (!newMaxCapacity.matches(NEW_CAPACITY_REGEX) || Integer.parseInt(newMaxCapacity) < 0) {
            throw new InvalidMaxCapacityException(Messages.INVALID_MAX_CAPACITY);
        }
    }

    /**
     * Executes the EditMaxCommand.
     *
     * @param trackingList list of visitors
     * @return a success message to user
     */
    @Override
    public CommandOutput execute(TrackingList trackingList) {
        location.setMaxCapacity(newMaxCapacity);
        return new CommandOutput(String.format(EDIT_MAX_MESSAGE, newMaxCapacity), COMMAND);
    }

}

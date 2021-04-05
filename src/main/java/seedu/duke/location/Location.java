package seedu.duke.location;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvalidArgumentSizeException;
import seedu.duke.exceptions.InvalidMaxCapacityException;

/**
 * Details of the venue for managing the crowd levels.
 */
public class Location {

    private int maxCapacity;
    private static final String MAX_CAPACITY_REGEX = "^[0-9]{1,6}$";

    /**
     * Creates a Location using the arguments the user had provided when running the jar file.
     *
     * @param args provided by the user when running the jar file. Contains the venue name and maximum capacity.
     * @throws InvalidMaxCapacityException if the maximum capacity provided does not meet the requirements
     */
    public Location(String[] args) throws InvalidMaxCapacityException, InvalidArgumentSizeException {
        checkArgumentValidity(args);
        this.maxCapacity = Integer.parseInt(args[0]);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Checks if the arguments entered by the user is valid and meets the requirements specified.
     *
     * @param args provided by the user when running the jar file. Contains the venue name and maximum capacity.
     * @throws InvalidMaxCapacityException if the maximum capacity provided does not meet the requirements
     */
    private void checkArgumentValidity(String[] args) throws InvalidMaxCapacityException, InvalidArgumentSizeException {
        if (args.length != 1) {
            throw new InvalidArgumentSizeException(Messages.INVALID_ARGUMENT_SIZE);
        }
        if (!args[0].matches(MAX_CAPACITY_REGEX) || Integer.parseInt(args[0]) < 0) {
            throw new InvalidMaxCapacityException(Messages.INVALID_MAX_CAPACITY_ARG);
        }
    }

}
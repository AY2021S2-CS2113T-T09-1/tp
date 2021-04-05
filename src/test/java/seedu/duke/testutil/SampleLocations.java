package seedu.duke.testutil;

import seedu.duke.exceptions.InvalidArgumentSizeException;
import seedu.duke.exceptions.InvalidMaxCapacityException;
import seedu.duke.exceptions.NoArgumentPassedException;
import seedu.duke.location.Location;

public class SampleLocations {

    public static Location SCHOOL;
    public static Location SMALL_SHOP;
    public static Location BIG_MALL;
    public static Location FOOD_COURT;
    public static Location CINEMA;
    public static Location EVENT;

    static {
        try {
            SCHOOL = new Location(new String[] {"1000"});
            SMALL_SHOP = new Location(new String[] {"25"});
            BIG_MALL = new Location(new String[] {"1000"});
            FOOD_COURT = new Location(new String[] {"50"});
            CINEMA = new Location(new String[] {"100"});
            EVENT = new Location(new String[] {"250"});
        } catch (InvalidMaxCapacityException | InvalidArgumentSizeException e) {
            e.printStackTrace();
        }
    }
}

package seedu.duke.location;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvalidArgumentSizeException;
import seedu.duke.exceptions.InvalidMaxCapacityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.testutil.SampleLocations.BIG_MALL;
import static seedu.duke.testutil.SampleLocations.CINEMA;
import static seedu.duke.testutil.SampleLocations.EVENT;
import static seedu.duke.testutil.SampleLocations.FOOD_COURT;
import static seedu.duke.testutil.SampleLocations.SCHOOL;
import static seedu.duke.testutil.SampleLocations.SMALL_SHOP;

public class LocationTest {

    @Test
    public void testInvalidArgumentSize() {
        Throwable exception = assertThrows(InvalidArgumentSizeException.class, () ->
                new Location(new String[]{"Location1"}));
        assertEquals(Messages.INVALID_ARGUMENT_SIZE, exception.getMessage());
    }

    @Test
    public void testInvalidMaxCapacity() {
        Throwable exception = assertThrows(InvalidMaxCapacityException.class, () ->
                new Location(new String[]{"Location2", "aaa123"}));
        assertEquals(Messages.INVALID_MAX_CAPACITY_ARG, exception.getMessage());
    }

    @Test
    public void testGetLocationName() {
        assertEquals("National University of Singapore", SCHOOL.getLocationName());
        assertNotEquals("Daiso", SMALL_SHOP.getLocationName());
    }

    @Test
    public void testSetLocationName() {
        String newLocationName = "Golden Village";
        CINEMA.setLocationName(newLocationName);
        assertEquals(newLocationName, CINEMA.getLocationName());
    }

    @Test
    public void testGetMaxCapacity() {
        assertEquals(250, EVENT.getMaxCapacity());
        assertNotEquals(1, BIG_MALL.getMaxCapacity());
    }

    @Test
    public void testSetMaxCapacity() {
        int newMaxCapacity = 30;
        FOOD_COURT.setMaxCapacity(newMaxCapacity);
        assertEquals(newMaxCapacity, FOOD_COURT.getMaxCapacity());
    }

}

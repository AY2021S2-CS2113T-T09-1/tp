package seedu.duke.commands;

import seedu.duke.person.TrackingList;

/**
 * Displays a list of visitors currently in the venue.
 */
public class ListCheckedInCommand extends Command {

    public static final String COMMAND = "listcheckedin";

    /**
     * Executes the ListCheckedInCommand.
     *
     * @param trackingList list of visitors
     * @return list of visitors who are currently in the venue
     */
    @Override
    public CommandOutput execute(TrackingList trackingList) {
        TrackingList checkedInList = new TrackingList();
        for (int i = 0; i < trackingList.getSize(); i++) {
            if (trackingList.getPerson(i).getCheckedIn()) {
                checkedInList.add(trackingList.getPerson(i));
            }
        }
        return new CommandOutput(checkedInList.listPerson(), COMMAND);
    }

}

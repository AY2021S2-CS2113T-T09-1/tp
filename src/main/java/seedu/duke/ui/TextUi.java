package seedu.duke.ui;

import seedu.duke.Duke;
import seedu.duke.commands.CommandOutput;
import seedu.duke.person.Person;
import java.util.logging.Logger;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TextUi {

    public static final String PRINT_LISTALL_FORMAT = "|%-3s||%-35s||%-8s||%-10s||%-10s|";
    public static final String PRINT_LIST_FORMAT =  "|%-3s||%-36s||%-12s||%-17s|";
    public static final String PHONE_NUMBER_FILLER = "--";
    public static final int DIVIDER_LENGTH = 76;

    private final PrintStream out;
    private final Scanner in;
    private static final Logger logger = Logger.getLogger(TextUi.class.getSimpleName());

    private static final String DIVIDER = "=".repeat(DIVIDER_LENGTH);
    private static final String SINGLE_DIVIDER = "-".repeat(DIVIDER_LENGTH);

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Does not return any object.
     * Output text to UI based on the type of commandOutput object passed in.
     * listall command and list command prints out a list of persons object.
     * find command prints out a person object.
     *
     * @param commandOutput command object containing data queried by user
     *                      and feedback to user.
     */
    public void printReaction(CommandOutput commandOutput) {
        String command = commandOutput.command;
        switch (command) {
        case "listall":
            assert commandOutput.persons != null : "Expect NoPersonFound Exception,";
            printList(commandOutput.persons);
            break;
        case "listcheckedin":
            assert commandOutput.persons != null : "Expect NoPersonFound Exception.";
            printCheckedInList(commandOutput.persons);
            break;
        case "find":
            assert commandOutput.person != null : "Expect NoPersonFound Exception.";
            printFindList(commandOutput.person);
            break;
        default:
            printDivider();
            out.println(commandOutput.messageToUser);
            printDivider();
        }
    }

    /**
     * Prints out a list of person objects with respective attributes such as
     * Name, Id, Phone and Checked In status.
     *
     * @param persons person object encapsulated in CommandOutput object.
     */
    private void printList(List<Person> persons) {
        assert persons != null : "Does not print null";
        printDivider();
        out.printf((PRINT_LISTALL_FORMAT) + "%n", " ", "Name", "Id", "Phone", "Checked In");
        printDivider();
        printPersonsInListWithStatus(persons);
        printDivider();
    }

    /**
     * Prints out a list of person objects with respective attributes such as
     * Name, Id and Phone.
     *
     * @param persons person object encapsulated in CommandOutput object.
     */
    private void printCheckedInList(List<Person> persons) {
        assert persons != null : "Does not print null";
        printDivider();
        out.printf((PRINT_LIST_FORMAT) + "%n", " ", "Name", "Id", "Phone");
        printDivider();
        printPersonsInListWithoutStatus(persons);
        int currentCapacity = getCurrentCapacity(persons);
        int maximumCapacity = getMaximumCapacity();
        int amountLeftToMaxCapacity = maximumCapacity - currentCapacity;
        System.out.println("Number of people left for max capacity: " + amountLeftToMaxCapacity);
        printDivider();
    }

    private int getMaximumCapacity() {
        return Duke.getInstance().getLocation().getMaxCapacity();
    }

    private int getCurrentCapacity(List<Person> persons) {
        int count = 0;
        for (Person p : persons) {
            if (p.getCheckedIn()) {
                count++;
            }
        }
        return count;
    }

    private void printPersonsInListWithoutStatus(List<Person> persons) {
        for (int i = 0; i < persons.size(); ++i) {
            Person peronSelected = persons.get(i);
            String name = peronSelected.getName().getNameString();
            String truncatedName = nameTruncator(name);
            String idString = peronSelected.getId().getIdString();
            String phoneString = (peronSelected.getPhone().isAvailable())
                    ? peronSelected.getPhone().getPhoneNo() : PHONE_NUMBER_FILLER;
            out.printf((PRINT_LIST_FORMAT) + "%n", i + 1, truncatedName, idString, phoneString);
            printSingleDivider();
        }
    }

    private void printFindList(Person person) {
        assert person != null : "Only prints Person";
        printDivider();
        out.printf((PRINT_LISTALL_FORMAT) + "%n", " ", "Name", "Id", "Phone", "Checked In");
        printDivider();
        String name = person.getName().getNameString();
        String truncatedName = nameTruncator(name);
        String idString = person.getId().getIdString();
        String phoneString = (person.getPhone().isAvailable())
                ? person.getPhone().getPhoneNo() : PHONE_NUMBER_FILLER;
        String statusString = (person.getCheckedIn()) ? "Yes" : "No";
        out.printf((PRINT_LISTALL_FORMAT) + "%n", 1, truncatedName, idString, phoneString, statusString);
        printDivider();

    }

    private void printPersonsInListWithStatus(List<Person> persons) {
        for (int i = 0; i < persons.size(); ++i) {
            Person personSelected = persons.get(i);
            String name = personSelected.getName().getNameString();
            String truncatedName = nameTruncator(name);
            String idString = personSelected.getId().getIdString();
            String phoneString = (personSelected.getPhone().isAvailable())
                    ? personSelected.getPhone().getPhoneNo() : PHONE_NUMBER_FILLER;
            String statusString = (personSelected.getCheckedIn()) ? "Yes" : "No";
            out.printf((PRINT_LISTALL_FORMAT) + "%n", i + 1, truncatedName, idString, phoneString, statusString);
            printSingleDivider();
        }
    }

    public void showWelcomeMessage(String version) {
        printDivider();
        assert version instanceof String : "Version has to be a string";
        out.println("Welcome to Control Your Crowd - Version " + version);
        // for next patch update - Storage class - dataStorageSuccessful()
        out.println("Data successfully loaded from storage file path.");
        printDivider();
    }

    private void printDivider() {
        out.println(DIVIDER);
    }

    private void printSingleDivider() {
        out.println(SINGLE_DIVIDER);
    }

    /**
     * Takes in user input from Scanner Object.
     *
     * @return String type of user input.
     */
    public String getUserInput() {
        String rawInput = in.nextLine();
        return rawInput;
    }

    /**
     * Takes in exception and print error message on UI.
     *
     * @param error Exception thrown from respective command.
     */
    public void notifyErrorToUser(Exception error) {
        printDivider();
        out.println(error.getMessage());
        printDivider();
    }

    private String nameTruncator(String longName) {
        if (longName.length() > 30) {
            return longName.substring(0,30);
        }
        return longName;
    }

}

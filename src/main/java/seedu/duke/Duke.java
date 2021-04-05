package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandOutput;
import seedu.duke.commands.ExitCommand;
import seedu.duke.exceptions.*;
import seedu.duke.history.HistoryFile;
import seedu.duke.location.Location;
import seedu.duke.parser.Parser;
import seedu.duke.person.PersonLog;
import seedu.duke.person.TrackingList;
import seedu.duke.storage.ConfigFile;
import seedu.duke.storage.StorageFile;
import seedu.duke.ui.TextUi;

public class Duke {

    private static final String VERSION_NO = "v2.0";

    private TextUi ui;
    private Parser parser;
    private StorageFile storage;
    private TrackingList trackingList;
    private Location location;
    private PersonLog personLog;
    private ConfigFile configFile;
    private HistoryFile historyFile;
    private static Duke theOnlyDuke = null;

    private Duke() {
    }

    public static Duke getInstance() {
        if (theOnlyDuke == null) {
            theOnlyDuke = new Duke();
        }
        return theOnlyDuke;
    }

    public static void main(String[] args) throws HistoryStorageException {
        getInstance().run(args);
    }

    public void run(String[] args) throws HistoryStorageException {
        start(args);
        runUntilExit();
        exit();
    }

    /** Prints Goodbye message then exists. */
    private void exit() throws HistoryStorageException {
        historyFile.endHistory();
        System.exit(0);
    }

    /** Main entry-point for the java.duke.Duke application. */
    private void start(String[] args) {
        ui = new TextUi();
        parser = new Parser();
        personLog = PersonLog.getInstance();
        try {
            location = new Location(args);
            historyFile = new HistoryFile();
            configFile = new ConfigFile();
            storage = new StorageFile(configFile.getStorageFilePath());
            trackingList = storage.load();
            personLog.loadAllPersons();
            historyFile.startHistory();
        } catch (StorageOperationException | HistoryStorageException e) {
            // Shut the program down as it can not be recovered
            // throw new RuntimeException();
            //PR testing for checks
            ui.notifyErrorToUser(e);
        } catch (InvalidMaxCapacityException | InvalidArgumentSizeException e) {
            ui.notifyErrorToUser(e);
            System.exit(0);
        }
        ui.showWelcomeMessage(VERSION_NO);
    }

    /** Reads the user command and executes it, until the user issues the exit command. */
    private void runUntilExit() {
        Command command = null;
        String userInput;
        do {
            userInput = ui.getUserInput();
            try {
                command = parser.parseCommand(userInput);
            } catch (InvalidCommandException | NoArgumentPassedException | WrongFlagException | InvalidIdException
                    | InvalidNameFormatException | InvalidPhoneNumberException | StorageOperationException
                    | InvalidIntegerException | PersonNotFoundException | InvalidMaxCapacityException e) {
                ui.notifyErrorToUser(e);
                continue;
            }

            CommandOutput commandOutput = null;
            try {
                commandOutput = command.execute(trackingList);
                storage.save(trackingList);
                personLog.saveAllPersons();
                ui.printReaction(commandOutput);
            } catch (PersonNotFoundException | StorageOperationException | HistoryStorageException
                    | CheckOutException e) {
                ui.notifyErrorToUser(e);
            }

        } while (!(command instanceof ExitCommand));
    }

    public Location getLocation() {
        return location;
    }

    public StorageFile getStorage() {
        return storage;
    }

    public void setStorage(StorageFile storage) {
        this.storage = storage;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public HistoryFile getHistoryFile() {
        return historyFile;
    }
}

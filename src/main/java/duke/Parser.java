package duke;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    /**
     * The Parser class handles text parsing for Duke.
     */
    //Exception messages
    public static final String EXCEPTION_EMPTY_FIELD = "Oops!You have to enter a task number. Please try again!";
    public static final String EXCEPTION_TIMEDEVENT_INTRO = "Oops! \nThe ";
    public static final String EXCEPTION_TIMEDEVENT_BODY =
            " command requires both description and time information in the format of: \n";
    public static final String EXCEPTION_TIMEDEVENT_DESCRIPTION = "[description] ";
    public static final String EXCEPTION_TIMEDEVENT_TIMEINFO = " [time information]";

    public static final String BLANK_SPACE = "";
    public static final String WHITESPACE_IDENTIFIER = " ";
    public static final String HYPHEN_IDENTIFIER = "-";
    public static final int DATE_FORMAT_SIZE = 3;
    public static final String DATE_FORMAT = "MMM d yyyy";

    /**
     * Removes the command passed into the method and replaces it with white space
     *
     * @params userInput  Original string typed by user and to be modified
     * @params commandToRemove  Command to be removed from string
     */
    public static String removeCommandFromInput(String userInput, String commandToRemove) {
        String modifiedUserInput = userInput.replace(commandToRemove, BLANK_SPACE);
        return modifiedUserInput.trim();
    }

    /**
     * Returns a string array with the task's description and additional information
     *
     * @params userInput Original string typed by user and to be modified
     * @params commandToRemove  Command to be removed from string
     * @params identifier  Identifier token to split the string with
     **/
    public static String[] determineTaskInformation(String userInput, String commandToRemove, String identifier) {
        String modifiedString = removeCommandFromInput(userInput, commandToRemove);
        return modifiedString.split(identifier);
    }

    /**
     * Checks for invalid command and throws DukeException.
     *
     * @param splitUserInput Original user typed string split by whitespace.
     * @throws DukeException Thrown when invalid inut length.
     */
    public static void checkForValidInput(String[] splitUserInput) throws DukeException {
        if (splitUserInput.length == 1) {
            throw new DukeException(EXCEPTION_EMPTY_FIELD);
        }
    }

    /**
     * Validates arguments for events with time descriptions.
     *
     * @param informationStrings Arguments split by whitespaces.
     * @param eventIdentifier Identifier to distinguish events and deadlines.
     */
    public static void checkForValidFieldEntered(String[] informationStrings, String command, String eventIdentifier)
            throws DukeException {
        boolean fieldsArePresent = true;

        //Check if both fields have been fulfilled
        for (String information : informationStrings) {
            if (information.equals(BLANK_SPACE)) {
                fieldsArePresent = false;
                break;
            }
        }

        //Check for valid information
        if (informationStrings.length < 2 || !fieldsArePresent) {
            String exceptionMessage = EXCEPTION_TIMEDEVENT_INTRO + command +
                    EXCEPTION_TIMEDEVENT_BODY +
                    EXCEPTION_TIMEDEVENT_DESCRIPTION + eventIdentifier + EXCEPTION_TIMEDEVENT_TIMEINFO;
            throw new DukeException(exceptionMessage);
        }
    }

    /**
     * Returns an arraylist containing the information to replace the date in the original string.
     *
     * @param timeInformation The original field entered by the user after the task identifier.
     */
    public static ArrayList<String> determineDateInformation(String timeInformation) {
        String[] splitTimeInformation = timeInformation.split(WHITESPACE_IDENTIFIER);
        ArrayList<String> replacementStrings = new ArrayList<>();

        //Check if substring contains 2 '-' to try to parse into date information
        for (String stringInformation : splitTimeInformation) {
            if (stringInformation.contains(HYPHEN_IDENTIFIER)) {
                if (checkForValidDateFormat(stringInformation)) {
                    try {
                        replacementStrings = formatDateInformation(stringInformation);
                    } catch (DateTimeParseException e) {
                        Ui.showInvalidDateFormatError();
                    }
                    break;
                }
            }
        }
        return replacementStrings;
    }

    /**
     * Formats date object into specified format and returns both original and formatted strings.
     *
     * @param stringInformation Date information in string form.
     */
    public static ArrayList<String> formatDateInformation(String stringInformation) {
        LocalDate taskDate;
        ArrayList<String> replacementStrings = new ArrayList<>();

        replacementStrings.add(stringInformation);
        taskDate = LocalDate.parse(stringInformation);
        String formattedDate = taskDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        replacementStrings.add(formattedDate);

        return replacementStrings;
    }

    /** Returns true when string contains 3 members separated by 2 hyphens */
    public static boolean checkForValidDateFormat(String stringInformation) {
        String[] splitDate = stringInformation.split(HYPHEN_IDENTIFIER);

        //Check for empty fields
        for (String subString : splitDate) {
            if (subString.equals(BLANK_SPACE)) {
                return false;
            }
        }
        //Check for only 3 inputs
        return splitDate.length == DATE_FORMAT_SIZE;
    }

    /** Splits string by white space and returns array of strings */
    public String[] divideUserCommand(String userInput) {
        return userInput.split(WHITESPACE_IDENTIFIER);
    }

    /** Determines command from string */
    public String determineCommand(String[] splitUserInput) {
        return splitUserInput[0];
    }
}

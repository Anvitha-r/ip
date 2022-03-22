public class InvalidCommandException extends Exception {
    // Used for when exception was not thrown with specified expected and received values
    private static final String empty_placeholder = "N.A.";

    /**
     * List of pre-defined error messages
     */
    protected static final String no_such_command_msg = "no such command";
    protected static final String todo_description_msg = "no description for todo command";
    protected static final String event_invalid_msg = "flag used for event command was invalid";
    protected static final String event_missing_flag_msg = "required flag for event command was not found";
    protected static final String deadline_invalid_msg = "flag used for deadline command was invalid";
    protected static final String deadline_missing_flag_msg = "required flag for deadline command was not found";

    private String expectedValue;
    private String receivedValue;

    public InvalidCommandException(String errorMsg) {
        super(errorMsg);
        this.expectedValue = empty_placeholder;
        this.receivedValue = empty_placeholder;
    }

    public InvalidCommandException(String errorMsg, String expectedValue, String receivedValue) {
        super(errorMsg);
        this.expectedValue = expectedValue;
        this.receivedValue = receivedValue;
    }

    //Accessor methods
    public String getExpectedValue() {
        return this.expectedValue;
    }

    public String getReceivedValue() {
        return this.receivedValue;
    }
}


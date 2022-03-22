package duke;

import duke.InvalidCommandException;

public class DukeExceptionHandler {

    // Allows SoraExceptionHandler to provide UI outputs to the user

    private DukeUI dukeUI;


    public DukeExceptionHandler(DukeUI soraUIObj) {

        this.dukeUI = soraUIObj;

    }

    public void handleInvalidCommandException(InvalidCommandException e) {

        String errorMessage = e.getMessage();

        switch (errorMessage) {

            case InvalidCommandException.no_such_command_msg:

                dukeUI.printCommandNotUnderstood();
                break;

            case InvalidCommandException.todo_description_msg:
                dukeUI.printTodoMissingDescription();

                break;

        }

    }

}
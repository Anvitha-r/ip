package duke;
import tasks.TasksManager;

import duke.InvalidCommandException;

public class Duke {
    private boolean userWantsToExit = false;
    TasksManager tasksManager;
    DukeUI dukeUI = new DukeUI();
    DukeReader dukeReader;
    private DukeExceptionHandler exceptionHandler;

    public Duke() {
        // Instantiate components
        dukeUI = new DukeUI();
        tasksManager = new TasksManager();
        dukeReader = new DukeReader();
        exceptionHandler = new DukeExceptionHandler(dukeUI);

        //greet the user
        dukeUI.printGreetings();


    }
    protected boolean doesUserWantsToExit() {
        return this.userWantsToExit;
    }

    protected void setUserWantsToExit() {
        this.userWantsToExit = true;
    }

    protected TasksManager getTasksManager() {
        return this.tasksManager;
    }

    public void startContinuousUserPrompt() {
        boolean isFirstPrompt = true;

        while (!doesUserWantsToExit()) {
            // Get user input
            dukeUI.printPrompter(isFirstPrompt);
            String userRawInput = dukeReader.getUserInput();
            isFirstPrompt = false;
            dukeUI.printLine();

            // Execute command
            executeCommand(userRawInput);
        }

        // Bid farewell to user
        dukeUI.printGoodbye();
    }

        private void executeCommand(String userRawInput) {
            String userCommand = extractCommand(userRawInput);

            try {
                switch (userCommand) {
                    case DukeUI.exit_command:
                        setUserWantsToExit();
                        break;
                    case DukeUI.list_command:
                        dukeUI.displayTaskList(getTasksManager());
                        break;
                    case DukeUI.mark_command:
                        int taskNum = getTaskNumberFromCommand(userRawInput);
                        boolean markSuccess = getTasksManager().updateDoneStatus(taskNum, true);
                        dukeUI.printMarkTaskResponseMessage(markSuccess, getTasksManager(), taskNum);
                        break;
                    case DukeUI.unmark_command:
                        taskNum = getTaskNumberFromCommand(userRawInput);
                        boolean unmarkSuccess = getTasksManager().updateDoneStatus(taskNum, false);
                        dukeUI.printUnmarkTaskResponseMessage(unmarkSuccess, getTasksManager(), taskNum);
                        break;
                    case DukeUI.todo_command:
                        // Fallthrough
                    case DukeUI.event_command:
                        // Fallthrough
                    case DukeUI.deadline_command:
                        boolean addSuccess = getTasksManager().addTask(userRawInput);
                        dukeUI.printAddTaskResponseMessage(addSuccess, getTasksManager());
                        break;
                    default:
                        throw new InvalidCommandException(InvalidCommandException.no_such_command_msg);
                }
            } catch (InvalidCommandException e) {
                // TODO: Create method to handle exception
                exceptionHandler.handleInvalidCommandException(e);
            }
        }

    private String extractCommand(String userRawInput) {
        String userCommand = userRawInput.toLowerCase().split(" ", 2)[0];
        return userCommand;
    }
    private int getTaskNumberFromCommand(String userRawInput) {
        int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
        return taskNum;
    }
    }

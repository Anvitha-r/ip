package tasks;
import duke.DukeUI;
import duke.InvalidCommandException;

import java.util.ArrayList;

public class TasksManager {
    private ArrayList<Task> list;
    private int numberOfTasks;

    public TasksManager() {
        this.list = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void incrementNumberOfTasks() {
        this.numberOfTasks += 1;
    }

    public boolean addTask(String text) throws InvalidCommandException {
        // Create new tasks.Task object with text
        boolean isTaskAdded;
        String taskType = extractTaskType(text);

        try {
            switch (taskType) {
                case DukeUI.todo_command:
                    checkTodoCommand(text);
                    String todoDescription = removeCommandKeyword(text);
                    isTaskAdded = list.add(new Todo(todoDescription));
                    break;
                case DukeUI.event_option_command:
                    checkEventCommand(text);
                    String[] eventDescriptionAndDate = extractDescriptionAndDate(text, DukeUI.event_option_command);
                    isTaskAdded = list.add(new Event(eventDescriptionAndDate));
                    break;
                case DukeUI.deadline_command:
                    checkDeadlineCommand(text);
                    String[] deadlineDescriptionAndDate = extractDescriptionAndDate(text, DukeUI.deadline_option_command);
                    isTaskAdded = list.add(new Deadline(deadlineDescriptionAndDate));
                    break;
                default:
                    isTaskAdded = false;
            }
        } catch (InvalidCommandException e) {
            // Rethrow it to caller method
            throw e;
        }


        if (isTaskAdded) {
            incrementNumberOfTasks();
            return true;
        }
        return false;
    }

    private void checkTodoCommand(String todoCommand) throws InvalidCommandException {
        // Check if command has a description
        String[] commandAndDescription = todoCommand.split(" ", 2);

        if (commandAndDescription.length < 2) {
            throw new InvalidCommandException(InvalidCommandException.todo_description_msg);
        }

        // Checks are complete, the command is okay. Method will return to caller.
    }

    private void checkEventCommand(String eventCommand) throws InvalidCommandException {
        // Check if user input has a description
        String[] splitByFlag = eventCommand.split("/at");
        String[] commandAndDescription = splitByFlag[0].split(" ", 2);
        if (commandAndDescription.length < 2) {
            // User input has no description
            throw new InvalidCommandException(InvalidCommandException.event_invalid_msg);
        }
        // Check if user input has the correct flag
        if (!eventCommand.contains("/at")) {
            throw new InvalidCommandException(InvalidCommandException.event_missing_flag_msg);
        }
        // Check if user input has a date period
        if (splitByFlag.length < 2) {
            // User input has no date period
            throw new InvalidCommandException(InvalidCommandException.event_missing_flag_msg);
        }
        // Checks are complete, the command is okay. Method will return to caller.
    }
    private void checkDeadlineCommand(String deadlineUserInput) throws InvalidCommandException {

    }


    private String extractTaskType(String text) {
        String taskType = text.toLowerCase().split(" ", 2)[0];
        return taskType;
    }

    private String removeCommandKeyword(String text) {
        String description = text.split(" ", 2)[1];
        return description;
    }

    private String[] extractDescriptionAndDate(String text, String optionKeyword) {
        String taskDescriptionAndDateString = removeCommandKeyword(text);
        String[] taskDescriptionAndDate = new String[2];
        for (int i = 0; i < taskDescriptionAndDate.length; i += 1) {
            taskDescriptionAndDate[i] = taskDescriptionAndDateString.split(optionKeyword)[i].trim();
        }
        return taskDescriptionAndDate;
    }

    public boolean updateDoneStatus(int taskNum, boolean status) {
        // Marks a task as either done or not done
        if (isEmpty()) {
            return false;
        }
        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;
        // Update the task status
        getList().get(indexNum).setDone(status);
        return true;
    }

    public Task deleteTask(int taskNum) {
        if (isEmpty()) {
            System.out.println("Please enter task");
        }
        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;
        // Remove the task from the list
        Task taskRemoved = getList().remove(indexNum);
        return taskRemoved;
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            System.out.println("please enter a task");
            return;
        }

        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayTask(Task taskObject) {
        System.out.println("\t" + taskObject.toString());
    }

    public void displayLastAddedTask() {
        if (isEmpty()) {
            System.out.println("Please enter a task");
            return;
        }
        int taskIndex = getNumberOfTasks() - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayAllTasks() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + getList().get(i).toString());
        }
    }
}
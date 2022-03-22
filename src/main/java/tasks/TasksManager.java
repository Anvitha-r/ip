package tasks;
import duke.DukeUI;
import duke.DukeReader;
import duke.InvalidCommandException;

import java.util.ArrayList;

public class TasksManager {
    public static final int FILE_DATA_TASK_TYPE_INDEX_NUM = 0;
    public static final int FILE_DATA_DESCRIPTION_INDEX_NUM = 2;


    private ArrayList<Task> list;
    private int numberOfTasks;
    DukeReader dukeReader;

    public TasksManager() {
        this.list = new ArrayList<Task>();
        this.numberOfTasks += 1;
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

    public Task addTask(String text) throws InvalidCommandException {
        // Create new tasks.Task object with text
        Task newTask = null;
        String taskType = extractTaskType(text);

        try {
            switch (taskType) {
                case DukeUI.todo_command:
                    checkTodoCommand(text);
                    String todoDescription = removeCommandKeyword(text);
                    newTask = new Todo(todoDescription);
                    list.add(newTask);
                    break;
                case DukeUI.event_option_command:
                    String[] eventDescriptionAndDate = extractDescriptionAndDate(text, DukeUI.event_option_command);
                    newTask = new Event(eventDescriptionAndDate);
                    list.add(newTask);
                    break;
                case DukeUI.deadline_command:
                    String[] deadlineDescriptionAndDate = extractDescriptionAndDate(text, DukeUI.deadline_option_command);
                    newTask = new Deadline(deadlineDescriptionAndDate);
                    list.add(newTask);
                    break;
                default:
            }
        } catch (InvalidCommandException e) {
            // Rethrow it to caller method
            throw e;
        }


        incrementNumberOfTasks();

        return newTask;
    }

    public void addTask(String[] taskDataFromFile) {
        String type;
        String description;
        String flag;
        String flagValue;
        String isDoneValue = taskDataFromFile[1];
        boolean isDone = (isDoneValue.equals("1")) ? true : false;

        // Get details of task
        type = getTaskType(taskDataFromFile[FILE_DATA_TASK_TYPE_INDEX_NUM]);
        description = taskDataFromFile[FILE_DATA_DESCRIPTION_INDEX_NUM];


        // Craft the command to add task to list
        StringBuilder commandBuilder = new StringBuilder(type + " " + description);


        String newTaskCommand = commandBuilder.toString();

        // Add the task to the list
        try {
            addTask(newTaskCommand);
        } catch (InvalidCommandException e) {
            System.out.println("Oh no! I failed to add a task from the saved data file to my task list.");
            System.out.println("Here's some details about the error: ");
            System.out.println("\t" + e.getMessage());

        }

        // If task is marked as done in the file, reflect it in Sora's task list
        if (isDone) {
            updateDoneStatus(getNumberOfTasks(), true);
        }
    }

    private String getTaskType(String abbreviation) {
        switch (abbreviation) {
            case DukeReader.TODO_TYPE_FILE_ABBREVIATION:
                return DukeUI.todo_command;
            case DukeReader.EVENT_TYPE_FILE_ABBREVIATION:
                return DukeUI.event_command;
            case DukeReader.DEADLINE_TYPE_FILE_ABBREVIATION:
                return DukeUI.deadline_command;
            default:
                return "";
        }
    }


    private void checkTodoCommand(String todoCommand) throws InvalidCommandException {
        // Check if command has a description
        String[] commandAndDescription = todoCommand.split(" ", 2);

        if (commandAndDescription.length < 2) {
            throw new InvalidCommandException(InvalidCommandException.todo_description_msg);
        }

        // Checks are complete, the command is okay. Method will return to caller.
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

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            return;
        }
        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayLastAddedTask() {
        if (isEmpty()) {
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
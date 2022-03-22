package duke;

import tasks.Task;
import tasks.TasksManager;

public class DukeUI {

    protected static final String logo
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    protected static final String prompt_symbol = ">";

    protected static final int line_length = 60;
    protected static final String line_char = "-";

    /**
     * List of command keywords
     */
    public static final String exit_command = "bye";

    public static final String list_command = "list";

    public static final String todo_command = "todo";
    public static final String event_command = "event";
    public static final String event_option_command = "/at";
    public static final String deadline_command = "deadline";
    public static final String deadline_option_command = "/by";
    public static final String mark_command = "mark";
    public static final String unmark_command = "unmark";
    public static final String delete_command = "delete";

    public static final String empty_list_response =
            "%s, Here's a list of tasks that you have given to me:\n";
    public static final String empty_list =
            "The list is empty at the moment.";
    public static final String add_task_success =
            "%s, I've added your new task to my list:\n";
    public static final String add_task_failed =
            "Oops sorry! Somehow I wasn't able to add your task to my list";
    public static final String mark_success =
            "%s, I've marked this task as done:\n";
    public static final String mark_failed =
            "Oops sorry, I couldn't mark that task as done.";
    public static final String unmark_success =
            "%s, I've marked this task as not done:\n";
    public static final String unmark_failed =
            "Oops sorry, I couldn't mark that task as not done.";
    protected static final String delete_success =
            "%s, I've deleted this task:\n";
    protected static final String delete_failed =
            "%s, Sorry I couldn't delete this task.";

    public static final String command_not_understood =
            "Oops! I can't understand what you've just typed...\nCould you try again?";
    public static final String todo_missing_parameters =
            "Hmmm, seems like you didn't give me a description for your\ntodo, please try again";



    protected void printMarkTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager, int taskNum) {
        if (isSuccessful) {
            //System.out.printf(DukeUI.mark_success);
            tasksManager.displayTask(taskNum);
            System.out.println();
            return;
        }
        // Mark task was unsuccessful
        System.out.println(DukeUI.mark_failed);
    }
    protected void printUnmarkTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager, int taskNum) {
        if (isSuccessful) {
            //System.out.printf(DukeUI.unmark_success);
            tasksManager.displayTask(taskNum);
            System.out.println();
            return;
        }
        // Unmark task was unsuccessful
        System.out.println(DukeUI.unmark_failed);
    }
    protected void printAddTaskResponseMessage(boolean isSuccessful, TasksManager tasksManager) {
        if (isSuccessful) {
            //System.out.printf(DukeUI.add_task_success);
            tasksManager.displayLastAddedTask();
            System.out.println();
            return;
        }
        // Adding task was unsuccessful
        System.out.println(DukeUI.add_task_failed);
    }

    protected void printDeleteTaskResponseMessage(Task taskRemoved, TasksManager tasksManager) {
        //System.out.printf(DukeUI.delete_success);
        System.out.println();
        tasksManager.displayTask(taskRemoved);
        System.out.println();
    }

    public static void displayTaskList(TasksManager tasksManager) {
        // Check if the task list is empty
        if (tasksManager.isEmpty()) {
            System.out.println("The list is empty at the moment...");
            return;
        }
        System.out.println("Here's a list of tasks that you have given to me: ");
        System.out.println();
        tasksManager.displayAllTasks();
    }

    protected void printLine() {
        // Prints a line based on the default parameters
        printLine(line_length, line_char);
    }

    /**
     * Prints a line on the console based on the arguments given to this method.
     */
    protected void printLine(int lineLen, String character) {
        // Prints a line based on the specified length and the character/symbol to use
        System.out.println(String.format("%" + lineLen + "s", " ").replaceAll(" ", character));
    }

    protected void printGreetings() {
        // Print banner
        System.out.println(logo);
        String fillerLine = "____________________________________________________________";

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(fillerLine);
    }
    protected void printGoodbye() {
        System.out.println("Bye!See you again soon");
        printLine();
    }

    protected void printPrompter(boolean isFirstPrompt) {
        if (isFirstPrompt) {
            System.out.print(prompt_symbol + " ");
        } else {
            printLine();
            System.out.println("What's next?");
            printLine();
            System.out.print(prompt_symbol + " ");
        }
    }

    protected void printCommandNotUnderstood() {
        System.out.println(DukeUI.command_not_understood);
    }

    protected void printTodoMissingDescription() {
        System.out.println(DukeUI.todo_missing_parameters);
    }

    public void printAddTaskResponseMessage(Task newTask) {
    }
}

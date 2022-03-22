package duke;

import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class DukeReader {
    private static final String DATA_FILE_PATH = "src/main/java/data.txt";
    private static final String DATA_FILENAME = "data.txt";

    private static final String FILE_DATA_SEPARATOR_REGEX = " \\| ";
    private static final String FILE_DATA_SEPARATOR = " | ";

    public static final String TODO_TYPE_FILE_ABBREVIATION = "T";
    public static final String EVENT_TYPE_FILE_ABBREVIATION = "E";
    public static final String DEADLINE_TYPE_FILE_ABBREVIATION = "D";

    protected String getUserInput() {
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
        return userInputTrimmed;
    }

    protected void loadTaskListFromFile(TasksManager tasksManager) throws IOException {
        File dataFile = new File(DATA_FILE_PATH + DATA_FILENAME);

        if (!dataFile.exists()) {
            System.out.println("File for storing task list data not found. Creating file...");
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error has occurred while trying to create a file.\nHere are the details:");
                System.out.println(e.getMessage());
                // Pass it to caller method to exit
                throw e;
            }

            // Since it's a newly created file, there's no data to read from it. Continue normal execution
            return;
        }

        // Read file data line by line
        Scanner fileReader = new Scanner(dataFile);

        while (fileReader.hasNext()) {
            // Read and parse the line of text from the file
            String rawLineData = fileReader.nextLine();
            String[] parsedLineData = parseFileLineData(rawLineData);

            // Add this line of text data into Sora's task list
            tasksManager.addTask(parsedLineData);
        }
    }

    private String[] parseFileLineData(String rawLineData) {
        String[] parsedLineData = rawLineData.split(FILE_DATA_SEPARATOR_REGEX);
        return parsedLineData;
    }

    public void writeNewTaskToFile(Task newTask) throws IOException {
        String taskInFileFormat = buildTaskTextForFile(newTask);

        // Add line of text to end of file
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE_PATH + DATA_FILENAME, true);
            fileWriter.append(taskInFileFormat + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error opening data file for writing. Here are some details:");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private String buildTaskTextForFile(Task newTask) {
        // Build line of text
        StringBuilder taskInFileFormat = new StringBuilder();
        String taskType = getTaskType(newTask);
        taskInFileFormat.append(taskType);
        taskInFileFormat.append(FILE_DATA_SEPARATOR);
        String isDoneValue = newTask.isDone() ? "1" : "0";
        taskInFileFormat.append(isDoneValue);
        taskInFileFormat.append(FILE_DATA_SEPARATOR);
        taskInFileFormat.append(newTask.getDescription());

        if (!taskType.equals(TODO_TYPE_FILE_ABBREVIATION)) {
            // Add date value into taskInFileFormat
            taskInFileFormat.append(FILE_DATA_SEPARATOR);
            taskInFileFormat.append(getDate(newTask));
        }
        return taskInFileFormat.toString();
    }

    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return TODO_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Event) {
            return EVENT_TYPE_FILE_ABBREVIATION;
        } else if (task instanceof Deadline) {
            return DEADLINE_TYPE_FILE_ABBREVIATION;
        }


        return "";
    }

    private String getDate(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getEventPeriod();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getDueDate();
        }


        return "";
    }

    // @param tasksManager

    public void rewriteAllTasksToFile(TasksManager tasksManager) throws IOException {
        ArrayList<Task> tasksList = tasksManager.getList();

        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE_PATH + DATA_FILENAME);

            for (Task task  : tasksList) {
                String taskText = buildTaskTextForFile(task);
                fileWriter.append(taskText + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error opening data file for writing. Here are the details:");
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
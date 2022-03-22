package duke;

import java.util.Scanner;

public class DukeReader {
    Scanner reader = new Scanner(System.in);

    protected String getUserInput() {
        String userInput = reader.nextLine();
        String userInputTrimmed = userInput.trim();
        return userInputTrimmed;
    }
}
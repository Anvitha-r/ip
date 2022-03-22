package duke;

import java.util.Scanner;

public class DukeReader {
    Scanner reader = new Scanner(System.in);

    protected String getUserInput() {
        return reader.nextLine();
    }
}
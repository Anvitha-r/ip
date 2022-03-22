package tasks;

public class Todo extends Task {

    private static final String todo_checkbox = "[T]";

    public Todo(String description) {
        super(description);

    }

    public String toString() {

        String checkbox = getCheckbox();

        return String.format("%s%s %s", todo_checkbox, checkbox, getDescription());

    }

}

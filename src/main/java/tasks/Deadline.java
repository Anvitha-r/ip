package tasks;

public class Deadline extends Task {
    private static final int deadline_description = 0;
    private static final int deadline_date = 1;
    private String dueDate;
    private static final String deadline_checkbox = "[D]";

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    public Deadline(String[] descriptionAndDate) {
        super(descriptionAndDate[deadline_description]);
        this.dueDate = descriptionAndDate[deadline_date];
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (due on: %s)", deadline_checkbox, checkbox, getDescription(), getDueDate());
    }
}
package tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String empty_checkbox = "[ ]";
    protected static final String marked_checkbox = "[X]";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    protected String getCheckbox() {
        return isDone() ? marked_checkbox : empty_checkbox;
    }

    public String toString() {
        String checkbox = getCheckbox();

        return String.format("%s %s", checkbox, getDescription());
    }
}
public class Event extends Task {
    private static final int event_description = 0;
    private static final int event_period = 1;
    private String eventPeriod;
    private static final String event_checkbox = "[E]";

    public Event(String[] descriptionAndPeriod) {
        super(descriptionAndPeriod[event_description]);
        this.eventPeriod = descriptionAndPeriod[event_period];
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", event_checkbox, checkbox, getDescription(), getEventPeriod());
    }
}
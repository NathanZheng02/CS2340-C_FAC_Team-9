import java.util.Date;

public class BasicTask extends Task {
    private Date dueDate;

    public BasicTask(String title, String description, String status, String priority, Date dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;

        this.dueDate = dueDate;
    }
}
import java.util.Date;

public class BasicTask extends Task {
    private Date dueDate;

    

    public BasicTask(String title, String description, String priority, Date dueDate) {
        this.title = title;
        this.description = description;
        this.status = "To Do";
        this.priority = priority;
        
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
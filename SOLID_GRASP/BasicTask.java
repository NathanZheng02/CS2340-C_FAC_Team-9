public class BasicTask extends Task {
    // private int dueDate;

    

    public BasicTask(String title, String description, String priority, int dueDate) {
        this.title = title;
        this.description = description;
        this.status = "To Do";
        this.priority = priority;
        
        this.dueDate = dueDate;
    }

    @Override
    void nextDay() {
        this.dueDate -= 1;
    }
}
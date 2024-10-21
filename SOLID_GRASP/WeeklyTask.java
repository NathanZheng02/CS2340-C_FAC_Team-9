public class WeeklyTask extends Task implements RepeatableTask {
    

    public WeeklyTask(String title, String description, String priority, int dueDate) {
        this.title = title;
        this.description = description;
        this.status = "To Do";
        this.priority = priority;
        this.dueDate = dueDate;

        
    }

    @Override
    public void rolloverTask() {
        if (dueDate == 0) {
            dueDate += 7;
        }
        
    }

    @Override
    void nextDay() {
        this.dueDate -= 1;
        if (dueDate == 0 && !(this.status.equals("Complete"))) {
            this.setStatus("Overdue");
        }
        this.rolloverTask();
    }
}
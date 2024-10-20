public class WeeklyTask extends Task implements RepeatableTask {
    private String rolloverDayOfWeek;

    public WeeklyTask(String title, String description, String status, String priority, String rolloverDayOfWeek) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        
        this.rolloverDayOfWeek = rolloverDayOfWeek;
    }

    @Override
    public void rolloverTask() {
        // TODO Auto-generated method stub
        
    }
}

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private int startDate;
    private int endDate;

    private final ArrayList<Task> projectTasks;
    private final ArrayList<TeamMember> projectMembers;

    public Project(String name, String description, int startDate, int endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

        this.projectTasks = new ArrayList<>();
        this.projectMembers = new ArrayList<>();
    }


    //methods
    public void addTask(Task task) {
        this.projectTasks.add(task);
    }

    public void removeTask(Task task) {
        this.projectTasks.remove(task);
    }

    public void addTeamMember(TeamMember member) {
        this.projectMembers.add(member);
    }

    public void removeTeamMember(TeamMember member) {
        this.projectMembers.remove(member);
    }

    public void nextDay() {
        for (Task task : this.projectTasks) {
            task.nextDay();
        }
    }




    //getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public List<Task> getProjectTasks() {
        return projectTasks;
    }

    public List<TeamMember> getProjectMembers() {
        return projectMembers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    
}
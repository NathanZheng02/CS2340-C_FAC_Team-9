public class TeamLeader extends TeamMember implements TeamMemberManager {
    private Project project;

    public TeamLeader(String name, String emailAddress, Project project) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.project = project;
    }
}
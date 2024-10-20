public class BasicMember extends TeamMember implements TeamMemberManager {

    public BasicMember(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    public void performResposibility() {
        System.out.println("My responsibility is to be a member of the team.");
    }

    public void joinProject(Project project) {
        project.addTeamMember(this);
    }

    public void leaveProject(Project project) {
        project.removeTeamMember(this);
    }
}

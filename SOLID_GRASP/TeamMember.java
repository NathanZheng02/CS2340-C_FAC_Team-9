//open-closed principle
public abstract class TeamMember {
    protected String name;
    protected String emailAddress;

    public void joinProject(Project project) {
        project.addTeamMember(this);
    }

    public void leaveProject(Project project) {
        project.removeTeamMember(this);
    }

    abstract void performResposibility();
}




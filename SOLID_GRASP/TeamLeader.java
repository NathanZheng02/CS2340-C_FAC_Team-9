public class TeamLeader extends TeamMember {
    

    public TeamLeader(String name, String emailAddress, Project project) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    void performResposibility() {
        System.out.println("My responsibility is to lead the team.");
    }
}
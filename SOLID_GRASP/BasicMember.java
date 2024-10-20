public class BasicMember extends TeamMember {

    public BasicMember(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    public void performResposibility() {
        System.out.println("My responsibility is to be a member of the team.");
    }

    
}

import java.util.logging.Level; 
import java.util.logging.Logger; 

public class BasicMember extends TeamMember {

    static final Logger logger = Logger.getLogger(BasicMember.class.getName());

    public BasicMember(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    void performResposibility() {
        logger.log(Level.INFO, "My responsibility is to be a member of the team.");
    } 
}
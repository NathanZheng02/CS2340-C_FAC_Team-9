import java.util.logging.Level; 
import java.util.logging.Logger; 

public class TeamLeader extends TeamMember {
    
    static final Logger logger = Logger.getLogger(TeamLeader.class.getName());


    public TeamLeader(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    void performResposibility() {
        logger.log(Level.INFO, "My responsibility is to lead the team.");
    }
}
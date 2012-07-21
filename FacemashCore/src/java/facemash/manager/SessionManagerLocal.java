package facemash.manager;

import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for SessionManager EJB.
 * @author Danon
 */

@Local
public interface SessionManagerLocal {
    
    void addSession(String sessionId);
    void removeSession(String session);
    void removeAll();
    
    List<String> getSessions();
    
}

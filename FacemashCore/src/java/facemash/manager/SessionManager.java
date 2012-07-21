package facemash.manager;

import com.sun.xml.ws.api.tx.at.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Singleton;

/**
 * A simple implementation of session container EJB. It is a Singleton EJB which
 * stores all active user session.
 *
 * @author Danon
 */
@Singleton
public class SessionManager implements SessionManagerLocal {

    private final List<String> sessions = Collections.synchronizedList(new ArrayList<String>());

    @Override
    public void addSession(String session) {
        sessions.add(session);
        System.out.println("addSession(): Session added. Online: " + sessions.size());

    }

    @Override
    public void removeSession(String session) {
        sessions.remove(session);
        System.out.println("removeSession(): Session removed. Online: " + sessions.size());
    }

    @Override
    public void removeAll() {
        sessions.clear();
        System.out.println("removeAll(): Cleared. Online: " + sessions.size());
    }

    @Override
    @Transactional(value = Transactional.TransactionFlowType.SUPPORTS)
    public List<String> getSessions() {
        System.out.println(">> getSessions() returns " + sessions);
        return sessions;
    }
}

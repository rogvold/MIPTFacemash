package web.utils;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.ejbLinkType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danon
 */
public class SessionUtils {

    public static boolean isSignedIn() {
        if (SessionListener.getSessionAttribute("user", false) != null) {
            return true;
        }
        return false;
    }

    public static String getVkId() {
        String u = ((String) SessionListener.getSessionAttribute("user", true));
        if (u != null) {
            return u.toString();
        }
        return null;
    }

}

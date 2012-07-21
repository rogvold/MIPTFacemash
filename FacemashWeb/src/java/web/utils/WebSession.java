package web.utils;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import org.apache.log4j.Logger;
import web.utils.SessionListener;
import web.utils.SessionUtils;

/**
 *
 * @author Danon and Sabir
 */
@ManagedBean
@SessionScoped
public class WebSession implements Serializable {

    public static final String MODERATOR_VK_ID = "id8208583";
    public static final String ADMINISTRATOR_VK_ID = "id15181992";
    private transient HttpSession session = null;

    public boolean isSignedIn() {
        boolean b = SessionUtils.isSignedIn();
        System.out.println("isSignedId = " + b);
        return b;

    }

    public String getVkId() {
        return SessionUtils.getVkId();
    }

    public int getGroup() {
        int a = 0;
        if (isSignedIn() == false) {
            return 0;
        } else {
            String u = (String) SessionListener.getSessionAttribute("user", true);
            if (u != null) {
                System.out.println("user = " + u);
                if (u.equals(ADMINISTRATOR_VK_ID) || (u.equals(MODERATOR_VK_ID))) {
                    
                    a = 1;
                }
            }
        }
        return a;
    }

    public void resetSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession mysession = (HttpSession) fc.getExternalContext().getSession(true);
        mysession.invalidate();
        fc.getExternalContext().getSession(true);
    }
}

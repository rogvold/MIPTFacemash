/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;



import web.utils.SessionListener;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rogvol
 */


@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private transient HttpSession session = null;
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void simpleLogin() { // hardcode)) 
        if (secret.equals("sabirmipt")) {
            System.out.println("secret is right! ");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            session = (HttpSession) facesContext.getExternalContext().getSession(true);
            setSessionAttribute(session, "loggedIn", true);
        } else {
            System.out.println("incorrect secret");
        }

    }

    public static Object getSessionAttribute(HttpSession session, String attrName) {
        if (isSessionValid(session)) {
            try {
                return session.getAttribute(attrName);
            } catch (IllegalStateException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isOnline() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if (getSessionAttribute(session, "loggedIn") == null) {
            return false;
        }

        return true;
    }

    public static boolean isSessionValid(HttpSession session) {
        if (session == null) {
            return false;
        }
        try {
            session.getCreationTime();
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }

    public int getAmountOfPeopleOnSite() {
        try {
            int t =  SessionListener.getOnlineAmount();
            System.out.println("online am = " + t);
            return t;
        } catch (Exception e) {
            return 0;
        }
        
    }

    private void setSessionAttribute(HttpSession session, String attrName, Object value) {
        try {
            if (session == null || !isSessionValid(session)) {
                return;
            }
            session.setAttribute(attrName, value);
        } catch (Exception ex) {
            System.err.println("WARN: setSessionAttribute() failed!\n" + ex);
        }
    }
}

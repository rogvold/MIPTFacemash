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

//    public static User loginByCookies(Cookie cookies[]) {
//        String l = null, p = null, e = null;
//        if (cookies == null) {
//            return null;
//        }
//        for (Cookie cookie : cookies) {
////            switch (cookie.getName()) {
////                case "l":
////                    l = cookie.getValue();
////                    break;
////                case "p":
////                    p = cookie.getValue();
////                    break;
////                case "e":
////                    e = cookie.getValue();
////                    break;
////            }
//            if ("l".equals(cookie.getName())) {
//                l = cookie.getValue();
//            }
//            if ("p".equals(cookie.getName())) {
//                p = cookie.getValue();
//            }
//            if ("e".equals(cookie.getName())) {
//                e = cookie.getValue();
//            }
//        }
//        System.out.println("Cookies: l=" + l + "; p=" + p + "; e=" + e);
//        if (e == null || "true".equalsIgnoreCase(e)) {
//            return um.logInByEmailMD5(l, p);
//        } else {
//            return um.logInMD5(l, p);
//        }
//    }
}

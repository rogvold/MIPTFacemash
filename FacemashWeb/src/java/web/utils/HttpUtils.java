package web.view;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rogvold
 */
public class HttpUtils {

    public static String getRequestParam(String name) {
        String result = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            return null;
        }
        ExternalContext ec = fc.getExternalContext();
        if (ec == null) {
            return null;
        }
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        if (request != null) {
            result = request.getParameter(name);
        }
        return result;
    }

    public static String getRequestUrl() {
        String result = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            return null;
        }
        ExternalContext ec = fc.getExternalContext();
        if (ec == null) {
            return null;
        }
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        if (request != null) {
            result = request.getRequestURI();
        }
        return result;
    }
}

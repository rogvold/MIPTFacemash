/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.utils.SessionListener;
import web.utils.SessionUtils;
import web.utils.WebSession;

/**
 *
 * @author rogvold
 */
public class authorisation extends HttpServlet {

    private transient HttpSession session = null;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String viewer_id = request.getParameter("viewer_id");
            String api_id = request.getParameter("api_id");
            String api_secret = "9WiqiUbaBTAUzp8HiJxV";
            String auth_key = getMD5(api_id + "_" + viewer_id + "_" + api_secret);
            String s = "index.xhtml";
            if (auth_key.equals(request.getParameter("auth_key")) == false) {
                System.out.println("MD5(api_id+_+viewer+_+api_secret) and auth_key are not equal");
                System.out.println("viewer_id = " + viewer_id);
                System.out.println("api_id = " + api_id);
                System.out.println("api_secret = " + api_secret);
                System.out.println("auth_key should be " + auth_key);
                System.out.println("received auth_key = " + request.getParameter("auth_key"));
                if (!SessionUtils.isSignedIn()) { // TODO : erase this block
                    System.out.println("if not signed in. pequest is not from vk probably");
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    session = (HttpSession) facesContext.getExternalContext().getSession(true);
                    SessionListener.setSessionAttribute(session, "user", "notVK");
                    response.sendRedirect(s);
                    return;
                }
            }
            if (!SessionUtils.isSignedIn()) {
                System.out.println("if not signed in. trying to make authorisation");
                System.out.println("viewer_id = " + viewer_id);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                session = (HttpSession) facesContext.getExternalContext().getSession(true);
                SessionListener.setSessionAttribute(session, "user", "id" + viewer_id);

                if (WebSession.ADMINISTRATOR_VK_ID.equals("id" + viewer_id) || WebSession.MODERATOR_VK_ID.equals("id" + viewer_id)) {
                    SessionListener.setSessionAttribute(session, "loggedIn", true);
                }
                response.sendRedirect(s);
            } else {
                response.sendRedirect(s);
            }




        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte byteData[] = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception exc) {
        }

        return "";
    }
//    private User openIdAuthorisation(String uid) {
//        Map<String, String> map = new HashMap();
//        map.put("vkontakte", uid);
//        User user = userMan.openIdAuthorisation(map);
//        System.out.println("VK Iframe: user = " + user);
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        session = (HttpSession) facesContext.getExternalContext().getSession(true);
//        SessionListener.setSessionAttribute(session, "user", user);
//        return user;
//    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view.servlet;

import facemash.manager.GirlManagerLocal;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rogvold
 */
public class picture extends HttpServlet {

    @EJB
    GirlManagerLocal gm;

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
        OutputStream out = response.getOutputStream();
//        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code. //
             */
//            FileInputStream i = null;
//            String img_src = gm.getGirlById(Long.parseLong(request.getParameter("id"))).getImg();
//            
//            System.out.println("img_src = " + img_src);
//            i = new FileInputStream(new File("facemash/" + img_src));
//
//            response.setContentType("image/jpeg");
//            response.setHeader("Content-Disposition", "attachment; filename=\""+img_src+"\"");
//            
//            byte bites[] = new byte[5000];
//            int r = 0;
//            while (true) {
//                r = i.read(bites);
//                if (r <= 0) {
//                    break;
//                }
//                out.write(bites, 0, r);
//            }
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet picture</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet picture at " + request.getContextPath() + "</h1>");
//            out.println("id = " + request.getParameter("id"));
//            out.println("</body>");
//            out.println("</html>");
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

        response.setContentType("image/jpeg");
        String img_src = gm.getGirlById(Long.parseLong(request.getParameter("id"))).getImg();
        System.out.println("servlet. id = "+ request.getParameter("id"));

        File f = new File("facemash/" + img_src);
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
//        processRequest(request, response);




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
}

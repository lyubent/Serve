/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Model.HeaderAssembler;
import PageProcessing.CV;
import PageProcessing.CoverLetter;
import PageProcessing.Education;
import PageProcessing.Index;
import PageProcessing.Interest;
import PageProcessing.Work;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnManager;

/**
 *
 * @author danieldeak
 */
@WebServlet(name="Diary", urlPatterns={"/Index"})
public class Diary extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        // Servlet Stuff
        ServletContext context = getServletContext();
        RequestDispatcher view;

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.setAttribute("user", "ddeak"); // replace when user Login is implemented


        String page = "";

        if (request.getParameter("page") != null)
            page = request.getParameter("page");

        if (page.equals(""))
        {
            request = Index.processIndexRequest(request, context.getContextPath());
            //response = Index.processIndexResponse(response)

            view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
        else if (page.equals("CV"))
        {
            request = CV.processCVRequest(request, context.getContextPath());

            view = request.getRequestDispatcher("CV.jsp");
            view.forward(request, response);
        }
        else if (page.equals("CoverLetter"))
        {
            request = CoverLetter.processServletRequest(request);
            
            view = request.getRequestDispatcher("/jsp/CoverLetter.jsp");
            view.forward(request, response);
        }
        else if (page.equals("Education"))
        {
//            request = Education.processRequest(request);

            view = request.getRequestDispatcher("/jsp/Education.jsp");
            view.forward(request, response);
        }
        else if (page.equals("Work"))
        {
            view = request.getRequestDispatcher("/jsp/WorkExperience.jsp");
            view.forward(request, response);
        }
        else if (page.equals("Interests"))
        {
            view = request.getRequestDispatcher("/jsp/Interests.jsp");
            view.forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        // Servlet Stuff
        ServletContext context = getServletContext();

        // set user
        HttpSession session = request.getSession();
        session.setAttribute("user", "ddeak"); // TODO replace when user login is implemented

        // Writer for AJAX calls
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String page = "";

        if (request.getParameter("page") != null)
            page = request.getParameter("page");

        if (page.equals(""))
        {

        }
        else if (page.equals("CV"))
        {
            // Default page specific
            request.setAttribute("css", context.getContextPath() + "/css/CV.css");


            List<String> jsFiles = new ArrayList<String>();
            jsFiles.add(context.getContextPath() + "/js/CV.js");

            request.setAttribute("js", jsFiles);

            RequestDispatcher view = request.getRequestDispatcher("CV.jsp");
            view.forward(request, response);
        }
        else if (page.equals("saveCoverLetter"))
        {
            CoverLetter.saveLetter(request);
        }
        else if (page.equals("loadCoverLetter"))
        {
            out.println(CoverLetter.loadLetter(request));
        }
        else if (page.equals("loadEducation"))
        {
            out.println(Education.loadEducation(request));
        }
        else if (page.equals("saveEducation"))
        {
            Education.saveEducation(request);
        }
        else if (page.equals("loadWork"))
        {
            out.println(Work.loadWork(request));
        }
        else if (page.equals("saveWork"))
        {
            Work.saveWork(request);
        }
        else if (page.equals("saveInterests"))
        {
            Interest.saveInterests(request);
        }
        else if (page.equals("loadInterests"))
        {
            out.println(Interest.loadInterests(request));
        }

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

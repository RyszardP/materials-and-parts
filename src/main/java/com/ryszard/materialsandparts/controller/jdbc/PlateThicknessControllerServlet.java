package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.ThicknessDbUtil;
import com.ryszard.materialsandparts.domain.to.Thickness;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/PlateThicknessServlet")
public class PlateThicknessControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ThicknessDbUtil thicknessDbUtil;

    @Resource(name = "jdbc/materials")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            thicknessDbUtil = new ThicknessDbUtil(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {

            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "LIST";
            }
            switch (theCommand) {
                case "LIST":
                    listThickness(request, response);
                    break;
                case "ADD":
                    addThickness(request, response);
                    break;
                case "LOAD":
                    loadThickness(request, response);
                    break;
                case "UPDATE":
                    updateThickness(request, response);
                    break;
                case "DELETE":
                    deleteThickness(request, response);
                    break;
                default:
                    listThickness(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deleteThickness(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String theThicknessId = request.getParameter("thicknessId");
        thicknessDbUtil.deleteThickness(theThicknessId);
        listThickness(request, response);
    }

    private void updateThickness(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        long thicknessId = Integer.parseInt(request.getParameter("thicknessId"));
        int thicknessSize = Integer.parseInt(request.getParameter("thicknessSize"));
        Thickness theThickness = new Thickness(thicknessId, thicknessSize);
        thicknessDbUtil.updateThickness(theThickness);
        listThickness(request, response);
    }

    private void loadThickness(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String theThicknessId = request.getParameter("thicknessId");
        Thickness theThickness = thicknessDbUtil.getThickness(theThicknessId);
        request.setAttribute("THE_THICKNESS", theThickness);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-thickness-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addThickness(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int thicknessSize = Integer.parseInt(request.getParameter("thicknessSize"));
        Thickness theThickness = new Thickness(thicknessSize);
        thicknessDbUtil.addThickness(theThickness);
        listThickness(request, response);
    }

    private void listThickness(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Thickness> thicknesses = thicknessDbUtil.getThicknesses();
        request.setAttribute("THICKNESS_LIST", thicknesses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-thickness.jsp");
        dispatcher.forward(request, response);
    }

}

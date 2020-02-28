package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.PlateTypeDbUtil;
import com.ryszard.materialsandparts.domain.to.PlateType;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/PlateTypeServlet")
public class PlateTypeControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PlateTypeDbUtil plateTypeDbUtil;

    @Resource(name = "jdbc/materials")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            plateTypeDbUtil = new PlateTypeDbUtil(dataSource);
        }
        catch (Exception exc){
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
                    listPlateType(request, response);
                    break;
                case "ADD":
                    addPlateType(request, response);
                    break;
                case "LOAD":
                    loadPlateType(request, response);
                    break;
                case "UPDATE":
                    updatePlateType(request, response);
                    break;
                case "DELETE":
                    deletePlateType(request, response);
                    break;
                default:
                    listPlateType(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
    private void deletePlateType(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String thePlateTypeId = request.getParameter("plateId");
        plateTypeDbUtil.deletePlateType(thePlateTypeId);
        listPlateType(request, response);
    }

    private void updatePlateType(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        long plateTypeId = Integer.parseInt(request.getParameter("plateTypeId"));
        String plateTypeName = request.getParameter("plateTypeName");
        PlateType thePlateType = new PlateType(plateTypeId, plateTypeName);
        plateTypeDbUtil.updatePlateType(thePlateType);
        listPlateType(request, response);
    }

    private void loadPlateType(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String thePlateTypeId = request.getParameter("plateTypeId");
        PlateType thePlateType = plateTypeDbUtil.getPlateType(thePlateTypeId);
        request.setAttribute("THE_PLATE_TYPE", thePlateType);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-plate-type-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addPlateType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String plateTypeName = request.getParameter("plateTypeName");
        PlateType thePlateType = new PlateType(plateTypeName);
        plateTypeDbUtil.addPlateType(thePlateType);
        listPlateType(request, response);
    }

    private void listPlateType(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<PlateType> plates = plateTypeDbUtil.getPlateTypes();
        request.setAttribute("PLATE_LIST", plates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-plates.jsp");
        dispatcher.forward(request, response);
    }

}

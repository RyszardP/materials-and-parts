package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.PlateDbUtil;
import com.ryszard.materialsandparts.domain.to.Plate;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/PlateControllerServlet")
public class PlateControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PlateDbUtil plateDbUtil;

    @Resource(name = "jdbc/materials")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            plateDbUtil = new PlateDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }
            switch (command) {

                case "LIST":
                    listPlates(request, response);
                    break;

                case "ADD":
                    addPlate(request, response);
                    break;

                case "LOAD":
                    loadPlate(request, response);
                    break;

                case "UPDATE":
                    updatePlate(request, response);
                    break;

                case "DELETE":
                    deletePlate(request,response);
                    break;

                default:
                    listPlates(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deletePlate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String thePlateId = request.getParameter("plateId");
        plateDbUtil.deletePlate(thePlateId);
        listPlates(request, response);

    }
    private void updatePlate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int plateId = Integer.parseInt(request.getParameter("plateId"));
        int plateType = Integer.parseInt(request.getParameter("plateType"));
        int plateManufacturer = Integer.parseInt(request.getParameter("plateManufacturer"));
        int plateThickness = Integer.parseInt(request.getParameter("plateThickness"));
        int plateVCode = Integer.parseInt(request.getParameter("plateVCode"));
        int plateSizes = Integer.parseInt(request.getParameter("plateSizes"));
        String plateDescription = request.getParameter("plateDescription");
        Double platePrice = Double.valueOf(request.getParameter("platePrice"));
        Double plateCoefficient = Double.valueOf(request.getParameter("plateCoefficient"));

        Plate thePlate = new Plate(plateId, plateType, plateManufacturer, plateThickness, plateVCode, plateSizes,
                plateDescription, platePrice, plateCoefficient);

        plateDbUtil.updatePlate(thePlate);
        listPlates(request, response);
    }

    private void loadPlate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String thePlateId = request.getParameter("plateId");
        Plate thePlate = plateDbUtil.getPlate(thePlateId);
        request.setAttribute("THE_PLATE", thePlate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-plate-form.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addPlate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int plateType = Integer.parseInt(request.getParameter("plateType"));
        int plateManufacturer = Integer.parseInt(request.getParameter("plateManufacturer"));
        int plateThickness = Integer.parseInt(request.getParameter("plateThickness"));
        int plateVCode = Integer.parseInt(request.getParameter("plateVCode"));
        int plateSizes = Integer.parseInt(request.getParameter("plateSizes"));
        String plateDescription = request.getParameter("plateDescription");
        Double platePrice = Double.valueOf(request.getParameter("platePrice"));
        Double plateCoefficient = Double.valueOf(request.getParameter("plateCoefficient"));

        Plate thePlate = new Plate(plateType, plateManufacturer, plateThickness, plateVCode, plateSizes,
                plateDescription, platePrice, plateCoefficient);
        plateDbUtil.addPlate(thePlate);
        listPlates(request, response);
    }


    private void listPlates(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Plate> plates = plateDbUtil.getPlates();

        request.setAttribute("PLATE_LIST", plates);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list-plates.jsp");
        requestDispatcher.forward(request, response);
    }
}



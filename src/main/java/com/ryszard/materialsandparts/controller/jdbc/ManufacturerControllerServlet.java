package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.ManufacturerDbUtil;
import com.ryszard.materialsandparts.domain.to.Manufacturer;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManufacturerControllerServlet")
public class ManufacturerControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ManufacturerDbUtil manufacturerDbUtil;

    @Resource(name = "jdbc/web_manufacturer_tracer")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            manufacturerDbUtil = new ManufacturerDbUtil(dataSource);
        } catch (Exception exc) {
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
                    listManufacturer(request, response);
                    break;
                case "ADD":
                    addManufaturer(request, response);
                    break;
                case "LOAD":
                    loadManufacturer(request, response);
                    break;
                case "UPDATE":
                    updateManufacturer(request, response);
                    break;
                case "DELETE":
                    deleteManufacturer(request, response);
                    break;
                default:
                    listManufacturer(request, response);
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deleteManufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theManufacturerId = request.getParameter("studentId");
        manufacturerDbUtil.deleteManufacturer(theManufacturerId);
        listManufacturer(request, response);
    }

    private void updateManufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long manufacturerId = Long.parseLong(request.getParameter("manufacturerId"));
        String manufacturerTitle = request.getParameter("ManufacturerTitle");
        Manufacturer theManufacturer = new Manufacturer(manufacturerId, manufacturerTitle);
        manufacturerDbUtil.updateManufaturer(theManufacturer);
        listManufacturer(request, response);
    }

    private void loadManufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theManufacturerId = request.getParameter("manufacturerId");
        Manufacturer theManufacturer = manufacturerDbUtil.getManufacturer(theManufacturerId);
        request.setAttribute("THE_MANUFACTURER", theManufacturer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-manufacturer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addManufaturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String manufacturerTitle = request.getParameter("manufacturerTitle");
        Manufacturer theManufacturer = new Manufacturer(manufacturerTitle);
        manufacturerDbUtil.addManufacturer(theManufacturer);
        listManufacturer(request, response);
    }

    private void listManufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Manufacturer> manufacturers = manufacturerDbUtil.getManufacturers();
        request.setAttribute("MANUFACTURER_LIST", manufacturers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-manufacturer.jsp");
        dispatcher.forward(request, response);
    }
}

package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.PlateVCodeDbUtil;
import com.ryszard.materialsandparts.domain.to.PlateVCode;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/PlateVCodeServlet")
public class PlateVCodeControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PlateVCodeDbUtil plateVCodeDbUtil;

    @Resource(name = "jdbc/materials")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException{
        super.init();
        try {
            plateVCodeDbUtil = new PlateVCodeDbUtil(dataSource);
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
                    listPlateVCode(request, response);
                    break;
                case "ADD":
                    addPlateVCode(request, response);
                    break;
                case "LOAD":
                    loadPlateVCode(request, response);
                    break;
                case "UPDATE":
                    updatePlateVCode(request, response);
                    break;
                case "DELETE":
                    deletePlateVCode(request, response);
                    break;
                default:
                    listPlateVCode(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deletePlateVCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String thePlateVCodeId = request.getParameter("plateVCodeId");
        plateVCodeDbUtil.deletePlateVCode(thePlateVCodeId);
        listPlateVCode(request, response);
    }

    private void updatePlateVCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        long plateVCodeId = Integer.parseInt(request.getParameter("PlateVCodeId"));
        String plateVCodeTitle= request.getParameter("plateVCodeTitle");
        String plateVCodeDescription = request.getParameter("plateVCodeDescription");
        PlateVCode thePlateVCode = new PlateVCode(plateVCodeId, plateVCodeTitle, plateVCodeDescription);
        plateVCodeDbUtil.updatePlateVCode(thePlateVCode);
        listPlateVCode(request, response);
    }

    private void loadPlateVCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String thePlateVCodeId = request.getParameter("plateVCodeId");
        PlateVCode thePlateVCode = plateVCodeDbUtil.getPlateVCode(thePlateVCodeId);
        request.setAttribute("THE_PLATE_V_CODE", thePlateVCode);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-v-code-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addPlateVCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String plateVCodeTitle = request.getParameter("plateVCodeTitle");
        String plateVCodeDescription = request.getParameter("plateVCodeDescription");
        PlateVCode thePlateVCode = new PlateVCode(plateVCodeTitle, plateVCodeDescription);
        plateVCodeDbUtil.addPlateVCode(thePlateVCode);
        listPlateVCode(request, response);
    }

    private void listPlateVCode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<PlateVCode> plateVCodeList = plateVCodeDbUtil.getPlateVCodes();
        request.setAttribute("PLATE_V_CODE_LIST", plateVCodeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-v-code_list.jsp");
        dispatcher.forward(request, response);
    }

}

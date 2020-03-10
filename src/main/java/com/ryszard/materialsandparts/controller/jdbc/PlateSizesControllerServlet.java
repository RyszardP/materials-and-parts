package com.ryszard.materialsandparts.controller.jdbc;

import com.ryszard.materialsandparts.dao.utils.PlateSizesDbUtil;
import com.ryszard.materialsandparts.domain.to.PlateSizes;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet("/PlateSizesControllerServlet")
public class PlateSizesControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PlateSizesDbUtil plateSizesDbUtil;

    @Resource(name = "jdbc/materials")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            plateSizesDbUtil = new PlateSizesDbUtil(dataSource);
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
                    listPlateSizes(request, response);
                    break;

                case "ADD":
                    addPlateSizes(request, response);
                    break;

                case "LOAD":
                    loadPlateSizes(request, response);
                    break;

                case "UPDATE":
                    updatePlateSizes(request, response);
                    break;

                case "DELETE":
                    deletePlateSizes(request, response);
                    break;

                default:
                    listPlateSizes(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deletePlateSizes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int thePlateSize = Integer.parseInt(request.getParameter("plate_sizes_id"));
        plateSizesDbUtil.deletePlateSizes(String.valueOf(thePlateSize));
        listPlateSizes(request, response);

    }

    private void updatePlateSizes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int plateSizesId = Integer.parseInt(request.getParameter("plateSizesId"));
        int plateLength = Integer.parseInt(request.getParameter("plateLength"));
        int plateWidth = Integer.parseInt(request.getParameter("plateWidth"));
        PlateSizes thePlateSizes = new PlateSizes(plateSizesId, plateLength, plateWidth);
        plateSizesDbUtil.updatePlateSizes(thePlateSizes);
        listPlateSizes(request, response);
    }

    private void loadPlateSizes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String thePlateSizesId = request.getParameter("plateSizesId");
        PlateSizes thePlateSizes = plateSizesDbUtil.getPlateSizes(thePlateSizesId);
        request.setAttribute("THE_PLATE_SIZES", thePlateSizes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-plate-sizes-form.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addPlateSizes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int plateLength = Integer.valueOf(request.getParameter("plateLength"));
        int plateWidth = Integer.parseInt(request.getParameter("plateWidth"));


        PlateSizes thePlateSizes = new PlateSizes(plateLength, plateWidth);
        plateSizesDbUtil.addPlateSizes(thePlateSizes);
        listPlateSizes(request, response);
    }

    private void listPlateSizes(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<PlateSizes> plateSizes = plateSizesDbUtil.getPlateSizes();
        request.setAttribute("PLATE_SIZES_LIST", plateSizes);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list-plate-sizes.jsp");
        requestDispatcher.forward(request, response);
    }
}

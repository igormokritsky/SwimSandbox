package org.igormokritsky.controller;

import org.apache.log4j.Logger;
import org.igormokritsky.dao.CoachDao;
import org.igormokritsky.dao.impl.CoachDaoImpl;
import org.igormokritsky.entity.Coach;
import org.igormokritsky.service.CoachesWorkService;
import org.igormokritsky.service.impl.CoachesWorkServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.igormokritsky.db.DAOException;

@WebServlet("/CoachServlet")
public class CoachController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CoachController.class);
    private static final long serialVersionUID = 1L;
    public static final String LIST_COACH = "/listCoaches.jsp";
    public static final String INSERT_OR_EDIT = "/coach.jsp";
    private final CoachDao dao = CoachDaoImpl.getInstance();
//    private CoachesWorkService coachesWorkService;

    @Override
    public void init() {
//        LOG.trace("ListOrdersDriverController init start");
//        coachesWorkService = (CoachesWorkService) getServletContext().getAttribute("CoachesWorkServiceImpl");
//        LOG.trace("ListOrdersDriverController init finish");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = LIST_COACH;
            int id = Integer.parseInt(request.getParameter("id"));
//            coachesWorkService.deleteCoach(id);
            try {
                dao.delete(id);
            } catch (DAOException e) {
                e.printStackTrace();
            }
//            request.setAttribute("coaches", coachesWorkService.selectAll());
            try {
                request.setAttribute("coaches", dao.selectAll());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Coach coach = null;
//            coach = coachesWorkService.readCoach(id);
            try {
                coach = dao.read(id);
            } catch (DAOException e) {
                e.printStackTrace();
            }
            request.setAttribute("coach", coach);
        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
        } else {
            forward = LIST_COACH;
//            request.setAttribute("coaches", coachesWorkService.selectAll());
            try {
                request.setAttribute("coaches", dao.selectAll());
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Coach coach = new Coach();
        coach.setName(request.getParameter("name"));
        coach.setAwards(request.getParameter("awards"));
        coach.setCountryId(Integer.parseInt(request.getParameter("countryId")));
        coach.setUserId(Integer.parseInt(request.getParameter("userId")));
        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
//            coachesWorkService.createCoach(coach);
            try {
                dao.create(coach);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        else {
            coach.setId(Integer.parseInt(id));
//            coachesWorkService.updateCoach(coach);
            try {
                dao.update(coach);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_COACH);
//        request.setAttribute("coaches", coachesWorkService.selectAll());
        try {
            request.setAttribute("coaches", dao.selectAll());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        view.forward(request, response);
    }
}

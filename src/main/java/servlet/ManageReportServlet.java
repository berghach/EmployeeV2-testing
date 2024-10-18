package servlet;

import dao.AllowanceDAO;
import service.AllowanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Month;

public class ManageReportServlet extends HttpServlet {
    private AllowanceService allowanceService;

    @Override
    public void init() throws ServletException {
        AllowanceDAO allowanceDAO = new AllowanceDAO();
        allowanceService = new AllowanceService(allowanceDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("report");
        switch (action){
            case "allowance":
                req.setAttribute("allowanceStatus", allowanceService.getAllowanceStatistics(Month.valueOf(req.getParameter("month"))));
                req.getRequestDispatcher("statistics/allowances.jsp");
                break;

        }
    }
}

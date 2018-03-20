package servlets;

import dao.entity.EmployeeEntity;
import dao.logic.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeList")
public class EmployeeListController extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        List<EmployeeEntity> list = employeeDAO.getList(id);
        req.setAttribute("ListOfEmployee", list);
        req.getRequestDispatcher("/ListEmployee.jsp").forward(req, resp);
    }
}

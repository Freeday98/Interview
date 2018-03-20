package servlets;

import dao.entity.EmployeeEntity;
import dao.logic.DepartmentDAO;
import dao.logic.EmployeeDAO;
import exception.WrongAccessException;
import util.EmployeeEntityCreator;
import validation.CustomValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeEdit")
public class EmployeeEditController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private CustomValidator customValidator = new CustomValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("unique");
        Integer id = null;
        if (parameter == null) {
            throw new WrongAccessException();
        } else id = Integer.parseInt(parameter);
        req.setAttribute("id", id);
        req.setAttribute("ListOfDepartments", departmentDAO.getList());
        req.getRequestDispatcher("/NewEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeEntity employeeEntity = EmployeeEntityCreator.createEntityFromRequest(req);
        List<String> violationMessages = customValidator.getViolationMessageList(employeeEntity);
        Integer id = Integer.parseInt(req.getParameter("unique"));
        if (violationMessages.isEmpty()) {
            employeeDAO.updateEntity(employeeEntity,id);
            resp.sendRedirect("/Start");

        } else {
            req.setAttribute("WrongValue", employeeEntity);
            req.setAttribute("ErrorMessageList", violationMessages);
            req.setAttribute("ListOfDepartments", departmentDAO.getList());
            req.setAttribute("id",id);
            req.getRequestDispatcher("/NewEmployee.jsp").forward(req, resp);
        }

    }
}

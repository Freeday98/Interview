package servlets;

import dao.entity.EmployeeEntity;
import dao.logic.DepartmentDAO;
import dao.logic.EmployeeDAO;
import util.EmployeeEntityCreator;
import validation.CustomValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeCreate")
public class EmployeeCreationController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private CustomValidator customValidator = new CustomValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ListOfDepartments", departmentDAO.getList());
        req.getRequestDispatcher("/NewEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeeEntity employeeEntity = EmployeeEntityCreator.createEntityFromRequest(req);
        List<String> violationMessages = customValidator.getViolationMessageList(employeeEntity);
        if (violationMessages.isEmpty()) {
            employeeDAO.saveEntity(employeeEntity);
            resp.sendRedirect("/Start");
        } else {
            req.setAttribute("ErrorMessageList", violationMessages);
            req.setAttribute("WrongValue", employeeEntity);
            req.setAttribute("ListOfDepartments", departmentDAO.getList());
            req.getRequestDispatcher("/NewEmployee.jsp").forward(req, resp);
        }
    }
}

package servlets;

import dao.entity.DepartmentEntity;
import dao.logic.DepartmentDAO;
import exception.WrongAccessException;
import validation.CustomValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DepartmentCreate")
public class DepartmentCreationController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private CustomValidator customValidator = new CustomValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameOfDepartment");
        if (name == null) throw new WrongAccessException();
        System.out.println(name);
        DepartmentEntity departmentEntity = new DepartmentEntity(0, name);
        List<String> violationMessages = customValidator.getViolationMessageList(departmentEntity);
        if (violationMessages.isEmpty()) {
            departmentDAO.saveEntity(departmentEntity);
            resp.sendRedirect("/Start");
        } else {
            req.setAttribute("WrongValue", departmentEntity);
            req.setAttribute("ErrorMessageList", violationMessages);
            req.getRequestDispatcher("/NewDepartment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/NewDepartment.jsp").forward(req, resp);
    }
}

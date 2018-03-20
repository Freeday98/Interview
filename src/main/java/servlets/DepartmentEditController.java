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

@WebServlet("/DepartmentEdit")
public class DepartmentEditController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private CustomValidator customValidator = new CustomValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter  = req.getParameter("id");
        Integer id = null;
        if(parameter ==null){
            throw new WrongAccessException();
        } else id = Integer.parseInt(parameter);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/EditDepartment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("newName");
        Integer id = Integer.parseInt(req.getParameter("id"));
        DepartmentEntity departmentEntity = new DepartmentEntity(0, newName);
        List<String> violationMessages = customValidator.getViolationMessageList(departmentEntity);
        if (violationMessages.isEmpty()) {
            departmentDAO.updateEntity(departmentEntity, id);
            resp.sendRedirect("/Start");
        } else {
            req.setAttribute("id",id);
            req.setAttribute("WrongValue",departmentEntity);
            req.setAttribute("ErrorMessageList", violationMessages);
            req.getRequestDispatcher("/EditDepartment.jsp").forward(req, resp);
        }

    }
}

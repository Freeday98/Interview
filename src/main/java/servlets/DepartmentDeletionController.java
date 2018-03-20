package servlets;

import dao.logic.DepartmentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DepartmentDelete")
public class DepartmentDeletionController extends HttpServlet {
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        departmentDAO.deleteEntity(id);
        resp.sendRedirect("/Start");
    }
}

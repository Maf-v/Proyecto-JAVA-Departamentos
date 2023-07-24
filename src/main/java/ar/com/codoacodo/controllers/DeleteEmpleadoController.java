package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmpleadoController")

public class DeleteEmpleadoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long dni = Long.parseLong(req.getParameter("dni"));
		
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		try {
			dao.delete(dni);
			//mensaje de exito
			req.setAttribute("success", List.of("Se he eliminado el producto con id:" + dni));
		} catch (Exception e) {
			e.printStackTrace();
			//mensaje de error
			req.setAttribute("erorrs", List.of("NO se he eliminado el producto :" + e.getMessage()));
		}
		
		getServletContext().getRequestDispatcher("/FindAllEmpleadosController").forward(req, resp);
	}

}

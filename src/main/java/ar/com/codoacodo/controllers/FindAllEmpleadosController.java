package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FindAllEmpleadosController")

public class FindAllEmpleadosController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		List<Empleado> empleados = new ArrayList<>();
		
		try {
			empleados = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace(); //  muestra por consola el error 
		}
		req.setAttribute("listaEmpleados", empleados);
		getServletContext().getRequestDispatcher("/listaEmpleados.jsp").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doGet(req, resp);
	}
	
}



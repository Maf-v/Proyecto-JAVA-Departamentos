package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateEmpleadoController")

public class CreateEmpleadoController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dni = req.getParameter("dni");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String deptoId = req.getParameter("deptoId");
		
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		Departamento depto = null;
		try {
			depto = dao.getById(Long.parseLong(deptoId));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		iEmpleadoDAO daoEmpleado = new EmpleadoDAOMysqlImpl();
		Empleado empleado = new Empleado(Long.parseLong(dni), nombre, apellido, depto);			
		
		try {
			daoEmpleado.create(empleado);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/FindAllEmpleadosController").forward(req, resp);
	}
	
}

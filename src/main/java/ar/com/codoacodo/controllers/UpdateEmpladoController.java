package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmpleadoController")

public class UpdateEmpladoController extends HttpServlet {

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
			System.out.println("Error al obtener el Departamento" + e.getMessage());
		}
				
		iEmpleadoDAO daoEmpleado = new EmpleadoDAOMysqlImpl();
		Empleado empleado = new Empleado(Long.parseLong(dni), nombre, apellido, depto);
		try {
			daoEmpleado.update(empleado);
			req.setAttribute("success", List.of("Empleado DNI:" + empleado.getDni() + " actualizado correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al actualizar el Empleado" + e.getMessage());
			req.setAttribute("errors", List.of("Error actualizando Empleado<" + e.getMessage()));
		}

		getServletContext().getRequestDispatcher("/FindAllEmpleadosController").forward(req, resp);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dni = req.getParameter("dni");
		
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		Empleado empleado = null;
		try {
			empleado = dao.getByDni(Long.parseLong(dni));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("empleado", empleado);
		
		getServletContext().getRequestDispatcher("/editarEmpleado.jsp").forward(req, resp);
	}
	
}

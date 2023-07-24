package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
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

@WebServlet("/SearchEmpleadoController")

public class SearchEmpleadoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		String clave = req.getParameter("claveBusqueda");
		
		List<Empleado> empleados;
		try {
			empleados = dao.search(clave);
		} catch (Exception e) {
			empleados = List.of();//crea una lista vacia
			e.printStackTrace();
		}
		
		req.setAttribute("listaEmpleados", empleados);
		
		getServletContext().getRequestDispatcher("/listaEmpleados.jsp").forward(req, resp);
	}
	
}

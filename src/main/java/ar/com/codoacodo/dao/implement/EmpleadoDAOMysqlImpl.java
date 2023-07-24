package ar.com.codoacodo.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.db.AdministradorDeConexiones;
import ar.com.codoacodo.domain.Departamento;
import ar.com.codoacodo.domain.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class EmpleadoDAOMysqlImpl implements iEmpleadoDAO {

	@Override
	public Empleado getByDni(Long dni) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "select * from empleados where dni = " + dni;
		
		Statement statement  = connection.createStatement();
		
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		
		if (resultset.next()){
			// obtengo el dato del campo dni
			Long dniBd = resultset.getLong("dni");
			String nombreBd = resultset.getString("nombre");
			String apellidoBd = resultset.getString("apellido");
			Long deptoIdBd = resultset.getLong("depto_id");
			
			iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
			Departamento depto = dao.getById(deptoIdBd);
			return new Empleado(dniBd,nombreBd,apellidoBd,depto);
		}
		
		cerrar(connection);
		return  null;
	}
	
	@Override
	public List<Empleado> findAll() throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "select * from empleados";
		
		Statement statement  = connection.createStatement();
		
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		while (resultset.next()) {
			Long dniBd = resultset.getLong("dni");
			String nombreBd = resultset.getString("nombre");
			String apellidoBd = resultset.getString("apellido");
			Long deptoIdBd = resultset.getLong("depto_id");
			
			iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
			Departamento depto = dao.getById(deptoIdBd);
			empleados.add(new Empleado(dniBd,nombreBd,apellidoBd,depto));
		}
		cerrar(connection);
		return empleados;
	}
	
	@Override
	public void delete(Long dni) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "DELETE FROM empleados WHERE dni=" + dni;
		Statement statement  = connection.createStatement();
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		statement.executeUpdate(sql);
		cerrar(connection);
	}
	
	@Override
	public void update(Empleado empleado) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "update empleados set nombre = ?, apellido = ?, depto_id = ? where dni= ?"  ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, empleado.getNombre());
		statement.setString(2, empleado.getApellido());
		statement.setObject(3, empleado.getDepto().getId());
		statement.setLong(4, empleado.getDni());
		
		try {
			statement.execute();			 
		} catch (Exception e) {
			 e.printStackTrace();
			System.out.println(e.getMessage());
		}
			
		cerrar(connection);
	}
	
	@Override
	public void create(Empleado newEmpleado) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		
		//2 - arma el statement
		 String sql = "insert into empleados (dni, nombre, apellido, depto_id) values (?,?,?,?)" ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		 statement.setLong(1, newEmpleado.getDni());
		 statement.setString(2, newEmpleado.getNombre());
		 statement.setString(3, newEmpleado.getApellido());
		 statement.setLong(4, newEmpleado.getDepto().getId());
		 
		 
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }
		 
		 cerrar(connection);
	}
	
	public List<Empleado> search(String clave) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "SELECT * FROM empleados WHERE nombre LIKE ? OR apellido LIKE ?"  ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, "%" + clave + "%");
		statement.setString(2, "%" + clave + "%");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		while(resultSet.next()) {
			empleados.add(this.crearEmpleado(resultSet));
		}
		
		cerrar(connection);
		
		return empleados;
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
	
	private Empleado crearEmpleado(ResultSet resultSet) throws Exception {
		// obtengo el dato del campo id
		Long dniBd = resultSet.getLong("dni");
		String nombreBd = resultSet.getString("nombre");
		String apellidoBd = resultSet.getString("apellido");
		Long deptoIdBd = resultSet.getLong("depto_id");
		
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		Departamento depto = dao.getById(deptoIdBd);
		return new Empleado(dniBd,nombreBd,apellidoBd,depto);
	}
	
}

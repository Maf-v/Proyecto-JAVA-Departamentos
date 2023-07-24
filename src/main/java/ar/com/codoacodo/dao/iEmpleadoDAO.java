package ar.com.codoacodo.dao;
import ar.com.codoacodo.domain.Empleado;
import java.util.List;

public interface iEmpleadoDAO {
	
	public Empleado getByDni(Long dni) throws Exception; 
	
	// devuelve todos los registros de la tabla empleados
	public List<Empleado> findAll() throws Exception;
	
	// esto borra un registro por el dni del empleado
	public void delete(Long dni) throws Exception;

	public void update(Empleado empleado) throws Exception;
	
	public void create(Empleado newEmpleado) throws Exception;
	
	//select * from departamentos where titulo like '%clave%' 
	public List<Empleado> search(String clave) throws Exception;
	
}

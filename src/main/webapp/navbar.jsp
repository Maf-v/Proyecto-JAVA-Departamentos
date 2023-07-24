<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand me-0" href="<%=request.getContextPath()%>/index.jsp">CRUD</a>
    <div class="collapse ms-4 navbar-collapse row justify-content-end" id="navbarNav">
      <ul class="navbar-nav row col-6 align-self-start">
        <li class="nav-item col-3 align-content-center">
           <a class="nav-link fs-6" href="<%=request.getContextPath()%>/FindAllDepartamentoController">Listado Departamentos</a>
        </li>
        <li class="nav-item col-3 align-content-center">
          <a class="nav-link" href="<%=request.getContextPath()%>/nuevo.jsp">Nuevo Departamento</a>
        </li>
        <li class="nav-item col-3 align-content-center">
           <a class="nav-link" href="<%=request.getContextPath()%>/FindAllEmpleadosController">Listado de Empleados</a>
        </li>
        <li class="nav-item col-3 align-content-center">
          <a class="nav-link" href="<%=request.getContextPath()%>/nuevoEmpleado.jsp">Nuevo Empleado</a>
        </li>
       
      </ul>
      <div class="d-flex col-3 justify-content-end me-4"> 
	      <form class="d-flex" action="<%=request.getContextPath()%>/SearchDepartamentoController">
		        <input  name="claveBusqueda"  class="form-control me-2" type="search" placeholder="Buscar Depto" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
      </div>
      <div class="d-flex col-3 justify-content-end"> 
	      <form class="d-flex" action="<%=request.getContextPath()%>/SearchEmpleadoController">
		        <input  name="claveBusqueda"  class="form-control me-2" type="search" placeholder="Buscar Empleado" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
      </div>
      
    </div>
  </div>
</nav>
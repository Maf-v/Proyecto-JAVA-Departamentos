<%@page import="ar.com.codoacodo.domain.Empleado"%>
<%
	Empleado empleado = (Empleado)request.getAttribute("empleado"); // levanto el departamento que viene del controller
%>

<!Doctype html>
<html>
		<head>
		 <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Editar Empleado</title>
		 <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  
	</head>
	<body>
	   <div class="container-fluid bg-light p-0">
	   		<jsp:include page="navbar.jsp"/>
	   </div>
		
		
		<main class="container mt-5">
			<h1>Editar Empleado</h1>
			
			<div class="row mt-5">
				<div class="col-12">
					<form class="row g-3 needs-validation" 
						action="<%=request.getContextPath()%>/UpdateEmpleadoController"
						method="POST">
					  <div class="col-md-0">
					    <input type="hidden" name="dni" value="<%=empleado.getDni()%>">
					    
					  </div>
					  <div class="col-md-8">
					    <label for="validationCustom02" class="form-label">Nombre</label>
					    <input type="text"
					    	name="nombre" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="<%=empleado.getNombre()%>" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-md-8">
					    <label for="validationCustom02" class="form-label">Apellido</label>
					    <input type="text"
					    	name="apellido" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="<%=empleado.getApellido()%>" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					  <div class="col-md-8">
					    <label for="validationCustom02" class="form-label">Depto ID</label>
					    <input type="number"
					    	name="deptoId" 
					    	class="form-control" 
					    	id="validationCustom02"
					    	value="<%=empleado.getDepto().getId()%>" 
					    	required>
					    <div class="valid-feedback">
					      Looks good!
					    </div>
					  </div>
					 
					  <div class="col-12">
					    <button class="btn btn-primary" type="submit">Editar</button>
					  </div>
					</form>
				</div>
			</div>
		</main>
	</body>
</html>
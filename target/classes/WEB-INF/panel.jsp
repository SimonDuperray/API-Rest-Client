<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		form { margin-bottom: 20px; }
	</style>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
	    <h1>Panel view</h1>
	    <a href="/API-Client/distance" class="btn btn-primary">Back to Home</a>
	    <div id="cities">
	    	<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">CodeCommune</th>
			      <th scope="col">NomCommune</th>
			      <th scope="col">CodePostal</th>
			      <th scope="col">Actions</th>
			    </tr>
			  </thead>
			  <tbody>
				  <c:forEach var="ville" items="${villes}">
		    			<tr>
					      <th scope="row"><c:out value="${ ville.getCodeCommune() }"/></th>
					      <td><c:out value="${ ville.getNomCommune() }"/></td>
					      <td><c:out value="${ ville.getCodePostal() }"/></td>
					      <td>
					      	<a href="/API-Client/panel/edit?id=<c:out value='${ ville.getCodeCommune() }' />">&#9871;</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="/API-Client/panel/delete?id=<c:out value='${ ville.getCodeCommune() }' />">&#xd7;</a>
						  </td>
					    </tr>
		    		</c:forEach>
			  </tbody>
			</table>
	    	
	    </div>
    </div>
</html>
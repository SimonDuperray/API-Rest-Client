<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		form { margin-bottom: 20px; }
		input.btn,input.btn-primary { margin-top: 20px; }
	</style>
	<title>Panel</title>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
	    <h1>Panel view</h1>
	    <a href="/API-Client/distance" class="btn btn-primary">Back to Home</a>
	    <form class="form" method="post" action="panel">
	    	<div class="row">
	    		<div class="col-md-4">
		    		<label for="codeCommune">Code Commune: </label>
		    		<input type="text" class="form-control" name="codeCommune" id="codeCommune" required />
	    		</div>
	    		<div class="col-md-4">
		    		<label for="nomCommune">Nom Commune: </label>
		    		<input type="text" class="form-control" name="nomCommune" id="nomCommune" required />
	    		</div>
	    		<div class="col-md-4">
		    		<label for="nomCommune">Code Postal: </label>
		    		<input type="text" class="form-control" id="codePostal" name="codePostal" required />
	    		</div>
	    	</div>
	    	<div class="row">
	    		<div class="col-md-4">
	    			<label for="ligne">Ligne: </label>
	    			<input type="text" class="form-control" id="ligne" name="ligne" required />
	    		</div>
	    		<div class="col-md-6">
	    			<label for="ligne">Libellé Acheminement: </label>
	    			<input type="text" class="form-control" id="libelleAcheminement" name="libelleAcheminement" required />
	    		</div>
	    	</div>
	    	<div class="row">
	    		<div class="col-md-3">
	    			<label for="latitude">Latitude: </label>
	    			<input type="text" id="latitude" name="latitude" class="form-control" required />
	    		</div>
	    		<div class="col-md-3">
	    			<label for="longitude">Longitude: </label>
	    			<input type="text" id="longitude" name="longitude" class="form-control" required />
	    		</div>
	    	</div>
	    	<input class="btn btn-primary" type="submit" value="Add City" />
	    </form>
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
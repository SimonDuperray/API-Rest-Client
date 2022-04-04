<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
	    <form method="post" action="distance">
	    	<div class="form-group">
		    	<label>Ville 1: </label>
		        <select name="city1">
		            <c:forEach var="ville" items="${result}" >
		                <option value="${ ville.getCodeCommune() }">
		                    ${ville.getNomCommune()}
		                </option>
		            </c:forEach>
		        </select>
	    	</div>
	        <div class="form-group">
		        <label>Ville 2: </label>
		        <select name="city2">
		            <c:forEach var="ville" items="${result}" >
		                <option value="${ ville.getCodeCommune() }">
		                    ${ville.getNomCommune()}
		                </option>
		            </c:forEach>
		        </select>
	        </div>
	        <input class="btn btn-primary" type="submit" value="compute"/>
	    </form>
	    <c:if test="${ distance>0 }">
	        <p>There are around <c:out value="${ distance }"/> km between <c:out value="${ city1 }"/> and <c:out value="${ city2 }"/></p>
	    </c:if>
    </div>
</html>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
    <form method="post" action="distance">
    	<label>Ville 1: </label>
    	<select name="city1">
    		<c:forEach var="ville" items="${result}" >
                <option value="${ ville.getCodeCommune() }">
                    ${ville.getNomCommune()}
                </option>
            </c:forEach>
    	</select>
    	<label>Ville 2: </label>
    	<select name="city2">
    		<c:forEach var="ville" items="${result}" >
                <option value="${ ville.getCodeCommune() }">
                    ${ville.getNomCommune()}
                </option>
            </c:forEach>
    	</select>
    	<input type="submit" value="compute"/>
    </form>
    <c:if test="${ distance>0 }">
		<h1>Distance: <c:out value="${ distance }"/></h1>
	</c:if>
</html>
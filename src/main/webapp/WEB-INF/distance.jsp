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
		<p>There are <c:out value="${ distance }"/> km between <c:out value="${ city1 }"/> and <c:out value="${ city2 }"/></p>
	</c:if>
</html>
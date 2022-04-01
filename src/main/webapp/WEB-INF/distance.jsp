<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
    <form>
    	<label>Ville 1: </label>
    	<select>
    		<c:forEach var="ville" items="${result}" >
                <option value="${ ville.getNomCommune() }">
                    ${ville.getNomCommune()}
                </option>
            </c:forEach>
    	</select>
    	<label>Ville 2: </label>
    	<select>
    		<c:forEach var="ville" items="${result}" >
                <option value="${ ville.getNomCommune() }">
                    ${ville.getNomCommune()}
                </option>
            </c:forEach>
    	</select>
    	<input type="submit" value="compute"/>
    </form>
</html>
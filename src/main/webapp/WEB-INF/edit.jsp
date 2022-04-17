<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit form</title>
</head>
<body>
	<p>code commune: <c:out value="${ getVille.getCodeCommune() }" /></p>
	<p>nom commune: <c:out value="${ getVille.getNomCommune() }" /></p>
	<p>code postal: <c:out value="${ getVille.getCodePostal() }" /></p>
	<p>libelle acheminement: <c:out value="${ getVille.getLibelleAcheminement() }" /></p>
	<p>ligne: <c:out value="${ getVille.getLigne() }" /></p>
	<p>latitude: <c:out value="${ getVille.getLatitude() }" /></p>
	<p>longitude: <c:out value="${ getVille.getLongitude() }" /></p>
</body>
</html>
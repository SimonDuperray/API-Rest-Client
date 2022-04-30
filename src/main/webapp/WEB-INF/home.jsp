<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="header.html" %>
    <title>Homepage</title>
</head>
<body>
<%@ include file="navbar.html" %>
<div class="container">
	<div class="justify-content-center" style="margin: 20px 0;">
	   	<a class="btn btn-dark" href="/API-Client/list" style="font-weight: bold;">Listed cities</a>
	    <form method="post">
	        <div class="row justify-content-center">
	            <label class="col-auto" for="city1">Choose two cities among the following ones:</label>
	        </div>
	        <div class="row justify-content-center" style="margin-top: 20px;">
	            <div class="col-auto">
	                <select class="custom-select" name="ville1" id="ville1">
	                    <c:forEach items="${ cities }" var="city">
	                        <option value="${ city.getCodeCommune() }">
	                        	<c:out value="${ city.getNomCommune() }"/>
                            </option>
	                    </c:forEach>
	                </select>
	            </div>
	            <div class="col-auto">
	                <select class="custom-select" name="ville2" id=""ville2"">
	                    <c:forEach items="${ cities }" var="city">
	                        <option value="${ city.getCodeCommune() }">
	                        	<c:out value="${ city.getNomCommune() }"/>
                      		</option>
	                    </c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="row justify-content-center" style="margin-top: 20px;">
	            <button type="submit" class="btn btn-warning col-auto" style="font-weight: bold;">Get Distance</button>
	        </div>
	    </form>
	</div>
	<c:if test="${ displayDistance }">
	    <div style="margin: 20px 0; display: flex; justify-content: center;">
	    <div class="col-5">
	        <div class="card" style="box-shadow: 0px 0px 7px 2px rgba(0, 0, 0, 0.25); border: none;">
	        	<div class="card-header" style="background-color: #212529!important;">
	        		<h5 class="card-title" style="color: white;">Result</h5>
	        	</div>
	        	<p class="card-text" style="padding: 15px;">The distance between <c:out value="${ city1.getNomCommune() }"/> and <c:out value="${ city2.getNomCommune() }"/> is <c:out value="${ distance/1000 }"/> km.</p>
	        	<div class="card-footer">
	        		<p>Il fait <c:out value="${ weatherCity2.get('temp') }"/>°C a <c:out value="${ city2.getNomCommune() }"/>, votre destination.</p>
	        	</div>
	        </div>
	     </div>
    </div>
</c:if>
</div>
</body>
</html>
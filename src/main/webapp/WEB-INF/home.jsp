<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="header.html" %>
    <title>Home</title>
</head>
<body>
<div class="row justify-content-center" style="margin: 20px 0;">
    <form method="post">
        <div class="row justify-content-center" style="margin-top: 20px;">
            <label class="col-auto" for="ville1">Choose two cities:</label>
        </div>
        <div class="row justify-content-center" style="margin-top: 20px;">
            <div class="col-auto">
                <select class="custom-select my-1 mr-sm-2" name="ville1"
                        id="ville1">
                    <c:forEach items="${ villes }" var="ville">
                        <option value="${ ville.getCodeCommune() }"><c:out
                                value="${ ville.getNomCommune() }"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-auto">
                <select class="custom-select my-1 mr-sm-2" name="ville2"
                        id="ville2">
                    <c:forEach items="${ villes }" var="ville">
                        <option value="${ ville.getCodeCommune() }"><c:out
                                value="${ ville.getNomCommune() }"/></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row justify-content-center" style="margin-top: 20px;">
            <button type="submit" class="btn btn-primary col-auto">Distance</button>
        </div>
    </form>
</div>
<c:if test="${ displayDistance }">
    <div class="row justify-content-center" style="margin: 20px 0;">
        <div class="col-3">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title">
                        <c:out value="${ ville1.getNomCommune() }"/>
                    </h5>
                    <p class="card-text">
                        <c:out value="${ ville1.getCodePostal() }"/>
                    </p>
                </div>
                <p class="text-center">
                    <img class="card-img-top" src="${ weatherVille1.get('icon') }"
                         alt="Weather Icon" style="width: 10rem;">
                </p>
                <div class="card-footer text-center">
                    <p>
                        <c:out value="${ weatherVille1.get('main') }"/>
                    </p>
                    <small class="text-muted"> <c:out
                            value="${ weatherVille1.get('temp') }"/> °C
                    </small>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card text-center">
                <div class="card-header">
                    <h5 class="card-title">Direct Distance</h5>
                </div>
                <div>
                    <h5 class="card-title">
                        <img class="card-img-top"
                             src="https://cdn-icons-png.flaticon.com/512/526/526754.png"
                             alt="Direct Distance Icon"
                             style="width: 5rem; margin: 0.5rem 1.5rem;">
                        <c:out value="${ distanceDirect/1000 }"/>
                        km
                    </h5>
                </div>
                <div class="card-header">
                    <h5 class="card-title">Distance by Car</h5>
                </div>
                <div>
                    <h5 class="card-title">
                        <img class="card-img-top"
                             src="https://thumbs.dreamstime.com/z/destination-distance-icon-passenger-transportation-destination-distance-icon-outline-logo-car-highway-geo-location-186218864.jpg"
                             alt="Road Distance Icon"
                             style="width: 9rem; height: 7.5rem; object-fit: cover;">
                        <c:out value="${ distanceRoad/1000 }"/>
                        km
                    </h5>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title">
                        <c:out value="${ ville2.getNomCommune() }"/>
                    </h5>
                    <p class="card-text">
                        <c:out value="${ ville2.getCodePostal() }"/>
                    </p>
                </div>
                <p class="text-center">
                    <img class="card-img-top" src="${ weatherVille2.get('icon') }"
                         alt="Weather Icon" style="width: 10rem;">
                </p>
                <div class="card-footer text-center">
                    <p>
                        <c:out value="${ weatherVille2.get('main') }"/>
                    </p>
                    <small class="text-muted"> <c:out
                            value="${ weatherVille2.get('temp') }"/> °C
                    </small>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="row justify-content-around" style="margin: 20px 0;">
    <div class="col-auto"></div><div class="col-auto">
        <a class="btn btn-primary" href="/list" role="button">List</a></div>
</div>
</body>
</html>
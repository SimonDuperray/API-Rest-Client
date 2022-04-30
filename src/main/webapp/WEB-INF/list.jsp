<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="header.html" %>
    <style>.modal-backdrop.fade {
        opacity: 0;
        filter: alpha(opacity=0);
    }</style>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Home</title>
    <style>
    	div.form-group,div.row {
    		margin: 10px 0!important;
    	}
    </style>
</head>
<body>
<%@ include file="navbar.html" %>
<div class="container">
	<c:if test="${message != null and success}">
	    <div class="row justify-content-center" style="margin-top: 20px;">
	        <div class="col-md-6">
	            <div class="alert alert-success alert-dismissible fade show" role="alert">
	                    ${message}
	                <button type="button" class="close" data-dismiss="alert" aria-label="Close" style="float: right">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	        </div>
	    </div>
	</c:if>
	<c:if test="${message != null and !success}">
		<div class="row justify-content-center" style="margin-top: 20px;">
	        <div class="col-md-6">
	            <div class="alert alert-danger alert-dismissible fade show" role="alert">
	                    ${message}
	                <button type="button" class="close" data-dismiss="alert" aria-label="Close" style="float: right">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	        </div>
	    </div>
	</c:if>
	
	<div style="padding: 20px; box-shadow: 0px 0px 7px 2px rgba(0, 0, 0, 0.25); margin: 20px; border-radius: 7px;">
	    <form action="/API-Client/list?page=${page}" method="post">
    		<h3>Add new city</h3>
			<input type="hidden" name="_method" value="post">
			<div class="form-group row">
			    <label for="codeCommune" class="col-sm-3 col-form-label">Code</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="codeCommune" id="codeCommune" placeholder="Code Commune">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="nomCommune" class="col-sm-3 col-form-label">Nom </label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="nomCommune" id="nomCommune" placeholder="Nom Commune">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="codePostal" class="col-sm-3 col-form-label">Code Postal</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="codePostal" id="codePostal" placeholder="Code Postal">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="libelle" class="col-sm-3 col-form-label">Libelle</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="libelle" id="libelle" placeholder="Libelle">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="ligne" class="col-sm-3 col-form-label">Ligne</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="ligne" id="ligne" placeholder="Ligne">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="latitude" class="col-sm-3 col-form-label">Latitude</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="latitude" id="latitude" placeholder="Latitude">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="longitude" class="col-sm-3 col-form-label">Longitude</label>
			    <div class="col-sm-9">
			        <input type="text" class="form-control" name="longitude" id="longitude" placeholder="Longitude">
			    </div>
			</div>
			<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel Action</button>
			<button type="submit" class="btn btn-success">Add City</button>
	   </form>
	</div>
		
   <table class="table" style="margin-top: 20px;">
       <thead>
	       <tr style="text-align:center;">
	           <th scope="col">Code</th>
	           <th scope="col">Nom de la Commune</th>
	           <th scope="col">Code Postal</th>
	           <th scope="col">Libelle Acheminement</th>
	           <th scope="col">Latitude</th>
	           <th scope="col">Longitude</th>
	           <th scope="col">Supprimer</th>
	           <th scope="col">Modifier</th>
	       </tr>
       </thead>
       <tbody>
	       <c:forEach items="${cities}" var="city">
	           <tr style="text-align:center;">
	               <td>${city.getCodeCommune()}</td>
	               <td>${city.getNomCommune()}</td>
	               <td>${city.getCodePostal()}</td>
	               <td>${city.getLibelleAcheminement()}</td>
	               <td>${city.getLatitude()}</td>
	               <td>${city.getLongitude()}</td>
               		<td style="width: 18px;padding: .5rem .2rem">
	                   <form action="/API-Client/list?page=${page}" method="post">
                           <input type="hidden" name="_method" value="delete">
                           <input type="hidden" name="codeCommune" value="${city.getCodeCommune()}">
                           <button href="/API-Client/list?page=${page}" type="submit" class="btn btn-danger">
                               Delete
                           </button>
                          </form>
               		</td>
               		<td style="width: 18px;padding: .5rem .2rem">
	                   <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updateModal-${city.getCodeCommune()}" style="margin: 0">
	                       Edit
	                   </button>
	                   <div class="modal" id="updateModal-${city.getCodeCommune()}" tabindex="-1" role="dialog" aria-hidden="true">
	                       <div class="modal-dialog modal-dialog-centered" role="document">
	                           <div class="modal-content">
	                               <div class="modal-header">
	                                   <h5 class="modal-title">
	                                       Edit ${city.getNomCommune()}</h5>
	                                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                       <span aria-hidden="true">&times;</span>
	                                   </button>
	                               </div>
	                               <div class="modal-body">
	                                   <form action="/API-Client/list?page=${page}" method="post">
	                                       <input type="hidden" name="_method" value="put">
	                                       <div class="form-group row">
	                                           <label for="codeCommune-${city.getCodeCommune()}"
	                                                  class="col-sm-3 col-form-label">Code
	                                           </label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="codeCommune" id="codeCommune-${city.getCodeCommune()}" placeholder="Code" value="${city.getCodeCommune()}" readonly>
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="nomCommune-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Nom</label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="nomCommune" id="nomCommune-${city.getCodeCommune()}" placeholder="Nom" value="${city.getNomCommune()}">
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="codePostal-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Code Postal</label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="codePostal" id="codePostal-${city.getCodeCommune()}" placeholder="Code Postal" value="${city.getCodePostal()}">
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="libelle-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Libelle</label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="libelle" id="libelle-${city.getCodeCommune()}" placeholder="Libelle" value="${city.getLibelleAcheminement()}">
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="ligne-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Ligne</label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="ligne" id="ligne-${city.getCodeCommune()}" placeholder="Ligne" value="${city.getLigne()}">
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="latitude-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Latitude</label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="latitude" id="latitude-${city.getCodeCommune()}" placeholder="Latitude" value="${city.getLatitude()}">
	                                           </div>
	                                       </div>
	                                       <div class="form-group row">
	                                           <label for="longitude-${city.getCodeCommune()}" class="col-sm-3 col-form-label">Longitude
	                                           </label>
	                                           <div class="col-sm-9">
	                                               <input type="text" class="form-control" name="longitude" id="longitude-${city.getCodeCommune()}" placeholder="Longitude" value="${city.getLongitude()}">
	                                           </div>
	                                       </div>
	                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	                                       <button type="submit" class="btn btn-success">Save changes</button>
	                                   </form>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
	               </td>
	           </tr>
	       </c:forEach>
       </tbody>
	</table>
   
	<div class="row justify-content-center">
	    <div class="col-auto">
	        <nav aria-label="...">
	            <ul class="pagination">
	                <c:if test="${page==1}">
	                    <li class="page-item disabled">
	                        <span class="page-link">Previous</span>
	                    </li>
	                </c:if>
	                <c:if test="${page==2}">
	                    <li class="page-item">
	                        <a class="page-link" href="/API-Client/list?page=${page-1}">Previous</a>
	                    </li>
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page-1}">${page-1}</a></li>
	                </c:if>
	                <c:if test="${page>2}">
	                    <li class="page-item">
	                        <a class="page-link" href="/API-Client/list?page=${page-1}">Previous</a>
	                    </li>
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page-2}">${page-2}</a></li>
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page-1}">${page-1}</a></li>
	                </c:if>
	                <li class="page-item active">
	                  <span class="page-link">
	                    ${page}
	                    <span class="sr-only"></span>
	                  </span>
	                </li>
	                <c:if test="${page==nbPages}">
	                    <li class="page-item disabled">
	                        <span class="page-link">Next</span>
	                    </li>
	                </c:if>
	                <c:if test="${page==nbPages-1}">
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page+1}">${page+1}</a></li>
	                    <li class="page-item">
	                        <a class="page-link" href="/API-Client/list?page=${page+1}">Next</a>
	                    </li>
	                </c:if>
	                <c:if test="${page<nbPages-1}">
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page+1}">${page+1}</a></li>
	                    <li class="page-item"><a class="page-link" href="/API-Client/list?page=${page+2}">${page+2}</a></li>
	                    <li class="page-item">
	                        <a class="page-link" href="/API-Client/list?page=${page+1}">Next</a>
	                    </li>
	                </c:if>
	            </ul>
	        </nav>
	    </div>
	</div>
</body>
</html>
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
		       <button type="submit" class="btn btn-primary">Add City</button>
		   </form>
		</div>
		
	        <table class="table" style="border-collapse: collapse; border-radius: 1em; overflow: hidden;margin-top: 20px;">
	            <thead>
	            <tr class="table-primary" style="text-align:center;">
	                <th scope="col" style="width:80px;">Code</th>
	                <th scope="col" style="width:300px;">Nom de la Commune</th>
	                <th scope="col" style="width:80px;">Code Postal</th>
	                <th scope="col" style="width:300px;">Libelle Acheminement</th>
	                <th scope="col" style="width:80px;">Latitude</th>
	                <th scope="col" style="width:80px;">Longitude</th>
	            </tr>
	            </thead>
	            <tbody>
	            <c:forEach items="${villes}" var="ville">
	                <tr style="text-align:center;">
	                    <td style="width: 18px;padding: .5rem .2rem">
	                        <button type="button" class="btn btn-success"
	                                data-toggle="modal" data-target="#updateModal-${ville.getCodeCommune()}"
	                                style="margin: 0">
	                            <svg xmlns="http://www.w3.org/2000/svg" width="16"
	                                 height="16" fill="currentColor"
	                                 class="bi bi-pencil-square" viewBox="0 0 16 16">
	                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
	                                <path fill-rule="evenodd"
	                                      d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
	                            </svg>
	                        </button>
	                        <div class="modal fade" id="updateModal-${ville.getCodeCommune()}" tabindex="-1" role="dialog"
	                             aria-hidden="true">
	                            <div class="modal-dialog modal-dialog-centered" role="document">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title">
	                                            Update ${ville.getNomCommune()}</h5>
	                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                            <span aria-hidden="true">&times;</span>
	                                        </button>
	                                    </div>
	                                    <div class="modal-body">
	                                        <form action="/API-Client/list?page=${page}" method="post">
	                                            <input type="hidden" name="_method" value="put">
	                                            <div class="form-group row">
	                                                <label for="codeCommune-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Code
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="codeCommune"
	                                                           id="codeCommune-${ville.getCodeCommune()}"
	                                                           placeholder="Code" value="${ville.getCodeCommune()}"
	                                                           readonly>
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="nomCommune-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Nom
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="nomCommune"
	                                                           id="nomCommune-${ville.getCodeCommune()}"
	                                                           placeholder="Nom" value="${ville.getNomCommune()}">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="codePostal-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Code
	                                                    Postal</label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="codePostal"
	                                                           id="codePostal-${ville.getCodeCommune()}"
	                                                           placeholder="Code Postal" value="${ville.getCodePostal()}">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="libelle-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Libelle
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="libelle"
	                                                           id="libelle-${ville.getCodeCommune()}"
	                                                           placeholder="Libelle"
	                                                           value="${ville.getLibelleAcheminement()}">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="ligne-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Ligne
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="ligne"
	                                                           id="ligne-${ville.getCodeCommune()}"
	                                                           placeholder="Ligne" value="${ville.getLigne()}">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="latitude-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Latitude
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="latitude"
	                                                           id="latitude-${ville.getCodeCommune()}"
	                                                           placeholder="Latitude" value="${ville.getLatitude()}">
	                                                </div>
	                                            </div>
	                                            <div class="form-group row">
	                                                <label for="longitude-${ville.getCodeCommune()}"
	                                                       class="col-sm-3 col-form-label">Longitude
	                                                </label>
	                                                <div class="col-sm-9">
	                                                    <input type="text" class="form-control" name="longitude"
	                                                           id="longitude-${ville.getCodeCommune()}"
	                                                           placeholder="Longitude" value="${ville.getLongitude()}">
	                                                </div>
	                                            </div>
	                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel
	                                            </button>
	                                            <button type="submit" class="btn btn-primary">Save changes</button>
	                                        </form>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </td>
	                    <td style="width: 18px;padding: .5rem .2rem">
	                        <button type="button" class="btn btn-outline-danger" data-toggle="modal"
	                                data-target="#deleteModal-${ville.getCodeCommune()}"
	                                style="margin: 0">
	                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
	                                 class="bi bi-trash3" viewBox="0 0 16 16">
	                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"></path>
	                            </svg>
	                        </button>
	                        <div class="modal fade" id="deleteModal-${ville.getCodeCommune()}" tabindex="-1" role="dialog"
	                             aria-hidden="true">
	                            <div class="modal-dialog modal-dialog-centered" role="document">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title">Modal title</h5>
	                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                            <span aria-hidden="true">&times;</span>
	                                        </button>
	                                    </div>
	                                    <div class="modal-body">
	                                        Etes-vous sur de vouloir supprimer ${ville.getNomCommune()} ?
	                                    </div>
	                                    <div class="modal-footer">
	                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel
	                                        </button>
	                                        <form action="/API-Client/list?page=${page}" method="post">
	                                            <input type="hidden" name="_method" value="delete">
	                                            <input type="hidden" name="codeCommune" value="${ville.getCodeCommune()}">
	                                            <button href="/API-Client/list?page=${page}" type="submit" class="btn btn-danger">
	                                                Delete
	                                            </button>
	                                        </form>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </td>
	                    <td>${ville.getCodeCommune()}</td>
	                    <td>${ville.getNomCommune()}</td>
	                    <td>${ville.getCodePostal()}</td>
	                    <td>${ville.getLibelleAcheminement()}</td>
	                    <td>${ville.getLatitude()}</td>
	                    <td>${ville.getLongitude()}</td>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
	<%--page selection--%>
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
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Computer Database</title>
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" media="screen">

</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
            <a id="homePage" class="navbar-brand" href="springcdb"><spring:message code="cdb.webapp.title"/></a>
		</div>
		
 	<a class="align-middle" href="?locale=en"><i class="us flag"></i></a>
    <a class="align-middle" href="?locale=fr_FR"><i class="fr flag"></i></a>
    
    </header>
     
	<section id="main">
		<div class="container">

			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>

			<h1 id="homeTitle">${numberOfElements} <spring:message code="cdb.dashboard.total"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
                    	<input name="action" type="hidden" value="filter"/>
						<input type="search" id="searchbox" name="filter" class="form-control" placeholder="Search name" /> 
						<input type="submit" id="searchsubmit" value=<spring:message code="cdb.dashboard.search"/> class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer" ><spring:message code="cdb.add.title"/></a>
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="cdb.dashboard.edit"/></a>
				</div>
			</div>
		</div>

        <form id="deleteForm" action="springcdb/delete" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="cdb.computer.name"/></th>
						<th><spring:message code="cdb.computer.introduced"/></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="cdb.computer.discontinued"/></th>
						<!-- Table header for Company -->
						<th><spring:message code="cdb.computer.company"/></th>

					</tr>
				</thead>
				<tbody id="results">
					<c:forEach items="${computers}" var="computerDto">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computerDto.id}"></td>
							<td><a href="editComputer?id=${computerDto.id}" onclick="">${computerDto.computerName}</a></td>
							<td>${computerDto.dateWichIsIntroduced}</td>
							<td>${computerDto.dateWichIsDiscontinued}</td>
							<td>${computerDto.companyName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="?action=previousPage&numOfPage=${currentPage-1}" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="i"
					begin="${(currentPage - 2 > 0) ? (currentPage - 2) : 1}"
					end="${(currentPage + 2 < numberOfPages) ? (currentPage + 2) : numberOfPages}">
					<li><a href="?action=numOfPage&numOfPage=${i-1}">${i}</a></li>
				</c:forEach>
				<li><a href="?action=nextPage&numOfPage=${currentPage+1}" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a class="btn btn-default " href="?size=10">10</a> <a
					class="btn btn-default " href="?size=50">50</a> <a
					class="btn btn-default " href="?size=100">100</a>
			</div>
		</div>
	</footer>
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/dashboard.js"/>"></script>

</body>
</html>
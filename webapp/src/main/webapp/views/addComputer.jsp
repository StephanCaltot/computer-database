<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="cdb.add.title"/></h1>
                    <form:form action="addComputer" modelAttribute="computerDto" method="POST">
                    	<input name="action" type="hidden" value="add"/>

                       		<spring:bind path="computerName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="computerName"><spring:message code="cdb.computer.name"/></label>
								    <form:input path="computerName" type="text" id="computerName" class="form-control" placeholder="Computer name" name="computerName" />
									<form:errors path="computerName" class="control-label" />
								</div>
							</spring:bind>

                            <spring:bind path="dateWichIsIntroduced">
								<div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="introduced"><spring:message code="cdb.computer.introduced"/></label>
									<form:input path="dateWichIsIntroduced" type="date" class="form-control" id="introduced" placeholder="Introduced date" name="introduced" />
									<form:errors path="dateWichIsIntroduced" class="control-label" />
								</div>
							</spring:bind>

                             <spring:bind path="dateWichIsDiscontinued">
								<div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="discontinued"><spring:message code="cdb.computer.discontinued"/></label>
                                <form:input path="dateWichIsDiscontinued" type="date" class="form-control" id="discontinued" placeholder="Discontinued date" name="discontinued" />
									<form:errors path="dateWichIsDiscontinued" class="control-label" />
								</div>
							</spring:bind>



	                        <spring:bind path="companyId">
								<div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="companyId"><spring:message code="cdb.computer.company"/></label>
									<form:select path="companyId" class="form-control">
										<form:option value="0" label="--No company selected --" />
										<form:options items="${companies}" itemValue="id" itemLabel="name" />
									</form:select>
									<form:errors path="companyId" class="control-label" />
								</div>
							</spring:bind>

                        <div class="actions pull-right">
                            <input id="addComputerButton" type="submit" value="<spring:message code="cdb.form.add"/>" class="btn btn-primary">
                            <spring:message code="cdb.form.or"/>
                            <a href="springcdb" class="btn btn-default"><spring:message code="cdb.form.cancel"/></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
</body>
</html>
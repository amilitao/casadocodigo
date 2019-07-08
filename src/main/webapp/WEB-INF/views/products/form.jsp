<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%-- <spring:hasBindErrors name="product"> --%>

	<form:form action="/casadocodigo/produtos" method="post" commandName="product">
		<div>
			<label for="title">Titulo  </label>
			<form:input path="title"/>
			<form:errors path="title" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea path="description" rows="10" cols="20"/>
			<form:errors path="description" />
		</div>
		<div>
			<label for="pages">Número de paginas</label> <input type="text"
				name="pages" id="pages" />
				<form:errors path="pages" />
		</div>


		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> <input
					type="text" name="prices[${status.index}].value"
					id="price_${bookType}" /> <input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
										
			</div>
		</c:forEach>

		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
		
	
	<%-- <ul>
		<c:forEach var="error" items="${errors.allErrors}">
			<li>
			<spring:message code="${error.code}" text="${error.defaultMessage}" />
			</li>
		</c:forEach>
	</ul> --%>
	
<%-- 	</spring:hasBindErrors> --%>
</body>
</html>
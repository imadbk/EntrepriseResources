<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp" />

		<div class="page-header">
			<h2>Connexion collaborateur</h2>
		</div>
		<div class="row row-centered">
			<div class="col-xs-6 col-centered col-fixed">
				<form id='loginForm' name='loginForm' class="form-horizontal"
					action="<c:url value='/j_spring_security_check' />" method='POST'>
					<fieldset>
						<span class="type"> Identifiant :</span>
						<div class="form-group">
							<div class="input-group ">
								<input id='username' name='username' type="text"
									class="form-control" placeholder="">
							</div>
						</div>
						<br />
						<div class="form-group">
							<span class="type">Mot de passe :</span>
							<div class="input-group ">
								<input name="password" type="password" class="form-control"
									placeholder="">
							</div>
						</div>
						<c:if test="${not empty error}">
							<div class="error">
								<spring:message code="${error}" />
							</div>
						</c:if>
						<br /> <input class="btn btn-small" type="submit"
							value="Connexion">
						<div class="btn">
							<a href="${pageContext.servletContext.contextPath}/reset">Mot
								de passe oublié</a>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
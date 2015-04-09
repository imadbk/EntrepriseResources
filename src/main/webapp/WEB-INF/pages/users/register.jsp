<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>

</head>
<body>

	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp"></jsp:include>
		<div id="message" class="message <c:if test="${error}">error</c:if>">
			<c:if test="${not empty message}">
				<spring:message code="${message}" />
			</c:if>
		</div>
		<div class="buttonAdmin">
			<div class="span3 offset2">
				<a href="${pageContext.servletContext.contextPath}/users/list"
					class="btn btn-primary">Retour</a>
			</div>
		</div>
		<br /> <br>
		<div class="row">
			<form class="form-horizontal" id="user-form"
				action="${pageContext.servletContext.contextPath}/users/save"
				method="GET">
				<fieldset>
					<legend>Ajout d'un utilisateur</legend>
					<div class="form-group">
						<label for="inputUsername" class="col-lg-2 control-label">Nom
							utilisateur</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputUsername"
								name="username" placeholder="Nom utilisateur">
						</div>
					</div>
					<div class="form-group">
						<label for="inputLastname" class="col-lg-2 control-label">Nom
						</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputLastname"
								name="lastname" placeholder="Nom">
						</div>
					</div>
					<div class="form-group">
						<label for="inputFirstname" class="col-lg-2 control-label">Prénom
						</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputFirstname"
								name="firstname" placeholder="Prénom">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">Email</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputEmail"
								name="email" placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="select" class="col-lg-2 control-label">Roles</label>
						<div class="col-lg-10">
							<select multiple="multiple" class="form-control" id="select"
								name="roles">
								<option value="CONFIG">Configurateur</option>
								<option value="ADMIN">Administrateur</option>
								<option value="USER">Utilisateur</option>

							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-lg-2 control-label">Mot
							de passe</label>
						<div class="col-lg-10">
							<input type="password" class="form-control" id="inputPassword"
								placeholder="Mot de passe" name="password">
						</div>
					</div>
					<div class="form-group">
						<label for="input_c_password" class="col-lg-2 control-label">
							Confirmation</label>
						<div class="col-lg-10">
							<input type="password" class="form-control" id="c_password"
								placeholder="Confirmation" name="c_password">
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-2">
							<button type="reset" class="btn btn-default">Annuler</button>
							<button type="submit" class="btn btn-primary">Envoyer</button>
						</div>

					</div>
				</fieldset>
			</form>

		</div>
	</div>
	<script src="<c:url value="/resources/js/jquery.validate.min.js"/> "></script>
	<script src="<c:url value="/resources/js/userForm.js"/> "></script>
		
</html>
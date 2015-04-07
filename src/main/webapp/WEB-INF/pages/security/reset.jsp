<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp"></jsp:include>


		<div class="page-header">
			<h2>Réinitialisation mot de passe</h2>
		</div>
		<div class="row row-centered">
			<d+iv class="col-xs-6 col-centered col-fixed">
				<form class="form-horizontal" action='' method="POST">
					<fieldset>
						<div class="control-group">
							<!-- Username -->
							<label class="control-label" for="username">Nom
								utilisateur</label>
							<div class="controls">
								<input type="text" id="username" name="username" placeholder=""
									class="input-xlarge">
								<p class="help-block"></p>
							</div>
						</div>

						<div class="control-group">
							<!-- E-mail -->
							<label class="control-label" for="email">E-mail</label>
							<div class="controls">
								<input type="text" id="email" name="email" placeholder=""
									class="input-xlarge">
								<p class="help-block"></p>
							</div>
						</div>


						<div class="control-group">
							<!-- Button -->
							<div class="controls">
								<a href="${pageContext.servletContext.contextPath}/reset"
									class="btn btn-primary">Envoyer un nouveau mot de passe</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
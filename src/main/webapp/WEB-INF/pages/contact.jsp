<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp" />
		<div class="buttonAdmin">
			<div class="span3 offset2">
				<a href="#" class="btn btn-primary"></a>
			</div>
		</div>
		<br />
		<div class="row">
			<form class="form-horizontal">
				<fieldset>
					<legend>Laissez nous un message</legend>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">Email</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputEmail"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="inputLastname" class="col-lg-2 control-label">Nom</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputLastname"
								placeholder="Nom">
						</div>
					</div>
					<div class="form-group">
						<label for="inputFirstname" class="col-lg-2 control-label">Pr�nom</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputFirstname"
								placeholder="Pr�nom">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPhone" class="col-lg-2 control-label">T�l�phone</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputPhone"
								placeholder="T�l�phone">
						</div>
					</div>

					<div class="form-group">
						<label for="textArea" class="col-lg-2 control-label">Message</label>
						<div class="col-lg-10">
							<textarea class="form-control" rows="3" id="textArea"></textarea>
							<span class="help-block">Saisissez votre message
								ci-dessus, nous t�cherons � y r�pondre le plus vite possible.</span>
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
</body>
</html>
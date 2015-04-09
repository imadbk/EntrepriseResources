<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
					class="btn btn-primary">Supprimer</a> <a
					href="${pageContext.servletContext.contextPath}/users/list"
					class="btn btn-primary">Retour</a>

			</div>
		</div>
		<br /> <br>
		<div class="row">
			<form class="form-horizontal" id="user-form"
				action="${pageContext.servletContext.contextPath}/users/saveEdit"
				method="POST">
				<fieldset>
					<legend>Mise à jour d'un utilisateur</legend>
					<div class="form-group">
						<label for="inputUsername" class="col-lg-2 control-label">Nom
							utilisateur</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputUsername"
								 name="username"
								placeholder="Nom utilisateur" value="${user.username}">
						</div>
					</div>
					<div class="form-group">
						<label for="inputLastname" class="col-lg-2 control-label">Nom
						</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputLastname"
								name="lastname" placeholder="Nom" value="${user.lastname}">
						</div>
					</div>
					<div class="form-group">
						<label for="inputFirstname" class="col-lg-2 control-label">Prénom
						</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputFirstname"
								name="firstname" placeholder="Prénom" value="${user.firstname}">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-2 control-label">Email</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputEmail"
								name="email" placeholder="Email" value="${user.email}">
						</div>
					</div>
					<div class="form-group">
						<label for="select" class="col-lg-2 control-label">Roles</label>
						<div class="col-lg-10">
							<select multiple="multiple" class="form-control" id="select"
								name="roles">
								<option value="CONFIG"
									<c:if test="${fn:containsIgnoreCase(roles, 'CONFIG')}"> selected="selected" </c:if>>Configurateur</option>
								<option value="ADMIN"
									<c:if test="${fn:containsIgnoreCase(roles, 'ADMIN')}"> selected="selected" </c:if>>Administrateur</option>
								<option value="USER"
									<c:if test="${fn:containsIgnoreCase(roles, 'USER')}"> selected="selected" </c:if>>Utilisateur</option>

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
						<label for="inputPassword" class="col-lg-2 control-label">
							Confirmation</label>
						<div class="col-lg-10">
							<input type="password" class="form-control" id="c_password"
								name="c_password" placeholder="Confirmation">
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-10 col-lg-offset-2">
							<button type="submit" class="btn btn-primary">Enregistrer</button>
						</div>

					</div>
				</fieldset>
			</form>

		</div>
	</div>
	<script src="<c:url value="/resources/js/jquery.validate.min.js"/> "></script>
	<script type="text/javascript">
	  
	  // When the browser is ready...
	  $(function() {
	  
	    // Setup form validation on the #register-form element
	    $("#user-form").validate({
	    
	        // Specify the validation rules
	        rules: {
	            firstname: "required",
	            lastname: "required",
	            email: {
	                required: true,
	                email: true
	            },
	            password: {
	                minlength: 8
	            },
	            c_password: { 
	                equalTo: "#inputPassword"
	            }, 
	            roles : { valueNotEquals: "default" }

	        },
	        
	        // Specify the validation error messages
	        messages: {
	            firstname: "Un prénom est requis",
	            lastname: "Un nom est requis",
	            password: {
	                minlength: "le mot de passe doit être superieur à 8 caractères"
	            },
	            c_password: {
	                equalTo: "La confirmation et le mot de passe sont déffirents"
	            },
	            email: "Merci d'insérer une adresse mail valide ",
	            roles: { valueNotEquals: "Choisissez au moins un role" }
	        },
	        
	        submitHandler: function(form) {
	            form.submit();
	        },

	        errorElement: "div",
	        wrapper: "div",  // a wrapper around the error message
	        errorPlacement: function(error, element) {
	            offset = element.offset();
	            error.insertAfter(element)
	         //   error.addClass('message');  // add a class to the wrapper
	            error.css('color', 'red');

	        }
	    });

	  });
	  
	//add the rule here
	  $.validator.addMethod("valueNotEquals", function(value, element, arg){
	   return null != value;
	  }, "Value must not equal arg.");


	  

	</script>

</body>
</html>
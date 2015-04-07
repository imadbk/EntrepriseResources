<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="navbar navbar-default">
	<ul class="nav navbar-nav">
		<li><a href="${pageContext.servletContext.contextPath}/">Accueil</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/contact">Contacts</a></li>
	</ul>
	<sec:authorize access="isAnonymous()">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.servletContext.contextPath}/login">Connexion</a></li>
		</ul>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown "><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i
					class="fa fa-user fa-fw grey"></i> <sec:authorize
						access="isAuthenticated()">
						<sec:authentication property="principal.username" />
					</sec:authorize><span class="caret"></span>
			</a>
				<ul class="dropdown-menu">

					<li><a href="${pageContext.servletContext.contextPath}/logout">Déconnexion</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/users/list">Gestion des utilisateurs</a></li>
				</ul></li>
		</ul>
	</sec:authorize>
</div>

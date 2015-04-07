<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp"></jsp:include>
		<h2>Utilisateur connecté</h2>
	</div>
</body>
</html>
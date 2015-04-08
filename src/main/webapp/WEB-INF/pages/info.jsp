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
    <h1>Information</h1> 
   </div>
		<div id="message" class="message <c:if test="${error}">error</c:if>">
			<c:if test="${not empty message}">
				<spring:message code="${message}" />
			</c:if>
		</div>
	</div>
</body>
</html>
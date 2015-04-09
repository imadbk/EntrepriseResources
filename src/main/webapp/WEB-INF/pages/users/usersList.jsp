<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/pages/includes/header.jsp">
	<jsp:param name="titre" value="MonEntreprise" />
</jsp:include>
<script type='text/javascript'
	src="<c:url value="/resources/js/grid.locale-fr.js"/>"></script>
<script type='text/javascript'
	src="<c:url value="/resources/js/jquery.jqGrid.min.js"/> "></script>
<script type="text/javascript">
	$(function() {
		$("#list").jqGrid(
				{
					url : "getUsers",
					datatype : "json",
					loadonce : true,
					colNames : [ '', 'Identifiant', 'Utilisateur', 'Roles',
							'Actif', 'Bloqué' ],
					colModel : [ {
						name : 'userid',
						index : 'userid',
						width : 20,
						search : false,
						align : 'center',
						resizable : false,
                        formatter: editCheckbox,
						multiselect : true
					}, {
						name : 'username',
						index : 'username',
						width : 100,
						align : 'left',
						edittype : 'text',
						resizable : false,
		                formatter: editLink
					}, {
						name : 'name',
						index : 'name',
						align : 'left',
						resizable : false,
						width : 100
					}, {
						name : 'roles',
						index : 'roles',
						align : 'center',
						resizable : false,
						width : 100
					}, {
						name : 'enabled',
						index : 'enabled',
						width : 60,
						align : 'center',
						resizable : false,
						edittype : 'checkbox',
						editoptions : {
							value : "True:False"
						},
						formatter : "checkbox",
						formatoptions : {
							disabled : true
						},
						stype : 'select',
						sortable : true,
						searchoptions : {
							sopt : [ 'eq', 'ne' ],
							value : 'true:Vrai;false:Faux'
						},
						editable : false
					}, {
						name : 'locked',
						index : 'locked',
						width : 60,
						align : 'center',
						resizable : false,
						edittype : 'checkbox',
						editoptions : {
							value : "True:False"
						},
						formatter : "checkbox",
						formatoptions : {
							disabled : true
						},
						stype : 'select',
						sortable : true,
						searchoptions : {
							sopt : [ 'eq', 'ne' ],
							value : 'true:Vrai;false:Faux'
						},
						editable : false
					} ],
					pager : "#pager",
					rownumbers : false,
					// 					pginput : false,
					rowList : [ 10, 15, 20, 30 ],
					rowNum : 15,
					height : 'auto',
					width : 930,
					scrollOffset : 0,
					viewrecords : true,
					gridview : true
				}).navGrid("#pager", {
			edit : false,
			add : false,
			del : false,
			search : true
		});
	});
	    function editLink(cellValue, options, rowdata, action) {
	        return "<a href='edit?id=" + rowdata.userid
	                + "'>" + cellValue + "</a>";
	    }

	    function editCheckbox(cellValue, options, rowdata, action) {
	        return "<input type='checkbox' id='del-" + rowdata.userid
                + "' name='" + rowdata.userid + "'>";
	    }
</script>
</head>
<body>

	<div class="container">
		<jsp:include page="/WEB-INF/pages/includes/menu.jsp"></jsp:include>

		<div class="pageContain">
			<div class="buttonAdmin">
				<a href="${pageContext.servletContext.contextPath}/users/add"
					class="btn btn-primary">Ajouter</a>
			</div>
			<div class="row">
				<div class="col-xs-12" id="tables">
					<div class="page-header">
						<h1>
							Liste des utilisateurs : <span id="nbClients"></span>
						</h1>
					</div>
					<table id="list">
						<tr>
							<td></td>
						</tr>
					</table>
					<div id="pager"></div>
				</div>
			</div>
		</div>


	</div>

</body>
</html>
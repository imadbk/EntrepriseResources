<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>${param.titre}</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/> " rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/ui.jqgrid.css"/> " rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/ui.jqgrid-bootstarp.css"/> " rel="stylesheet" type="text/css">
        <!--[if lt IE 9]>
           <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->   

        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="<c:url value="/resources/js/jquery-ui.js"/> "></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/> "></script>
        <link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet" type="text/css">

<style type="text/css">
div.buttonAdmin {
    float: right;
    margin: 15px 0px 10px;
    padding-right: 15px;
}
body{
background-image: url('${pageContext.request.contextPath}/resources/img/background.jpg');
}
 .container{
 background-color: rgb(255,255,255);
 height: 900px;
}
/* centered columns styles */
.row-centered {
    text-align:center;
}
.col-centered {
    display:inline-block;
    float:none;
    /* reset the text-align */
    text-align:left;
    /* inline-block space fix */
    margin-right:-4px;
}
</style>


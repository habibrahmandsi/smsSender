<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.dsoft.util.Utils" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%
    final String contextPath = request.getContextPath();

%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="<%= contextPath %>/resources/staticContent/assets/js/html5.js"></script>
    <!-- Javascript -->
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jquery-1.8.2.min.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jquery.flexslider.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jquery.tweet.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jflickrfeed.js"></script>

    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jquery.ui.map.min.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/jquery.quicksand.js"></script>
    <script src="<%= contextPath %>/resources/staticContent/assets/prettyPhoto/js/jquery.prettyPhoto.js"></script>
    <%--<link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/js/custom-home.js">--%>
    <script src="<%= contextPath %>/resources/staticContent/assets/js/scripts.js"></script>

    <!-- CSS -->
<%--    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Sans">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">--%>
    <link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/prettyPhoto/css/prettyPhoto.css">
    <link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/css/flexslider.css">
    <link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%= contextPath %>/resources/staticContent/assets/css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>

    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%= contextPath %>/resources/staticContent/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%= contextPath %>/resources/staticContent/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%= contextPath %>/resources/staticContent/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%= contextPath %>/resources/staticContent/assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footerDemo"/>
</body>
</html>
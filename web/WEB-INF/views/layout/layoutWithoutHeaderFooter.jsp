<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.dsoft.util.Utils" %>
<%@ page import="com.dsoft.util.Constants" %>
<%@ page import="com.dsoft.entity.Role" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
    final String contextPath = request.getContextPath();
%>

<!DOCTYPE html lang="en">
<link rel="shortcut icon" type="image/x-icon" href="<%= contextPath %>/resources/images/new_logo.png" />
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login</title>

    <!-- Page-Level Plugin CSS - Forms -->

    <!-- SB Admin CSS - Include with every page -->

    <link href='<%= contextPath %>/resources/theme/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
    <link href='<%= contextPath %>/resources/theme/font-awesome/css/font-awesome.css' rel='stylesheet' type='text/css'>
    <link href='<%= contextPath %>/resources/theme/css/sb-admin.css' rel='stylesheet' type='text/css'>
    <link href='<%= contextPath %>/resources/css/style.css' rel='stylesheet' type='text/css'>
    <!-- Core Scripts - Include with every page -->
    <script src="<%= contextPath %>/resources/theme/js/jquery-1.10.2.js"></script>
    <script src="<%= contextPath %>/resources/theme/js/bootstrap.min.js"></script>


</head>
<body>

<div id="wrapper">

    <div class="container">
        <br>
        <%@ include file="/WEB-INF/views/message.jsp" %>
        <tiles:insertAttribute name="body"/>
    </div>
</div>

</body>
</html>
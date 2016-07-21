<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
    final String contextPath = request.getContextPath();
%>

<!DOCTYPE html lang="en">
<link rel="shortcut icon" type="image/x-icon" href="<%= contextPath %>/resources/images/new_logo.png"/>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Product Key Validation</title>

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


<div class="row">
    <br>
    <%@ include file="/WEB-INF/views/message.jsp" %>
    <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">
                <a href="#"><img style="width: 70px; height: 50px;"
                                 src="<%= contextPath %>/resources/images/Icon-Keys.png">Product Key</a>
            </div>
            <div class="panel-body">
                <form:form id="productKeyValidationForm" method="post" action="./productKeyValidation.do"
                           commandName="productKeyValidation">
                    <div class="form-group">
                        <label><spring:message code="user.form.userName"/></label>
                        <form:input path="userName" cssClass="form-control"/>
                    </div>

                    <div class="form-group">
                        <label><spring:message code="superAdmin.form.privateKey"/></label>
                        <form:password path="privateKey" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label><spring:message code="superAdmin.form.productKey"/></label>
                        <form:input path="productKey" cssClass="form-control"/>
                    </div>
                    <div id="errorId"><font color="red">${error}</font></div>
                    <!-- Change this to a button or input when using this as a form -->
                    <button class="btn btn-success" type="submit"><spring:message code="button.submit"/></button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>

    /*  $(document).ready(function() {
     $('.login').click(function(e){
     e.preventDefault();
     $('#loginForm').submit();
     });

     });*/


</script>
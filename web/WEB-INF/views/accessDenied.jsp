<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>

<div class="page not-found">
    <br>
    <br>
    <div>
        <center>
            <pre>We are sorry for the inconvenience. Please contact with system administrator.You have not the right permission to view this page.<br>Please contact Customer support:</pre>

        </center>
    </div>

    <div>
        <center>
          <spring:message code="contact.point"/>
        </center>
    </div>
</div>
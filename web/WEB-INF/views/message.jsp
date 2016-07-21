<%@ page import="com.dsoft.util.Utils"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    pageContext.setAttribute("greenMessage", Utils.getGreenMessage(request));
    pageContext.setAttribute("redMessage", Utils.getErrorMessage(request));
    pageContext.setAttribute("blueMessage", Utils.getBlueMessage(request));

%>

<!-- ==================== ALERTS ROW ==================== -->
<%--    <c:if test="${not empty redMessage}">
    <div class="alert">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Warning!</strong> Best check yo self, you're not looking too good.
    </div>
    </c:if>--%>
    <c:if test="${not empty greenMessage}">
    <div class="alert alert-success successfulOrErrorMessages">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong><spring:message code="message.successfull"/></strong> ${fn:replace(fn:replace(greenMessage,'[', ' '),']',' ')}
    </div>
    </c:if>
    <c:if test="${not empty redMessage}">
    <div class="alert alert-danger successfulOrErrorMessages">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong><spring:message code="message.alart"/></strong> ${fn:replace(fn:replace(redMessage,'[', ' '),']',' ')}
    </div>
    </c:if>
     <c:if test="${not empty blueMessage}">
    <div class="alert alert-info successfulOrErrorMessages">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong><spring:message code="message.blue"/></strong> ${fn:replace(fn:replace(blueMessage,'[', ' '),']',' ')}
    </div>
     </c:if>
<!-- ==================== END OF ALERTS ROW ==================== -->
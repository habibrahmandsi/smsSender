<%@ page import="java.security.Principal" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    final String contextPath = request.getContextPath();
    final Principal loggedUser = request.getUserPrincipal();
%>

<!-- ==================== TOP MENU ==================== -->
<%--<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="#"><img alt="" class="logoDiv" src="<%= contextPath %>/resources/images/new_logo.png">&nbsp;<strong class="brandBold"><spring:message code="adminHeader.bpc"/></strong>&nbsp;<spring:message code="adminHeader.header"/></a>
            <div class="nav pull-right">
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>--%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a id="headerTitle" class="brand" href="#"><img alt="" class="logoDiv" src="<%= contextPath %>/resources/images/new_logo.png">&nbsp;<strong class="bpcClass"><spring:message code="adminHeader.bpc"/></strong>&nbsp;<spring:message code="adminHeader.header"/></a>
            <div class="nav pull-right">
                <form class="navbar-form">
                    <div class="input-append">
                        <div class="collapsibleContent">
                        </div>
                    </div>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<!-- ==================== END OF TOP MENU ==================== -->
<%--
<div id="header" style="padding: 5px 0px;">
    <div id="company_logo">
    </div>
    <label>
        <font class="headerText"><b><spring:message code="adminHeader.bpc"/></b>&nbsp;<spring:message
                code="adminHeader.header"/></font>
    </label>
    <br/><br/>

    <div style="float: left; padding-left: 20px; margin-top: -9px;color: white;">
    </div>

</div>--%>

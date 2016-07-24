<%@ page import="com.dsoft.entity.Role" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%
    final String contextPath = request.getContextPath();
%>

<!-- ==================== MAIN MENU ==================== -->
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="menu.contractInfo"/><span
                        class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="./upsertPerson.do"><i class="fa fa-money fa-fw"></i>
                            &nbsp;<spring:message code="menu.newContact"/></a>
                    </li>
                    <li>
                        <a href="./personList.do"><i class="fa fa-money fa-fw"></i>&nbsp;<spring:message
                                code="menu.contact.list"/></a>
                    </li>
                    <li>
                        <a href="./uploadContact.do"><i class="fa fa-money fa-fw"></i>&nbsp;<spring:message
                                code="menu.upload.contact.list"/></a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
         <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="menu.userGroup"/><span
                        class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="./upsertGroup.do"><i class="fa fa-money fa-fw"></i>
                            &nbsp;<spring:message code="menu.newUserGroup"/></a>
                    </li>
                    <li>
                        <a href="./groupList.do"><i class="fa fa-money fa-fw"></i>&nbsp;<spring:message
                                code="menu.userGroup.list"/></a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
        </ul>
        <!-- /#side-menu -->
    </div>
    <!-- /.sidebar-collapse -->
</nav>
<!-- /.navbar-static-side -->

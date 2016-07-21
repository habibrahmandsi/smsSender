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
            <%-- <security:authorize access="hasAnyRole(
             '${superAdmin}','${adminUser}','${CreateSales}','${SalesInfo}','${DeleteSales}','${SalesReturn}','${DeleteSalesReturn}','${CreatePurchase}','${PurchaseInfo}','${DeletePurchase}','${PurchaseReturnInfo}','${DeletePurchaseReturn}')">
 --%>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="menu.salesAndPurchase"/><span
                        class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="./upsertSales.do"><i class="fa fa-money fa-fw"></i>
                            &nbsp;<spring:message code="menu.sales"/></a>
                    </li>
                    <li>
                        <a href="./salesList.do?opt=0"><i class="fa fa-money fa-fw"></i>&nbsp;<spring:message
                                code="menu.sales.information"/></a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <%--</security:authorize>--%>
           <%-- <li>
                <a href="tables.html"><i class="fa fa-table fa-fw"></i> <spring:message code="menu.administration"/>
                    <span class="fa arrow"></span>
                </a>

                <ul class="nav nav-second-level">
                    <li>
                        <a href="./settings.do"><spring:message code="menu.administration.settings"/></a>
                    </li>
                    <li>
                        <a href="./userList.do"><spring:message code="menu.administration.user"/></a>
                    </li>
                    <li>
                        <a href="./companyList.do"><spring:message code="menu.administration.new.company"/></a>
                    </li>
                    <li>
                        <a href="./productList.do"><spring:message code="product.list.header"/></a>
                    </li>
                    <li>
                        <a href="./productGroupList.do"><spring:message
                                code="menu.administration.new.productGroup"/></a>
                    </li>
                    <li>
                        <a href="./productTypeList.do"><spring:message code="menu.administration.new.productType"/></a>
                    </li>
                    <li>
                        <a href="./unitOfMeasureList.do"><spring:message
                                code="menu.administration.new.unitOfMeasure"/></a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="tables.html"><i class="fa fa-table fa-fw"></i> <spring:message code="menu.reporting"/>
                    <span class="fa arrow"></span>
                </a>

                <ul class="nav nav-second-level">
                    <li>
                        <a href="./purchaseReport.do?opt=0"><spring:message code="purchase.report.header"/></a>
                    </li>
                    <li>
                        <a href="./purchaseReport.do?opt=1"><spring:message code="purchase.return.report.header"/></a>
                    </li>
                    <li>
                        <a href="./purchaseReport.do?opt=2"><spring:message code="purchase.unposted.report.header"/></a>
                    </li>
                    <li>
                        <a href="./saleReport.do?opt=0"><spring:message code="menu.reporting.sale"/></a>
                    </li>
                    <li>
                        <a href="./saleReport.do?opt=1"><spring:message code="menu.reporting.sale.return"/></a>
                    </li>
                    <li>
                        <a href="./saleReport.do?opt=2"><spring:message code="menu.reporting.unposted.sale"/></a>
                    </li>
                    <li>
                        <a href="./saleReport.do?opt=3"><spring:message code="menu.reporting.unposted.sale.return"/></a>
                    </li>
                     <li>
                        <a href="./stockReport.do"><spring:message code="menu.reporting.stock"/></a>
                    </li>
                    <li>
                        <a href="./incomeReport.do"><spring:message code="income.report.header"/></a>
                    </li>
                </ul>

                <!-- /.nav-second-level -->
            </li>--%>
        </ul>
        <!-- /#side-menu -->
    </div>
    <!-- /.sidebar-collapse -->
</nav>
<!-- /.navbar-static-side -->

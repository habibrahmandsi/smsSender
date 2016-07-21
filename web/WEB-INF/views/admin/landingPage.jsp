<%@ page import="com.dsoft.entity.Role" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%
    final String contextPath = request.getContextPath();
%>
<script src="<%= contextPath %>/resources/js/common/landingPage.js"  type="text/javascript"></script>

<title><spring:message code="landingPageTitle.title"/></title>


<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading dangerAlartHead">
                <%--<spring:message code="product.list.limited.header"/>--%>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">

                        <table id="limitedStockProductList" class="table table-striped table-hover dataTable">
                        </table>
                        <a href="./clearAllData.do" class="btn btn-primary" style="width: 98%;">Clear All Data</a>
                    </div>
                    <!-- /.col-lg-6 (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
    </div>

    <!-- /.col-lg-12 -->
</div>
<script>
     limitQty = +'${settings.stockLimitAlarmQty}';
    var expireDateAlarmDay = '${settings.expireDateAlarmDay}';
</script>
<!-- /.row -->
<!-- ==================== END OF COMMON ELEMENTS ROW ==================== -->

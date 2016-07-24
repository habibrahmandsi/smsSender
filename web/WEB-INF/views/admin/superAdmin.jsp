<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>
<title><spring:message code="superAdmin.header"/></title>

<!-- ==================== COMMON ELEMENTS ROW ==================== -->


<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <spring:message code="superAdmin.header"/>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form:form id="superAdminForm" commandName="adminBean" method="post"
                                   enctype="multipart/form-data">
                            <input type="file" name="productGroupFile"/>
                            <br/>

                            <button class="btn btn-success productUpload"><spring:message
                                    code="button.contact.upload"/></button>
                            <br/>

                        </form:form>
                        <c:if test="${not empty personList}">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Person Name</th>
                                    <th>Designation</th>
                                    <th>Address</th>
                                    <th>MobileNo</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${personList}" var="data" varStatus="idx">
                                    <tr>
                                        <td>${idx.index+1}</td>
                                        <td>${data.name}</td>
                                        <td>${data.designation}</td>
                                        <td>${data.address}</td>
                                        <td>${data.mobileNO}</td>
                                        <td>
                                            <c:if test="${data.saved == true}">
                                                <img alt="Saved" title="Saved" class="iconInsideTable"
                                                     src="<%= contextPath %>/resources/images/rightIcon.jpeg">
                                            </c:if>
                                            <c:if test="${data.saved == false}">
                                                <img alt="Saved" title="Saved" class="iconInsideTable"
                                                     src="<%= contextPath %>/resources/images/crossIcon.jpeg">
                                            </c:if>


                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </c:if>


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
    $(document).ready(function () {
        $(".productUpload").click(function () {
            $("#superAdminForm").attr("action", "./uploadContact.do?opt=1");
            $("#superAdminForm").submit()

        });
    });

</script>
<!-- /.row -->
<!-- ==================== END OF COMMON ELEMENTS ROW ==================== -->

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>
<title><spring:message code="person.header"/></title>

<%--<script src="<%= contextPath %>/resources/js/common/company.js"  type="text/javascript"></script>--%>

<!-- ==================== COMMON ELEMENTS ROW ==================== -->


    <!-- /.row -->
    <div class="row">
        <form:form method="post" id="personForm" commandName="person">
                <form:hidden path="id"/>
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="person.header"/> Form
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">

                            <div class="form-group">
                                <label><spring:message code="person.form.name"/></label>
                                <form:input path="name" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.father.name"/></label>
                                <form:input path="fatherName" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.mother.name"/></label>
                                <form:input path="motherName" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="person.form.designation"/></label>
                                <form:input path="designation" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.address"/></label>
                                <form:input path="address" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="person.form.mobileNO"/></label>
                                <form:input path="mobileNO" cssClass="form-control"/>
                            </div>

                            <button class="btn btn-danger" type="reset"><spring:message code="button.cancel"/></button>
                            <button class="btn btn-success" type="submit"><spring:message
                                    code="button.submit"/></button>


                        </div>

                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>

            <!-- /.col-lg-12 -->
        </div>
        </form:form>
        <!-- /.row -->
    <!-- ==================== END OF COMMON ELEMENTS ROW ==================== -->

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>
<title><spring:message code="user.header"/></title>

<script src="<%= contextPath %>/resources/js/common/user.js"  type="text/javascript"></script>

<!-- ==================== COMMON ELEMENTS ROW ==================== -->


    <!-- /.row -->
    <div class="row">
        <form:form method="post" id="userForm" commandName="user">
                    <form:hidden path="id"/>
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="user.header"/> Form
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">

                            <div class="form-group">
                                <label><spring:message code="user.form.name"/></label>
                                <form:input path="name" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.userName"/></label>
                                <form:input path="userName" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.password"/></label>
                                <form:password path="password" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.confirmPassword"/></label>
                                <input type="password" class="form-control">
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
                                <label><spring:message code="user.form.sex"/></label>
                                <form:select path="sex" cssClass="form-control">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Others">Others</option>
                                </form:select>

                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.age"/></label>
                                <form:input path="age" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.religion"/></label>
                                <form:select path="religion" cssClass="form-control">
                                    <option value="Islam">Islam</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Others">Others</option>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.nationalIdNo"/></label>
                                <form:input path="nationalIdNo" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.dof"/></label>

                                <div class="input-append date" id="dp1-1" data-date="${dateOfBirth}" data-date-format="${dateFormateForJs}">
                                    <div class="input-group">
                                        <form:input path="dateOfBirth" cssClass="form-control" />
                                        <span class="add-on input-group-btn"><img class="btn btn-default cal-btns" src="<%= contextPath %>/resources/images/cal.gif" /></span>
                                        <span class="input-group-btn"><img class="btn btn-default cal-btns cal-btns-last opaque crossBtn" src="<%= contextPath %>/resources/images/delete.png"/></span>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.address"/></label>
                                <form:input path="address" cssClass="form-control"/>
                            </div>

                            <button class="btn btn-danger" type="reset"><spring:message code="button.cancel"/></button>
                            <button class="btn btn-success" type="submit"><spring:message
                                    code="button.submit"/></button>


                        </div>
                        <!-- /.col-lg-6 (nested) -->
                        <div class="col-lg-6">

                            <div class="form-group">
                                <label><spring:message code="user.form.isActive"/></label>
                                <form:checkbox path="active" cssClass="form-control" cssStyle="width: 20px;"/>
                            </div>


                            <div class="form-group">
                                <label><spring:message code="user.form.email"/></label>
                                <form:input path="email" cssClass="form-control"/>
                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.joiningDate"/></label>

                                <div class="input-append date" id="dp1-2" data-date="${joiningDate}" data-date-format="${dateFormateForJs}">
                                    <div class="input-group">
                                        <form:input path="joiningDate" cssClass="form-control" />
                                        <span class="add-on input-group-btn"><img class="btn btn-default cal-btns" src="<%= contextPath %>/resources/images/cal.gif" /></span>
                                        <span class="input-group-btn"><img class="btn btn-default cal-btns cal-btns-last opaque crossBtn" src="<%= contextPath %>/resources/images/delete.png"/></span>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <label><spring:message code="user.form.startingSalary"/></label>
                                <form:input path="startingSalary" cssClass="form-control"/>
                            </div>

                             <div class="form-group">
                                <label><spring:message code="user.form.currentSalary"/></label>
                                <form:input path="currentSalary" cssClass="form-control"/>
                            </div>

                             <div class="form-group">
                                <label><spring:message code="user.form.maxDiscountPercent"/></label>
                                <form:input path="maxDiscountPercent" cssClass="form-control"/>
                            </div>
                            <!-- /.col-lg-6 (nested) -->
                           <div class="form-group">
                                <label><spring:message code="user.form.role"/></label>
                               <form:select path="role" cssClass="form-control">
                                   <c:forEach items="${roleList}" var="pg">
                                       <c:choose>
                                       <c:when test="${user.role == pg}" >
                                           <option value="${pg}" selected>${pg}</option>
                                       </c:when>

                                       <c:otherwise>
                                           <option value="${pg}">${pg}</option>
                                       </c:otherwise>
                                       </c:choose>
                                   </c:forEach>
                               </form:select>
                            </div>
                            <!-- /.col-lg-6 (nested) -->
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

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>
<title><spring:message code="group.header"/></title>

<!-- /.row -->
<div class="row">
    <form:form method="post" id="groupForm" commandName="group">
        <form:hidden path="id"/>
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="group.header"/>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="form-group">
                                <label><spring:message code="group.form.name"/></label>
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Mobile No</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${group.personList}" var="obj" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td>${obj.name}</td>
                                        <td>${obj.mobileNO}</td>
                                        <td>
                                             <div class="chkBoxDiv">
                                            <input name="personList[${i.index}].id" value="${obj.id}"class="hidden personId"/>
                                            <form:checkbox path="personList[${i.index}].active" cssClass="membersChkBox"/>
                                            </div>
                                        </td>
                                    </tr>

                            <%--<div class="checkbox">
                                <input name="rolePermissionList[${i.index}].feature.id" value="${obj.id}"class="hidden featureId"/>
                                <label><form:checkbox path="rolePermissionList[${i.index}].active" cssClass="featureChkBox"/>${obj.description}</label>
                            </div>--%>

                        </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3"></div>
                        <div class="col-lg-3">
                            <button class="btn btn-danger btn-block" type="reset"><spring:message
                                    code="button.cancel"/></button>
                        </div>
                        <div class="col-lg-3">
                            <button class="btn btn-success btn-block" type="submit"><spring:message
                                    code="button.submit"/></button>
                        </div>
                        <div class="col-lg-3"></div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>

            <!-- /.col-lg-12 -->
        </div>
    </form:form>
    <!-- /.row -->

</div>
<!-- ==================== END OF COMMON ELEMENTS ROW ==================== -->
<script>
    var memberList = {};
    <c:forEach items="${groupPersonList}" var="obj" varStatus="idx">
    var key = '${obj.person.id}';
    <c:if test="${not empty obj.person}">
    memberList[key] = '${obj.person.mobileNO}';
    </c:if>
    </c:forEach>

    $(document).ready(function(){
        console.log("SMNLOG:memberList:"+JSON.stringify(memberList));
        $("div.chkBoxDiv").each(function(){
            var personId = $(this).find("input.personId").val();
            console.log("SMNLOG:personId:"+personId);
            if(typeof memberList[personId] != 'undefined'){
                console.log(" ---------- Found for personId:"+personId+" --------------");
                $(this).find("input.personId").val();
                $(this).find("input.membersChkBox").prop("checked",true);
            }else{
                $(this).find("input.membersChkBox").prop("checked",false);
            }
        })
    });

</script>


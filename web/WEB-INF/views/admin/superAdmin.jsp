<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    final String contextPath = request.getContextPath();
%>
<title><spring:message code="superAdmin.header"/></title>

<script src="<%= contextPath %>/resources/js/common/companyList.js" type="text/javascript"></script>

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
                                    code="button.product.upload"/></button>
                            <button class="btn btn-success productGroupUpload"><spring:message
                                    code="button.productGroup.upload"/></button>
                             <button class="btn btn-success companyUpload"><spring:message
                                    code="button.company.upload"/></button>
                            <button class="btn btn-success productWithCnameUpload"><spring:message
                                    code="button.product.company.upload"/></button>
                            <br/>

                        </form:form>
                        <c:if test="${not empty productGroupList}">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Product group name</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${productGroupList}" var="data" varStatus="idx">
                                    <tr>
                                        <td>${idx.index+1}</td>
                                        <td>${data.name}</td>
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

                     <c:if test="${not empty companyList}">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Company name</th>
                                    <th>Agent Name</th>
                                    <th>Agent Cell No</th>
                                    <th>Address</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${companyList}" var="data" varStatus="idx">
                                    <tr>
                                        <td>${idx.index+1}</td>
                                        <td>${data.name}</td>
                                        <td>${data.agentName}</td>
                                        <td>${data.agentCellNo}</td>
                                        <td>${data.permanentAddress}</td>
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
                <br>
                <div class="row">
                    <div class="col-lg-5">
                    <form:form action="./generateKey.do" method="post" id="productKeyForm" commandName="productKey">
                    <form:hidden path="id"/>

                        <div class="form-group">
                            <label><spring:message code="user.form.userName"/></label>
                            <form:input path="userName" cssClass="form-control"/>
                        </div>

                        <div class="form-group">
                            <label><spring:message code="superAdmin.form.privateKey"/></label>
                            <form:input path="privateKey" cssClass="form-control"/>
                        </div>


                        <div class="form-group">
                            <label><spring:message code="superAdmin.form.valid.day"/></label>
                            <form:input path="validUpTo" cssClass="form-control"/>
                        </div>

                        <button class="btn btn-success" type="submit"><spring:message
                                code="button.submit"/></button>
                    </form:form>
                </div>
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
        $("#productKeyForm").find("input").attr("autocomplete","off");

        $("#productGroupFileId").change(function () {
            console.log("SMNLOG:changed");
            var val = $(this).val();
            var file = val.split(/[\\/]/);
            $("#productGroupRealFileId").val(file[file.length - 1]);

        });

        $(".productGroupUpload").click(function () {
            $("#superAdminForm").attr("action", "./superAdmin.do?opt=1");
            $("#superAdminForm").submit()

        });

        $(".productUpload").click(function () {
            $("#superAdminForm").attr("action", "./superAdmin.do?opt=2");
            $("#superAdminForm").submit()

        });

        $(".companyUpload").click(function () {
            $("#superAdminForm").attr("action", "./superAdmin.do?opt=3");
            $("#superAdminForm").submit()

        });
       $(".productWithCnameUpload").click(function () {
            $("#superAdminForm").attr("action", "./superAdmin.do?opt=4");
            $("#superAdminForm").submit()

        });

        /* initial form validation declaration */
        console.log('initiliaze validation....')
        function userFormValidation() {
            $("#productKeyForm").bootstrapValidator({

                fields: {
                    userName: {
                        validators: {
                            notEmpty: {
                                message: "Not Empty"
                            },
                            stringLength: {
                                message: 'Minimum 3 characters',
                                min:3
                            }
                        }
                    },
                    privateKey: {
                        validators: {
                            notEmpty: {
                                message: "Not Empty"
                            },
                            stringLength: {
                                message: 'Must be less than 16 characters',
                                max:16
                            }
                        }
                    },
                    validUpTo: {
                        validators: {
                            notEmpty: {
                                message: "Not Empty"
                            },
                            numeric: {
                                message: "must be numeric"
                            }
                        }
                    }

                }
            });
        }

        userFormValidation();
    });

</script>
<!-- /.row -->
<!-- ==================== END OF COMMON ELEMENTS ROW ==================== -->

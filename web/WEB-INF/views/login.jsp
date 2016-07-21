<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%
    final String contextPath = request.getContextPath();
%>

<title>Login</title>


    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form id="loginForm" role="form" method="post" action="j_spring_security_check">
                        <fieldset>
                            <div class="form-group">
                                <input type="text" placeholder="User id" id="login_username"  name='j_username' class="form-control"
                                       title="Please provide your username"value='<c:if test="${not empty error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>

                            </div>
                            <div class="form-group">
                                <input type='password' placeholder="Password" id="login_password" class="form-control" name='j_password'/>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                            </div>

                            <div id="errorId"><font color="red">${error}</font></div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="#" class="btn btn-lg btn-success btn-block login">Login</a>
                        </fieldset>
                    </form>


                </div>
            </div>
        </div>
    </div>

<script>

    $(document).ready(function() {
        $("#login_password").keypress(function (event) {
            //console.log("SMNLOG:writting in qty");
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                $('#loginForm').submit();
            }
        });

        $('.login').click(function(e){
            e.preventDefault();
            $('#loginForm').submit();
        });

    });


</script>
<%@ page import="java.security.Principal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%
    final String contextPath = request.getContextPath();
    final Principal loggedUser = request.getUserPrincipal();
    final Long loggedUserId = (Long)request.getSession().getAttribute("loggUserId");
%>
<script src="<%= contextPath %>/resources/js/header.js"></script>

<input id="contextPath" type="hidden" value="<%= contextPath %>" />
<input id="loggUserId" type="hidden" value="<%= loggedUserId %>" />
<!-- ==================== TOP MENU ==================== -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a id="headerTitle" class="brand" href="#"><img alt="" class="logoDiv" src="<%= contextPath %>/resources/images/new_logo.png">&nbsp;<strong class="bpcClass"><spring:message code="adminHeader.bpc"/></strong>&nbsp;<spring:message code="adminHeader.header"/></a>
            <div class="nav pull-right">
                <form class="navbar-form">
                    <div class="input-append">
                        <div class="collapsibleContent">
                            <a href="#settingsContent" class="sidebar"><span class="add-on add-on-middle add-on-mini add-on-dark" id="settings"><i class="icon-cog icon-cogCustom "></i></span></a>
                            <a href="#profileContent" class="sidebar"><span class="add-on add-on-mini add-on-dark" id="profile"><i class="icon-user"></i><span class="username"><%=loggedUser.getName()%></span></span></a>
                        </div>
                        <a href="#collapsedSidebarContent" class="collapseHolder sidebar"><span class="add-on add-on-mini add-on-dark"><i class="icon-sort-down"></i></span></a>
                    </div>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<!-- ==================== END OF TOP MENU ==================== -->

<!-- ==================== SIDEBAR ==================== -->
<div class="hiddenContent">
<!-- ==================== SIDEBAR COLLAPSED ==================== -->
<div id="collapsedSidebarContent">
    <div class="sidebarDivider"></div>
   <div class="sidebarContent">
        <ul class="collapsedSidebarMenu">
            <li><a href="#tasksContent" class="sidebar">Tasks <div class="notifyCircle cyan">3</div><i class="icon-chevron-sign-right"></i></a></li>
            <li><a href="#notificationsContent" class="sidebar">Notifications <div class="notifyCircle orange">9</div><i class="icon-chevron-sign-right"></i></a></li>
            <li><a href="#messagesContent" class="sidebar">Messages <div class="notifyCircle red">12</div><i class="icon-chevron-sign-right"></i></a></li>
            <li><a href="#settingsContent" class="sidebar">Settings<i class="icon-chevron-sign-right"></i></a></li>
            <li><a href="#profileContent" class="sidebar"><%=loggedUser.getName()%><i class="icon-chevron-sign-right"></i></a></li>
            <li class="sublevel"><a href="#">edit profile<i class="icon-user"></i></a></li>
            <li class="sublevel"><a href="#">change password<i class="icon-lock"></i></a></li>
            <li class="sublevel"><a href="#">logout<i class="icon-off"></i></a></li>
        </ul>
    </div>
</div>
<!-- ==================== END OF SIDEBAR COLLAPSED ==================== -->

<!-- ==================== SIDEBAR TASKS ==================== -->
<div id="tasksContent">
    <div class="sidebarDivider"></div>
    <div class="sidebarContent">
        <a href="#collapsedSidebarContent" class="showCollapsedSidebarMenu"><i class="icon-chevron-sign-left"></i><h1> Tasks</h1></a>
        <h1>Tasks</h1>
        <div class="sidebarInfo">
            <div class="progressTasks"><span class="label">11</span> tasks in progress</div>
            <div class="newTasks"><span class="label cyan">3</span> new tasks</div>
        </div>
        <ul class="tasksList">
            <li class="new">
                <h3>Update database</h3>
                <span class="taskProgress">0%</span>
                <div class="progress progress-striped active">
                    <div class="bar"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority red">High priority</div>
                    <div class="tag status cyan">New task</div>
                </div>
            </li>
            <li class="new">
                <h3>Clean CSS</h3>
                <span class="taskProgress">0%</span>
                <div class="progress progress-striped active">
                    <div class="bar"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority orange">Normal priority</div>
                    <div class="tag status cyan">New task</div>
                </div>
            </li>
            <li class="new">
                <h3>Clean JavaScript</h3>
                <span class="taskProgress">0%</span>
                <div class="progress progress-striped active">
                    <div class="bar"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority green">Low priority</div>
                    <div class="tag status cyan">New task</div>
                </div>
            </li>
            <li>
                <h3>Make a backup</h3>
                <span class="taskProgress">75%</span>
                <div class="progress progress-striped active">
                    <div class="bar" style="width: 75%;"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority green">Low priority</div>
                </div>
            </li>
            <li>
                <h3>Clean HTML</h3>
                <span class="taskProgress">50%</span>
                <div class="progress progress-striped active">
                    <div class="bar" style="width: 50%;"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority red">High priority</div>
                </div>
            </li>
            <li>
                <h3>Make a coffee</h3>
                <span class="taskProgress">25%</span>
                <div class="progress progress-striped active">
                    <div class="bar" style="width: 25%;"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority orange">Normal priority</div>
                </div>
            </li>
            <li>
                <h3>THEKAMAREL project</h3>
                <span class="taskProgress">100%</span>
                <div class="progress progress-striped">
                    <div class="bar" style="width: 100%;"></div>
                </div>
                <div class="appendedTags">
                    <div class="tag priority red">High priority</div>
                    <div class="tag status grey">Finished task</div>
                </div>
            </li>
        </ul>
        <button class="btn btn-primary">View all</button>
    </div>
</div>
<!-- ==================== END OF SIDEBAR TASKS ==================== -->

<!-- ==================== SIDEBAR NOTIFICATIONS ==================== -->
<div id="notificationsContent">
    <div class="sidebarDivider"></div>
    <div class="sidebarContent">
        <a href="#collapsedSidebarContent" class="showCollapsedSidebarMenu"><i class="icon-chevron-sign-left"></i><h1> Notifications</h1></a>
        <h1>Notifications</h1>
        <div class="sidebarInfo">
            <div><span class="label">32</span> notifications</div>
            <div><span class="label orange">9</span> new notifications</div>
        </div>
        <ul class="notificationsList">
            <li class="new first">
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">1 minute ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">3 minutes ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-comments"></i> New comment</div>
                <span class="notificationDate">15 minutes ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-shopping-cart"></i> New order</div>
                <span class="notificationDate">23 minutes ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-shopping-cart"></i> Order cancelled</div>
                <span class="notificationDate">1 hour ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-comments"></i> New comment</div>
                <span class="notificationDate">1 hour ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">3 hours ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">5 hours ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li class="new">
                <div><i class="icon-user"></i> User account cancelled</div>
                <span class="notificationDate">6 hours ago <span class="pull-right notificationStatus">new</span></span>
            </li>
            <li>
                <div><i class="icon-comments"></i> New comment</div>
                <span class="notificationDate">10 hour ago</span>
            </li>
            <li>
                <div><i class="icon-shopping-cart"></i> New order</div>
                <span class="notificationDate">23 hours ago</span>
            </li>
            <li>
                <div><i class="icon-comments"></i> New comment</div>
                <span class="notificationDate">yesterday</span>
            </li>
            <li>
                <div><i class="icon-shopping-cart"></i> New order</div>
                <span class="notificationDate">yesterday</span>
            </li>
            <li>
                <div><i class="icon-shopping-cart"></i> Order cancelled</div>
                <span class="notificationDate">yesterday</span>
            </li>
            <li>
                <div><i class="icon-comments"></i> New comment</div>
                <span class="notificationDate">01.03.2013</span>
            </li>
            <li>
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">01.03.2013</span>
            </li>
            <li>
                <div><i class="icon-user"></i> New user registered</div>
                <span class="notificationDate">02.03.2013</span>
            </li>
            <li>
                <div><i class="icon-user"></i> User account cancelled</div>
                <span class="notificationDate">02.03.2013</span>
            </li>
            <li>
                <div><i class="icon-shopping-cart"></i> New order</div>
                <span class="notificationDate">02.03.2013</span>
            </li>
            <li>
                <div><i class="icon-shopping-cart"></i> Order cancelled</div>
                <span class="notificationDate">03.03.2013</span>
            </li>
        </ul>
        <button class="btn btn-primary">View all</button>
    </div>
</div>
<!-- ==================== END OF SIDEBAR NOTIFICATIONS ==================== -->

<!-- ==================== SIDEBAR MESSAGES ==================== -->
<div id="messagesContent">
    <div class="sidebarDivider"></div>
    <div class="sidebarContent">
        <a href="#collapsedSidebarContent" class="showCollapsedSidebarMenu"><i class="icon-chevron-sign-left"></i><h1> Messages</h1></a>
        <h1>Messages</h1>
        <div class="sidebarInfo">
            <div><span class="label">128</span> messages</div>
            <div><span class="label red">12</span> unreaded messages</div>
        </div>
        <ul class="messagesList">
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/rimmer-avatar.jpg" alt=""></div>
                <h3>Arnold Karlsberg</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/homer-avatar.jpg" alt=""></div>
                <h3>John Doe</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/peter-avatar.jpg" alt=""></div>
                <h3>Peter Kay</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/zoidberg-avatar.jpg" alt=""></div>
                <h3>George McCain</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/peter-avatar.jpg" alt=""></div>
                <h3>Peter Kay</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
            <li class="unreaded">
                <div class="messageAvatar"><img src="<%= contextPath %>/resources/theme/img/rimmer-avatar.jpg" alt=""></div>
                <h3>Arnold Karlsberg</h3>
                <span class="messageDate">05.03.2013 17:55 <span class="pull-right messageStatus">unreaded</span></span>
                <div class="messageContent">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad..."</div>
            </li>
        </ul>
        <button class="btn btn-primary">View all</button>
    </div>
</div>
<!-- ==================== END OF SIDEBAR MESSAGES ==================== -->

<!-- ==================== SIDEBAR SETTINGS ==================== -->
<div id="settingsContent">
    <div class="sidebarDivider"></div>
    <div class="sidebarContent">
        <a href="#collapsedSidebarContent" class="showCollapsedSidebarMenu"><i class="icon-chevron-sign-left"></i><h1> Settings</h1></a>
        <h1>Settings</h1>

            <footer>
                <div id="userMangDivId" class="profileSettingBlock"><i class="icon-male"></i><spring:message code="adminHeader.userManagement"/></div>
                <div id="weightiningDivId" class="profileSettingBlock"><i class="icon-shield"></i><spring:message code="proactiveProject.ratio.setup.title2"/></div>
                <div id="controlDivId" class="profileSettingBlock"><i class="icon-cog"></i><spring:message code="adminHeader.controls"/></div>
                <%--<div id="policiesDivId" class="profileSettingBlock"><i class="icon-tags"></i><spring:message code="adminHeader.policies"/></div>--%>
                <div id="reactiveProjectMangDivId" class="profileSettingBlock"><i class="icon-cogs"></i><spring:message code="adminHeader.reactiveProjectManagement"/></div>
                <div id="realTimeMonIntervalSetUpDivId" class="profileSettingBlock"><i class="icon-asterisk"></i><spring:message code="adminHeader.realTimeMonitoringIntervalSetup"/></div>
                <div id="holidayId" class="profileSettingBlock"><i class="icon-asterisk"></i><spring:message code="adminHeader.holiday.rule.level"/></div>
           </footer>
           <footer>
               <div id="importTrxDivId" class="profileSettingBlock"><i class="icon-upload-alt"></i><spring:message code="adminHeader.import.transaction"/></div>
               <div id="ruleCreationDivId" class="profileSettingBlock"><i class="icon-qrcode"></i><spring:message code="adminHeader.rule"/></div>
           </footer>
    </div>
</div>
<!-- ==================== END OF SIDEBAR SETTINGS ==================== -->

<!-- ==================== SIDEBAR PROFILE ==================== -->
<div id="profileContent">
    <div class="sidebarDivider"></div>
    <div class="sidebarContent">
        <a href="#collapsedSidebarContent" class="showCollapsedSidebarMenu"><i class="icon-chevron-sign-left"></i><h1> My account</h1></a>
        <h1>My account</h1>
        <div class="profileBlock">
            <div class="profilePhoto">
                <div class="usernameHolder"><%=loggedUser.getName()%></div>
            </div>
            <div class="profileInfo">
                <p><i class="icon-map-marker"></i> Piestany, SK</p>
                <p><i class="icon-envelope-alt"></i> ici.kamarel@tattek.com</p>
                <p><i class="icon-globe"></i> tattek.com</p>
                <p class="aboutMe">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                </p>
            </div>
            <footer>
                <div id="changeProfileDivId" class="profileSettingBlock editProfile"><i class="icon-user"></i>edit profile</div>
                <div id="changePasswordDivId" class="profileSettingBlock changePassword"><i class="icon-lock"></i>change password</div>
                <div id="logoutDivId" class="profileSettingBlock logout"><i class="icon-off"></i>logout</div>
            </footer>
        </div>
    </div>
</div>
<!-- ==================== END OF SIDEBAR PROFILE ==================== -->

</div>
<!-- ==================== END OF SIDEBAR ==================== -->
<input id="mainMenuSelectionTxtId" type="hidden" value="${mainTabId}"/>
<input type="hidden" id="submenuId" value="${subTabId}">
<!-- ==================== MAIN MENU ==================== -->
<div class="mainmenu noDisplay">
    <div class="container-fluid">
        <ul class="nav">
            <li class="collapseMenu"><a href="#"><i class="icon-double-angle-left"></i>hide menu<i class="icon-double-angle-right deCollapse"></i></a></li>
            <li class="divider-vertical firstDivider"></li>
            <li class="left-side" id="dashboard"><a href="landingPage.html"><%--<i class="icon-dashboard"></i>--%><spring:message code="dashboard"/></a></li>
            <li class="divider-vertical"></li>
            <li id="transactionMonitoring" class="dropdown assignment">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="transactionMonitoringId"><%--<i class="icon-list"></i>--%><spring:message code="realtime.workFlow"/><%--<span class="label label-pressed">2</span>--%></a>
                <ul class="dropdown-menu" >
                    <li><a tabindex="-1" id="trxMoniId" href="RealtimeMonitoringWorkflow.html"><spring:message code="realtime.transaction.summary.view"/></a></li>
                    <li><a tabindex="-1" href="transactionApprove.html"><spring:message code="landingPage.transactionApprove" /></a></li>
                    <li><a tabindex="-1" href="transactionSearch.html"><spring:message code="mainTab.transactionSearch.title" /></a></li>
                </ul>
            </li>
            <li class="divider-vertical"></li>
            <li id="newRiskAss" class="dropdown riskMenu" >
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="formElements"><%--<i class="icon-list"></i>--%><spring:message code="landingPage.riskAssessment"/><%--<span class="label label-pressed">3</span>--%></a>
                <ul class="dropdown-menu">
                    <li><a tabindex="-1" href="weightedScreen.html"><spring:message code="landingPage.newRiskAssessment" /></a></li>
                    <li><a tabindex="-1" href="ContinueRiskAssessmentWorkFlow.html"><spring:message code="landingPage.continueRiskAssessment" /></a></li>
                    <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="thirdPartyReview" /></a>
                        <ul class="dropdown-menu sub-menu">
                            <li><a href="existingVendorsForReputaionalReview.html"><spring:message code="dueDilligance.new.reputational.review" /></a></li>
                            <li><a href="newReputationalFormSearch.html"><spring:message code="dueDilligance.landingPage.header.ReputationalReview" /></a></li>
                            <li><a href="#"><spring:message code="dueDilligance.landingPage.header.financialReview" /></a></li>
                        </ul>
                     </li>
                </ul>
            </li>                                                                                                G
            <li class="divider-vertical"></li>
            <li id="icga" class="dropdown internal">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="interface"><%--<i class="icon-pencil"></i>--%><spring:message code="landingPage.internalControls" /><%--<span class="label label-pressed">2</span>--%></a>
                <ul class="dropdown-menu">
                    <li><a href="existingCtrlGapAnalysis.html"><spring:message code="landingPage.existingControls"/></a></li>
                    <li><a tabindex="-1" href="InternalCtrlGapAnalysisAC.html?icga=0"><spring:message code="landingPage.analyzeByControls" /></a></li>
                </ul>
            </li>
            <li class="divider-vertical"></li>
            <li id="investigations"><a href="ReactiveWorkflow.html"><%--<i class="icon-tint"></i>--%><spring:message code="landingPage.investigations" /></a></li>
            <li class="divider-vertical"></li>
            <li id="reporting"><a href=""><%--<i class="icon-th"></i>--%><spring:message code="landingPage.reporting" /></a></li>
            <%--<li class="divider-vertical"></li>--%>

            <li id="tranningAndCertification"><a href="trainingList.html"><%--<i class="icon-th-large"></i>--%><spring:message code="mainTab.training.and.certification.title"/></a></li>
            <li id="policyAndProcedure"><a href="policyList.html"><%--<i class="icon-th-large"></i>--%><spring:message code="procedures.and.certification"/></a></li>
            <li class="divider-vertical"></li>

        </ul>
    </div>
</div>
<!-- ==================== END OF MAIN MENU ==================== -->
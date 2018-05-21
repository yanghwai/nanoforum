<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Mini Forum</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
        <h2>Welcome to Mini Forum</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <%--put a new button: add user--%>
            <input type="button" value="Post Thread"
                   onclick="window.location.href='postNewTopic'; return false"
                   class="add-button"/>

            <%-- add a search box--%>
            <form:form action="search" method="post">
                Search title: <input type="text" name="theTitle" title="Search Title"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Post Time</th>

                    <security:authorize access="hasRole('DBA')">
                        <th>Action</th>
                    </security:authorize>

                </tr>

                <c:forEach var="tmpTopic" items="${topics}">
                    <%--construct an "show topic details page" link with topic id--%>
                    <c:url var="topicLink" value="/topic/showTopicPage">
                        <c:param name="topicId" value="${tmpTopic.id}"/>
                    </c:url>

                    <%--construct a "send message" link with user id--%>
                    <c:url var="sendMsgLink" value="/message/sendNewMessage">
                        <c:param name="userId" value="${tmpTopic.authorId}"/>
                    </c:url>


                    <tr>
                        <td>
                            <a href="${topicLink}">${tmpTopic.title}</a>
                        </td>
                        <td>
                            <a href="${sendMsgLink}">${tmpTopic.authorName}</a>
                        </td>
                        <td>${tmpTopic.postTime}</td>

                        <security:authorize access="hasRole('DBA')">
                            <%--<button type="submit">Delete</button>--%>
                            <form:form action="/topic/delete" method="post">
                                <input type="hidden" name="topicId" value="${tmpTopic.id}"/>
                                <td>
                                    <input type="submit" value="Delete"/>
                                </td>
                            </form:form>
                        </security:authorize>

                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

    <hr>
    <a href="${pageContext.request.contextPath}/message/list">Go to My Message Box</a>


    <security:authorize access="hasRole('ADMIN')">
        <hr>
        <%-- Add a link for admins--%>
        <p>
            <a href="${pageContext.request.contextPath}/systems/user/list">User Management System</a>
        </p>
    </security:authorize>


    <security:authorize access="!isAnonymous()">
        <hr>
        <%--Add a logout button--%>
        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Logout" class="add-button"/>
        </form:form>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <hr>
        <%--Add a login button--%>
        <form:form action="${pageContext.request.contextPath}/login" method="get">
            <input type="submit" value="Login" class="add-button">
        </form:form>
    </security:authorize>

</body>
</html>

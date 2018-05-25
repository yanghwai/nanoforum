<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Mini Forum</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="icon" href="${pageContext.request.contextPath}/static/image/favicon.ico"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ConfirmForm.js"></script>
</head>

<body>
    <h1>Welcome to Mini Forum</h1>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Home</li>
        </ol>
    </nav>

    <div class="container">
        <div>
        <%--put a new button: add user--%>
            <form:form action="${pageContext.request.contextPath}/topic/postNewTopic" method="get">
                <button class="btn btn-primary" type="submit">New Post</button>
            </form:form>

            <%-- add a search box--%>
            <form:form action="search" method="post">
                Search title: <input type="text" name="theTitle" title="Search Title"/>
                <input type="submit" value="Search" class="btn btn-secondary"/>
            </form:form>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Post Time</th>
                        <security:authorize access="hasRole('DBA')">
                            <th>Action</th>
                        </security:authorize>

                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="tmpTopic" items="${topics}">
                        <%--construct an "show topic details page" link with topic id--%>
                        <c:url var="topicLink" value="/topic/showTopicPage">
                            <c:param name="topicId" value="${tmpTopic.id}"/>
                        </c:url>

                        <%--Link for checking user info--%>
                        <c:url var="userInfoLink" value="/user/info">
                            <c:param name="userId" value="${tmpTopic.authorId}"/>
                        </c:url>

                        <%--construct a "delete" link with topic id--%>
                        <c:url var="deleteTopicLink" value="${pageContext.request.contextPath}/topic/delete"/>

                        <tr>
                            <td>
                                <a href="${topicLink}">${tmpTopic.title}</a>
                            </td>
                            <td>
                                <c:if test="${empty tmpTopic.authorName}">
                                    User Deleted
                                </c:if>
                                <c:if test="${not empty tmpTopic.authorName}">
                                    <a href="${userInfoLink}">${tmpTopic.authorName}</a>
                                </c:if>
                            </td>
                            <td>${tmpTopic.postTime}</td>

                            <security:authorize access="hasRole('DBA')">
                                <%--"Delete" button--%>
                                <form:form action="${deleteTopicLink}" method="post" onsubmit="return submitResult()">
                                    <input type="hidden" name="topicId" value="${tmpTopic.id}"/>
                                    <td>
                                        <button type="submit" name="delete" class="btn btn-danger">Delete</button>
                                    </td>
                                </form:form>
                            </security:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

    <div class="container">
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
                <input type="submit" value="Logout" class="btn btn-secondary"/>
            </form:form>
        </security:authorize>

        <security:authorize access="isAnonymous()">
            <hr>
            <%--Add a login button--%>
            <form:form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Login" class="btn btn-success">
            </form:form>
        </security:authorize>
    </div>

</body>
</html>

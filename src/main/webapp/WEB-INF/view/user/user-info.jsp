<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Info</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

    <h2>User Information</h2>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a> </li>
            <li class="breadcrumb-item active" aria-current="page">User Information</li>
        </ol>
    </nav>

    <div class="container">
        <div class="content">
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <td>User ID:</td>
                        <td>${user.id}</td>
                    </tr>

                    <tr>
                        <td>Username:</td>
                        <td>${user.ssoId}</td>
                    </tr>

                    <tr>
                        <td>First name:</td>
                        <td>${user.firstName}</td>
                    </tr>

                    <tr>
                        <td>Last name: </td>
                        <td>${user.lastName}</td>
                    </tr>

                    <tr>
                        <td>Email: </td>
                        <td>${user.email}</td>
                    </tr>

                    <tr>
                        <td>Roles:</td>
                        <td>
                            <c:forEach var="role" items="${user.roleList}">
                                ${role};
                            </c:forEach>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>


        <div>
            <c:url var="sendMsgLink" value="${pageContext.request.contextPath}/message/sendNewMessage">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <a href="${sendMsgLink}"><button class="btn btn-success">Send Message</button></a>
        </div>

        <div>
            <hr>
            <a href="${pageContext.request.contextPath}/">Back to homepage</a>

            <br><hr>

            <%--Aadd a logout button--%>
            <form:form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout" class="btn btn-secondary"/>
            </form:form>
        </div>

    </div>


</body>
</html>

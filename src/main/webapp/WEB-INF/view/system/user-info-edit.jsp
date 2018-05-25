<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Info Editor</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

    <h2>User Information Editor</h2>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/systems/user/list">User Management</a> </li>
            <li class="breadcrumb-item active" aria-current="page">User Information Editor</li>
        </ol>
    </nav>


    <div class="container">
        <div class="content">
            <h2>${errorMessage}</h2>
            <form:form action="processUserInfoEdit" method="post" modelAttribute="theUser">
                <table class="table table-striped">
                    <tbody>
                        <tr>
                            <td>User ID:</td>
                            <td>${theUser.id}</td>
                        </tr>

                        <tr>
                            <td>Username:</td>
                            <td>${theUser.ssoId}</td>
                        </tr>

                        <tr>
                            <td>Password:</td>
                            <td>${theUser.password}</td>
                        </tr>

                        <tr>
                            <td>First name:</td>
                            <td><form:input path="firstName"/></td>
                        </tr>

                        <tr>
                            <td>Last name: </td>
                            <td><form:input path="lastName"/></td>
                        </tr>

                        <tr>
                            <td>Email: </td>
                            <td><form:input path="email"/></td>
                        </tr>

                        <tr>
                            <td>Roles:</td>
                            <td>
                                <c:forEach items="${theUser.userRoles}" var="role">
                                    ${role.type};
                                </c:forEach>
                            </td>

                        </tr>
                    </tbody>
                </table>

                <button type="submit" class="btn btn-warning">Save</button>
            </form:form>
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

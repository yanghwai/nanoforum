<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="icon" href="${pageContext.request.contextPath}/static/image/favicon.ico"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ConfirmForm.js"></script>
</head>
<body>

    <h2>Admin - User Management System</h2>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">User Management</li>
        </ol>
    </nav>

    <div class="container">
        <div>

            <%--put a new button: add user--%>
            <a href="${pageContext.request.contextPath}/systems/user/showAddUserForm">
                <button type="button" class="btn btn-primary">Add User</button>
            </a>

            <%-- add a search box--%>
            <form:form action="search" method="post">
                Search user: <input type="text" name="theSearchName" title="Search User"/>
                <input type="submit" value="Search" class="btn btn-secondary"/>
            </form:form>

            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Action</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="tmpUser" items="${users}">
                        <%--construct an "update" link with user id--%>
                        <c:url var="updateLink" value="/user/showFormForUpdate">
                            <c:param name="userId" value="${tmpUser.id}"/>
                        </c:url>

                        <c:url var="userInfoLink" value="/user/info">
                            <c:param name="userId" value="${tmpUser.id}"/>
                        </c:url>

                        <%--construct a "delete" link with user id--%>
                        <c:url var="deleteLink" value="/systems/user/delete"/>

                        <tr>
                            <td>
                                <a href="${userInfoLink}">${tmpUser.ssoId}</a>
                            </td>
                            <td>${tmpUser.password}</td>
                            <td>${tmpUser.firstName}</td>
                            <td>${tmpUser.lastName}</td>
                            <td>${tmpUser.email}</td>
                            <td>
                                <a href="${updateLink}">
                                    <button class="btn btn-warning">Update</button>
                                </a>
                            </td>
                            <td>
                                <form:form method="post" action="${deleteLink}" onsubmit="return submitResult();">
                                    <input type="hidden" name="userId" value="${tmpUser.id}"/>
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
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

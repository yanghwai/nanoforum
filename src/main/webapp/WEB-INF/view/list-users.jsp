<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>User List</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
        <h2>UMS - User Management System</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <%--put a new button: add user--%>
            <input type="button" value="Add User"
                   onclick="window.location.href='showFormForAdd'; return false"
                   class="add-button"/>

            <%-- add a search box--%>
            <form:form action="search" method="post">
                Search user: <input type="text" name="theSearchName" title="Search User"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="tmpUser" items="${users}">
                    <%--construct an "update" link with user id--%>
                    <c:url var="updateLink" value="/user/showFormForUpdate">
                        <c:param name="userId" value="${tmpUser.id}"/>
                    </c:url>

                    <%--construct a "delete" link with user id--%>
                    <c:url var="deleteLink" value="/systems/user/delete">
                        <c:param name="userId" value="${tmpUser.id}"/>
                    </c:url>

                    <tr>
                        <td>${tmpUser.ssoId}</td>
                        <td>${tmpUser.password}</td>
                        <td>${tmpUser.firstName}</td>
                        <td>${tmpUser.lastName}</td>
                        <td>${tmpUser.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                               onclick="if(!confirm('Are you sure you want to delete this user?')) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

    <hr>
    <a href="${pageContext.request.contextPath}/">Back to homepage</a>

    <br><hr>

    <%--Aadd a logout button--%>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
    </form:form>

</body>
</html>

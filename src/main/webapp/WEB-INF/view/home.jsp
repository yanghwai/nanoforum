<%--User homepage after logged in--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>
        Welcome to Home page!
    </p>


    <hr>

    <p>
        Your username: <security:authentication property="principal.username"/>
        <br><br>
        Your role(s): <security:authentication property="principal.authorities"/>
    </p>

    <hr>
    <a href="${pageContext.request.contextPath}/topic/list">Go to Topic List</a>

    <hr>
    <a href="${pageContext.request.contextPath}/message/list">Go to My Message Box</a>

    <hr>
    <%--Aadd a logout button--%>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
    </form:form>
</body>
</html>

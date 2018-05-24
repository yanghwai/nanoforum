<%--
 Page to edit/submit a topic post
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Topic</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/style.css"/>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/add-customer-style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Mini Forum</h2>
        </div>
    </div>

    <div id="container">
        <h3>Add a New Topic</h3>
        <form:form action = "saveTopic" modelAttribute="topic" method="post">
            <form:hidden path="id"/>
            <h3>Title: <form:input path="title"/></h3>
            <h3>Body:</h3>
            <form:textarea path="content" rows="10" cols="50"/>
            <br><br>
            <input type="submit" value="Post" class="save"/>

        </form:form>
        <br><br>
        <p>
            <a href="${pageContext.request.contextPath}/topic/list">Back to list</a>
        </p>
    </div>
</body>
</html>

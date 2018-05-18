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
            <h2>New Message</h2>
        </div>
    </div>

    <div id="container">
        <h3>Send a new message</h3>
        <form:form action = "send" modelAttribute="theMessage" method="post">
            <form:hidden path="id"/>
            <%--<form:hidden path="sender"/>--%>
            <%--<form:hidden path="receiver"/>--%>

            <h3>From: ${theMessage.sender.ssoId}</h3>
            <h3>To: ${theMessage.receiver.ssoId}</h3>
            <h3>Content:</h3>
            <form:textarea path="content" rows="10" cols="50"/>
            <br><br>
            <input type="submit" value="Send Now" class="save"/>

        </form:form>
        <br><br>
        <p>
            <a href="${pageContext.request.contextPath}/message/list">Back to Message InBox/OutBox</a>
        </p>
    </div>
</body>
</html>

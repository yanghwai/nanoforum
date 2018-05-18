<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>My Messages</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>My Message Box</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <h2>Inbox</h2>

            <table>
                <tr>
                    <th>From</th>
                    <th>Content</th>
                    <th>Send Time</th>
                    <th>Read Flag</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="tmpMsg" items="${inbox}">
                    <c:url var="replyLink" value="/message/sendNewMessage">
                        <c:param name="userId" value="${tmpMsg.sender.id}"/>
                    </c:url>
                    <tr>
                        <td>${tmpMsg.sender.ssoId}</td>
                        <td>${tmpMsg.content}</td>
                        <td>${tmpMsg.sendTime}</td>
                        <td>${tmpMsg.read}</td>
                        <td>
                            <a href="${replyLink}">Reply</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <br><hr><br>

            <h2>Outbox</h2>

            <table>
                <tr>
                    <th>To</th>
                    <th>Content</th>
                    <th>Send Time</th>
                    <th>Read Flag</th>
                </tr>

                <c:forEach var="tmpMsg" items="${outbox}">
                    <tr>
                        <td>${tmpMsg.receiver.ssoId}</td>
                        <td>${tmpMsg.content}</td>
                        <td>${tmpMsg.sendTime}</td>
                        <td>${tmpMsg.read}</td>
                    </tr>
                </c:forEach>
            </table>


        </div>


    </div>

    <hr>
    <a href="${pageContext.request.contextPath}/">Back to homepage</a>


</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>My Messages</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>


    <h1>My Message Box</h1>


    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a> </li>
            <li class="breadcrumb-item active" aria-current="page">Message Box</li>
        </ol>
    </nav>

    <div class="container">
        <div class="content">
            <h2>Inbox</h2>
            <table class="table table-striped">
                <tr>
                    <th>From</th>
                    <th>Content</th>
                    <th>Send Time</th>
                    <th>Read Flag</th>
                    <th></th>
                    <th></th>

                </tr>

                <c:forEach var="tmpMsg" items="${inbox}">

                    <c:url var="userInfoLink" value="${pageContext.request.contextPath}/user/info">
                        <c:param name="userId" value="${tmpMsg.senderId}"/>
                    </c:url>
                    <tr>
                        <td>
                            <c:if test="${empty tmpMsg.senderSsoId}">
                                User Deleted
                            </c:if>
                            <c:if test="${not empty tmpMsg.senderSsoId}">
                                <a href="${userInfoLink}">${tmpMsg.senderSsoId}</a>
                            </c:if>
                        </td>
                        <td>${tmpMsg.content}</td>
                        <td>${tmpMsg.sendTime}</td>
                        <td>${tmpMsg.read}</td>
                        <form:form action="setRead" method="post">
                            <input type="hidden" name="msgId" value="${tmpMsg.id}"/>
                            <td>
                                <c:if test="${not tmpMsg.read}">
                                    <input type="submit" value="Read" class="btn btn-primary"/>
                                </c:if>
                                <c:if test="${tmpMsg.read}">
                                    <input type="submit" value="Read" class="btn btn-primary" disabled/>
                                </c:if>
                            </td>
                        </form:form>

                        <td>
                            <c:if test="${not empty tmpMsg.senderSsoId}">
                                <c:url var="sendMsgLink" value="${pageContext.request.contextPath}/message/sendNewMessage">
                                    <c:param name="userId" value="${tmpMsg.senderId}"/>
                                </c:url>
                                <a href="${sendMsgLink}"><button class="btn btn-success">Reply</button></a>
                            </c:if>
                        </td>

                    </tr>
                </c:forEach>
            </table>

        </div>

        <div class="content">
            <h2>Outbox</h2>

            <table class="table table-striped">
                <tr>
                    <th>To</th>
                    <th>Content</th>
                    <th>Send Time</th>
                    <th>Read Flag</th>
                </tr>

                <c:forEach var="tmpMsg" items="${outbox}">
                    <tr>
                        <td>${tmpMsg.receiverSsoId}</td>
                        <td>${tmpMsg.content}</td>
                        <td>${tmpMsg.sendTime}</td>
                        <td>${tmpMsg.read}</td>
                    </tr>
                </c:forEach>
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

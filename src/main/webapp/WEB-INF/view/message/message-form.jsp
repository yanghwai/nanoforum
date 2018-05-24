<%--
 Page to edit/submit a topic post
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Topic</title>

    <link type="text/css"
          rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

    <h1>New Message</h1>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a> </li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/message/list">Message Box</a> </li>
            <li class="breadcrumb-item active" aria-current="page">New Message</li>
        </ol>
    </nav>

    <div class="container">
        <h3>Send a new message</h3>
        <div class="content">
            <div class="col-md-6" align="left">
                <form:form action = "send" modelAttribute="theMessage" method="post">
                    <form:hidden path="id"/>
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <td >From: </td>
                                <td>${theMessage.senderSsoId}</td>
                            </tr>
                            <tr>
                                <td>To: </td>
                                <td>${theMessage.receiverSsoId}</td>
                            </tr>
                        </tbody>
                    </table>
            </div>

            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Message</span>
                </div>
                <form:textarea path="content" class="form-control" aria-label="With textarea" />
            </div>

            <hr>
            <input type="submit" value="Send Now" class="btn btn-success"/>
            </form:form>
        </div>

        <div>
            <hr>
            <a href="${pageContext.request.contextPath}/message/list">Back to Message InBox/OutBox</a>
        </div>
    </div>
</body>
</html>

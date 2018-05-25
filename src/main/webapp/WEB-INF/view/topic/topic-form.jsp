<%--
 Page to edit/submit a topic post
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Topic</title>
    <link type="text/css" rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

    <h2>Mini Forum</h2>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a> </li>
            <li class="breadcrumb-item active" aria-current="page">New Thread</li>
        </ol>
    </nav>

    <div class="container">
        <div class="content">
            <h3>Add a New Topic</h3>
            <form:form action = "saveTopic" modelAttribute="topic" method="post">
                <form:hidden path="id"/>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Title</span>
                    </div>
                    <form:input path="title" type="text" class="form-control" placeholder="Title" aria-label="Title"
                                aria-describedby="basic-addon1"/>
                </div>

                <hr>

                <div class="input-group-lg">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-lg">Message</span>
                    </div>
                    <form:textarea path="content" class="form-control" aria-label="With textarea" />
                </div>

                <hr>
                <input type="submit" value="Post" class="btn btn-success"/>
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

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Page to display a topic
--%>
<html>
<head>
    <title>${topic.title}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ConfirmForm.js"></script>
</head>
<body>

    <h1>Nano Forum - Thread</h1>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/topic/list">Home</a> </li>
            <li class="breadcrumb-item active" aria-current="page">Topic</li>
        </ol>
    </nav>



    <div class="container">
        <div align="right">
            <%--"Delete" button--%>
            <security:authorize access="hasRole('DBA')">
                <c:url var="deleteLink" value="${pageContext.request.contextPath}/topic/delete"/>
                <form:form action="${deleteLink}" method="post" onsubmit="return submitResult();">
                    <input type="hidden" name="topicId" value="${topic.id}"/>
                    <button type="submit" name="delete" class="btn btn-danger">Delete</button>
                </form:form>
            </security:authorize>
        </div>

        <div class="content">
            <h3>${topic.title}</h3>

            <hr>

            <h4>Posted by ${topic.authorName} on ${topic.postTime}</h4>

            <hr>
            <h4>Content:</h4>

            <p>${topic.content}</p>

        </div>


        <div>
            <hr>
            <p>
                <a href="${pageContext.request.contextPath}/topic/list">Back to list</a>
            </p>
        </div>
    </div>

</body>
</html>

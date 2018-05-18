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
            <h2>Nano Forum - Thread</h2>
        </div>

        <security:authorize access="hasRole('DBA')">
            <c:url value="/topic/delete" var="deleteLink"/>
            <form:form action="${deleteLink}" method="post">
                <input type="hidden" name="topicId" value="${topic.id}"/>
                <input type="submit" value="Delete" class="add-button"/>
                <%--<button type="submit">Delete</button>--%>
            </form:form>
        </security:authorize>

    </div>

    <div id="container">
        <div id="content">
            <h3>${topic.title}</h3>

            <hr>

            <h4>Posted by ${topic.author.ssoId} on ${topic.postTime}</h4>

            <hr>
            <h4>Content:</h4>

            <p>${topic.content}</p>

        </div>
    </div>


    <hr>
    <p>
        <a href="${pageContext.request.contextPath}/topic/list">Back to list</a>
    </p>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<myTags:template>
    <jsp:attribute name="header">
    <meta charset="UTF-8">
    <title>New Student</title>
    </jsp:attribute>
    <jsp:body>
        <jsp:useBean id="person" class="ru.inno.stc14.entity.Person" />
        <c:set target="${person}" property="name" value="Anonim" />
        <fmt:parseDate pattern="yyyy-MM-dd" value="1900-01-01" var="defaultDate" />
        <c:set target="${person}" property="birthDate" value="${defaultDate}" />
        <h1>Adding a new student</h1>
        <form method="post" action="${pageContext.request.contextPath}/person" autocomplete="off">
            <div class="form-group">
                <label for="name">Name</label>
                <input name="name" type="text" class="form-control" id="name" value="<jsp:getProperty name="person" property="name" />">
            </div>
            <div class="form-group">
                <label for="birth">Birthday</label>
                <input name="birth" type="text" class="form-control" id="birth" value="<fmt:formatDate value="${person.birthDate}" pattern="dd.MM.yyyy" />">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input name="email" type="text" class="form-control" id="email" value="">
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input name="phone" type="text" class="form-control" id="phone" value="">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </jsp:body>
</myTags:template>

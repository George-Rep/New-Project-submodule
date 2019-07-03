<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>


<myTags:template>
    <jsp:attribute name="header">
       <title>Логин</title>
       <meta charset="UTF-8">
    </jsp:attribute>
    <jsp:body>
        <h1>Логин</h1>
        <form method="post" action="${pageContext.request.contextPath}/login"  autocomplete="off">
            <div class="form-group">
                <label for="login">Login</label>
                <input name="login" type="text" class="form-control" id="login" placeholder="login" >
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" id="password" placeholder="password">
            </div>
            <div class="form-group">
                <input name="from" type="hidden" class="form-control" id="from" value="${LOGIN_REDIRECT}">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <span style="color:red" >${ERROR_MESSAGE}</span>
    </jsp:body>
</myTags:template>




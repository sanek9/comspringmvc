<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>
<img src="<c:url value="/resources/images/logo.png"/>" style="float: left">
<!--  <form action="/asd/" >
<input type="text" name="q" placeholder="логин" >
<input type="password" name="q" placeholder="пароль" >
<button type="submit" >Вход</button>
</form>
<form class="panel" action="reg.html">
<button type="submit" >Регистрация</button>
</form>-->
<sec:authorize var="loggedIn" access="isAuthenticated()" />
<c:choose>
    <c:when test="${loggedIn}">
        <form action="logout" method="post" class="panel fl_r" >
            <button type="submit" >Выход</button>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </form>
    </c:when>
    <c:otherwise>
        <c:url value="/login" var="loginUrl"/>
        <form method="POST" action="${loginUrl}" class="fl_r">
            <input type="text" placeholder="логин" name="username">
            <input type="password"  placeholder="пароль" name="password">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" >Вход</button>
        </form>
    </c:otherwise>
</c:choose>

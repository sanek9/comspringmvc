<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Whitesquare</title>
<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />" type="text/css" />
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery-compressed.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.dimensions.js"/>"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.tooltip.min.js"/>"></script>--%>
</head>
<body>
    <style> 
        
    </style>
    <div>
        <div>
            <div id="pan">
                <img src="<c:url value="/resources/images/logo.png" />" class="fl_l">
                <form method="GET" class="panel fl_r" action="reg" >
                    <button type="submit" >Регистрация</button>
                </form>
                <c:url value="/login" var="loginUrl"/>
                <form method="POST" action="${loginUrl}" class="fl_r">
                    <input type="text" placeholder="логин" name="username">
                    <input type="password"  placeholder="пароль" name="password">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" >Вход</button>
                </form>
                
            </div>
            <div id="reg" class="">
                <div>
                    <form:form method="POST" action="register" commandName="person">
                    <div class="line">
                        <form:label path="firstName" cssClass="reglabel">Имя</form:label>
                        <form:input path="firstName" class="" type="text" />
                        <form:errors path="firstName" cssClass="error"/>

                    </div>
                    <div class="line">
                        <form:label path="lastName" class="reglabel">Фамилия</form:label>
                        <form:input path="lastName" class="" type="text" />
                        <form:errors path="lastName" cssClass="error"/>
                    </div>
                    <div class="line">
                        <form:label path="middleName" class="reglabel">Отчество</form:label>
                        <form:input path="middleName" class="" type="text" />
                        <form:errors path="middleName" cssClass="error"/>
                    </div>
                    <div class="separ"></div>
                    <div class="line">
                        <form:label path="birthday" class="reglabel">Дата рожденья</form:label>
                        <form:input path="birthday" class="" type="date" />
                        <form:errors path="birthday"/>
                    </div>
                    <div class="line">
                        <form:label path="gender" class="reglabel">Пол</form:label>
                        <form:radiobutton class="" path="gender" label="Мужской" value="M" />
                        <%--<label style="margin-right: 10px;">Мужской</label>--%>
                        <form:radiobutton class="" path="gender" label="Женский" value="F"/>
                        <%--<label >Женский</label>--%>
                        <form:errors path="gender" cssClass="error"/>
                    </div>
                    <div class="separ"></div>
                    <div class="line">
                        <form:label path="email" class="reglabel">Адрес эл.почты</form:label>
                        <form:input path="email" class="" type="email" />
                        <form:errors path="email" cssClass="error"/>
                    </div>
                    <div class="line">
                        <form:label path="phone" class="reglabel">Моб. телефон</form:label>
                        <form:input path="phone" class="" type="tel" pattern="[0-9]{10}" />
                        <form:errors path="phone" cssClass="error" />
                    </div>
                    <div class="separ"></div>
                    <div class="line">
                        <form:label path="password" class="reglabel">Пароль</form:label>
                        <form:input path="password" class="" type="password"/>
                        <form:errors path="password" cssClass="error"/>
                    </div>
                    <div class="line">
                        <div class="reglabel">Повторите пароль</div>
                        <input name="pass2" class="" type="password" required>
                    </div>
                    <div class="separ"></div>
                    
                    <form:button type="submit">Зарегестрироваться</form:button>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</body>
</html>

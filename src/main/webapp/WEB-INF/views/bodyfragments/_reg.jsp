<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
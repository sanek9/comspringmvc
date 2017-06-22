<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>
<div class="clearfix">
    <div class="img fl_l">
        <img src="<c:url value="/resources/images/cot.jpg"/>" width="240">
    </div>
    <div id="inform" class="fl_l">
        <h4><b>
            ${person.firstName} ${person.lastName}
        </b></h4>
        <div class="miniblock clearfix">
            <div class="miniblock">
                <div class="label fl_l">День рожденья:</div>
                <div class="labeled fl_l "><fmt:formatDate type="date" value="${person.birthday.time}" /></div>
            </div>
            <div class="miniblock">
                <div class="label fl_l">Родной город:</div>
                <div class="labeled fl_l ">Молодечно</div>
            </div>
            <div class="miniblock">
                <div class="label fl_l">Место учебы:</div>
                <div class="labeled fl_l ">ПГУ</div>
            </div>
        </div>
        <h4><b>Контактная информация</b></h4>
        <div class="miniblock clearfix">
            <div class="miniblock">
                <div class="label fl_l">Город:</div>
                <div class="labeled fl_l ">Новополоцк</div>
            </div>
            <div class="miniblock fl_l" >
                <div class="label fl_l">Моб. телефон:</div>
                <div class="labeled fl_l ">${person.phone}</div>
            </div>
            <div class="miniblock fl_l" >
                <div class="label fl_l">Доп. телефон:</div>
                <div class="labeled fl_l ">Информация скрыта</div>
            </div>
        </div>
        <%--<h4><b>Образование</b></h4>--%>
        <%--<div class="miniblock clearfix">--%>
        <%--<div class="miniblock">--%>
        <%--<div class="label fl_l">Вуз:</div>--%>
        <%--<div class="labeled fl_l ">ПГУ</div>--%>
        <%--</div>--%>
        <%--<div class="miniblock fl_l" >--%>
        <%--<div class="label fl_l">Школа:</div>--%>
        <%--<div class="labeled fl_l ">Средняя школа №2</div>--%>
        <%--</div>--%>
        <%--<div class="miniblock fl_l" >--%>
        <%--<div class="label fl_l">Колледж:</div>--%>
        <%--<div class="labeled fl_l ">МГПТК</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<h4><b>Жизненная позиция</b></h4>--%>
        <%--<div class="miniblock clearfix">--%>
        <%--<div class="miniblock">--%>
        <%--<div class="label fl_l">Мировоззрение:</div>--%>
        <%--<div class="labeled fl_l ">Православие</div>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
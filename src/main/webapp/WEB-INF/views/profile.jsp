<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Whitesquare</title>
<link rel="stylesheet" href="<c:url value="/resources/css/styles.css"/>" type="text/css" />
</head>
<%--<% Person p = (Person) session.getAttribute("person");--%>
    <%--System.out.println("atribute user: " + p);%>--%>
<body>
    <style> 
        
    </style>
    <div>
        <div>
            <div id="pan">
                <img src="<c:url value="/resources/images/logo.png"/>" style="float: left">
              <!--  <form action="/asd/" >
                    <input type="text" name="q" placeholder="логин" >
                    <input type="password" name="q" placeholder="пароль" >
                    <button type="submit" >Вход</button>
                </form>
                <form class="panel" action="reg.html">
                    <button type="submit" >Регистрация</button>
                </form>-->
                <form action="logout" method="post" class="panel fl_r" >
                    <button type="submit" >Выход</button>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                </form>
            </div>
            <div id="page">
                <div id="navig">
                    <a href="">Фотографии</a>
                    <a href="">Друзья</a>
                    <a href="">Группы</a>
                    <a href="">Аудиозаписи</a>
                    <a href="">Видеозаписи</a>
                </div>
                <div class="wrapper">
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
                    <div id="wall" class="">
                        

<script>
    
    function new_post_focus(){
        document.getElementById("new_post_submit").style.display="block";
      //  document.getElementById("new_post_input").h
     //   document.getElementById("new_post_submit").style.visibility="visible";
     //   document.getElementById("new_post_submit").style.height="auto";
       // alert("ss");
    }
    function new_post_blur(){
        document.getElementById("new_post_submit").style.display="none";
     //   document.getElementById("new_post_submit").style.visibility="hidden";
    //    document.getElementById("new_post_submit").style.height="0";
       // alert("ds");
    }
                            
</script>

                        <div class="new_post">

                            <form:form method="POST" action="add_message" commandName="message">
                                <div onmouseover="new_post_focus()" onmouseout="new_post_blur()">
                                        <%--onblur="new_post_blur()" onfocus="new_post_focus()"--%>
                                    <form:textarea path="message" id="new_post_input" class="new_post_ta" type="text"

                                                   placeholder="Добавить запись" ></form:textarea>
                                    <button id="new_post_submit" type="submit" style="display:none">Отправить</button>
                                </div>
                            </form:form>
                        </div>
                        <c:forEach var="mess" items="${person.messages}">
                            <div class="post">
                                    <div class="post_panel">
                                        <%--<c:out value="${mess.date.time}"/>--%>
                                        <label class="time">
                                            <fmt:formatDate  value="${mess.date.time}" type="both" dateStyle = "long" timeStyle = "medium" />
                                        </label>
                                        <a class="del_post" href="/del_message/${mess.messageId}">X</a>
                                    </div>
                                    <div class="post_aera">${mess.message}</div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
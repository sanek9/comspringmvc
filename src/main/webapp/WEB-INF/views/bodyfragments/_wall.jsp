<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>
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
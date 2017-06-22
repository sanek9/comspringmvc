<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title><tiles:getAsString name="title" /></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css"/>" type="text/css" />
</head>

<body>
<div>
    <div id="pan">
        <tiles:insertAttribute name="header" />
    </div>
        <tiles:insertAttribute name="body" />
    <div>
        <tiles:insertAttribute name="footer" />
    </div>
</div>
</body>
</html>
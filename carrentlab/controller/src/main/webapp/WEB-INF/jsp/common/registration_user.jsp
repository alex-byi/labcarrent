<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title"/>
    </title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>

<body>

<div class="header">
    <c:import url="header.jsp" charEncoding="utf-8"/>
</div>
<c:if test="${sessionScope.duplicateLogin != null }">
    <center>
        <h2>
            <c:out value="${duplicateLogin}"/>
        </h2>
    </center>
</c:if>
<c:remove var="duplicateLogin" scope="session"/>
<center>

    <div id="login">
        <h1>
            <fmt:message key="registration.title"/>
        </h1>
        <div>
            <form name="LanguageForm" method="get" action="?lang=ru" class="lang">
                <input name="lang" type="submit" value="ru"/>
                <input name="lang" type="submit" value="en"/>
            </form>
        </div>

        <spring:form method="post" modelAttribute="user" action="registration">

            <div class="titling">
                <fmt:message key="registration.login"/>
                <h7>*</h7>
            </div>
            <spring:input path="login" type="text" minlength="5" required="required"/>
            <br/>

            <div class="titling">
                <fmt:message key="registration.password"/>
                <h7>*</h7>
            </div>
            <spring:input path="password" type="password" minlength="6" required="required"/>
            <br/>

            <div class="titling">
                <fmt:message key="registration.fullname"/>
                <h7>*</h7>
            </div>
            <spring:input path="fullName" type="text" minlength="6" maxlength="25" required="required"/>
            <br/>

            <div class="titling">
                <fmt:message key="registration.address"/>
                <h7>*</h7>
            </div>
            Address:
            <spring:input path="address" type="text" minlength="6" required="required"/>
            <br/>

            <div class="titling">
                <fmt:message key="registration.email"/>
                <h7>*</h7>
            </div>
            <spring:input path="email" type="email" minlength="6" maxlength="25" required="required"/>
            <br/>

            <div class="titling">
                <fmt:message key="registration.passportnumber"/>
                <h7>*</h7>
            </div>
            <spring:input path="passNum" type="text" minlength="6" required="required"/>
            <br/>

            <spring:button>
                <fmt:message key="registration.submit"/>
            </spring:button>

        </spring:form>

        <form name="back" action="/carrent" method="get">
            <input type="submit"
                   value="<fmt:message key='registration.back'/>"/><br/>
        </form>
    </div>
</center>
<div class="footer">
    <c:import url="footer.jsp" charEncoding="utf-8"/>
</div>
</body>

</html>
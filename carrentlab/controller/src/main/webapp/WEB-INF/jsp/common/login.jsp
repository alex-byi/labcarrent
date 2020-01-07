<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title"/>
    </title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>

<sec:authorize access="!isAuthenticated()">
    <div class="page-wrapper">
        <div class="header">
            <c:import url="header.jsp" charEncoding="utf-8"/>
        </div>
        <div class="page-buffer">
            <center>
                <div id="login">
                    <div>
                        <form name="LanguageForm" method="get" action="?lang=ru" class="lang">
                            <input name="lang" type="submit" value="ru"/>
                            <input name="lang" type="submit" value="en"/>
                        </form>
                    </div>

                    <c:url value="/j_spring_security_check" var="loginUrl"/>

                    <form name='form-login' method="POST" action="${loginUrl}">
                        <h1>
                            <fmt:message key="login.title"/>
                        </h1>
                        <br/>
                        <input type="text" name="j_username" value="" required placeholder=<fmt:message
                               key="login.name"/> />
                        <input type="password" name="j_password" value="" required placeholder=<fmt:message
                               key="login.password"/> /> <input type="submit" value=<fmt:message key="login.enter"/>>
                    </form>
                    <form name="RegisterForm" action='common/registration_user'>
                        <input type="submit" value=<fmt:message key="login.register"/> >
                    </form>

                </div>
            </center>
        </div>
    </div>
    <div class="footer">
        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div class="page-wrapper">
        <div class="header">
            <c:import url="../common/header.jsp" charEncoding="utf-8"/>
        </div>
        <div class="page-buffer">
            <div id="menu">
                <div id="lang">
                    <center>
                        <div>
                            <form name="LanguageForm" method="get" action="?lang=ru" class="lang">
                                <input name="lang" type="submit" value="ru"/>
                                <input name="lang" type="submit" value="en"/>
                            </form>
                        </div>
                    </center>
                </div>
                <br> <br>
                <sec:authorize access="hasAuthority('ADMIN')">
                        <c:import url="../admin/menu_admin.jsp" charEncoding="utf-8"/>
                </sec:authorize>
                <sec:authorize access="hasAuthority('USER')">
                        <c:import url="../user/menu_user.jsp" charEncoding="utf-8"/>
                </sec:authorize>
            </div>
            <div>
                <table summary="position table">
                    <!-- блок для позиционирования меню -->
                </table>
            </div>
            <div id="page-content">
                <h1>Это стартовая страница. В этом приложении вы можете <br>
                    сделать заказ интересующего вас автомобиля, оплатить его, <br>
                    если будут повреждения, то администратор выставит дополнительный счет, <br>
                    который тоже надо будет оплатить.</h1>
            </div>

        </div>

    </div>
    <div class="footer">
        <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
    </div>
</sec:authorize>
</body>

</html>
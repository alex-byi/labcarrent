<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title"/>
    </title>
    <link rel="stylesheet" href="..\css\style.css" type="text/css">
    <script>
    function disp(form) {
        if (form.style.display == "none") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }

    </script>
</head>

<body>
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
            <c:import url="menu_admin.jsp" charEncoding="utf-8"/>
        </div>
        <div>
            <table summary="user role table">
                <tr>
                    <th id="user role" colspan=2>
                        <fmt:message key="role.admin"/>
                    </th>
                </tr>
            </table>
        </div>
        <div id="page-content">

            <table summary="user data table" id="usertable">
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.fullname" />
                    </td>
                    <td style="font-size: 20pt;">${user.fullName}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.username" />
                    </td>
                    <td style="font-size: 20pt;">${user.login}</td>
                    <td>
                        <form method="post" action="change_password" id="form1" style="display: none;">
                            Введите старый пароль <input type="password" name=oldPassword value="">
                            <br>
                            Введите новый пароль <input type="password" name=newPassword value="">
                            <input type="hidden" name=idUser value="${user.id}">
                            <input type="submit" value="Подтвердить" onclick="return confirm('are u shure?')">
                        </form>
                        <input type="button" value="Сменить пароль" onclick="disp(document.getElementById('form1'))"/>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.email" />
                    </td>
                    <td style="font-size: 20pt;">${user.email}</td>
                    <td>
                        <form method="post" action="change_email" id="form2" style="display: none;">
                            Введите новый адрес почты <input type="email" name=email value="">
                            <input type="hidden" name=idUser value="${user.id}">
                            <input type="submit" value="Подтвердить" onclick="return confirm('are u shure?')">
                        </form>
                        <input type="button" value="Сменить почту" onclick="disp(document.getElementById('form2'))"/>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.address" />
                    </td>
                    <td style="font-size: 20pt;">${user.address}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.cash" />
                    </td>
                    <td style="font-size: 20pt;">${user.cash}</td>
                </tr>
            </table>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>

</html>
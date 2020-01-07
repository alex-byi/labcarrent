<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

            <!DOCTYPE html>
            <html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

            <head>
                <fmt:setLocale value="${locale}" scope="session" />
                <fmt:setBundle basename="title" />
                <title>
                    <fmt:message key="header.title" />
                </title>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="..\css\style.css" type="text/css">

            </head>

            <body>
                <div class="page-wrapper">
                    <div class="header">
                        <c:import url="../common/header.jsp" charEncoding="utf-8" />
                    </div>
                    <div class="page-buffer">
                        <div id="menu">
                            <div id="lang">

                                <center>
                                    <div>
                                        <form name="LanguageForm" method="get" action="?lang=ru" class="lang">
                                                                                   <input name="lang" type="submit" value="ru" />
                                                                                     <input name="lang" type="submit" value="en" />
                                                                                  </form>
                                    </div>
                                </center>
                            </div>
                            <br> <br>
                            <c:import url="menu_admin.jsp" charEncoding="utf-8" />
                        </div>
                        <div>
                            <table summary="user role table">
                                <tr>
                                    <th id="user role" colspan=2>
                                        <fmt:message key="role.admin" />
                                    </th>
                                </tr>
                            </table>
                        </div>

                        <div id="page-content">
                            <h2>
                                <fmt:message key="menu.crashbills" />
                            </h2>
                            <table summary="all crashes" id="usertable">
                                <thead>
                                    <tr>
                                        <th id="crash id">
                                            <fmt:message key="crashpage.id" />
                                        </th>
                                        <th id="crash damage">
                                            <fmt:message key="crashpage.damage" />
                                        </th>
                                        <th id="crash amount">
                                            <fmt:message key="crashpage.amount" />
                                        </th>
                                        <th id="crash car">
                                            <fmt:message key="crashpage.car" />
                                        </th>
                                        <th id="crash user">
                                            <fmt:message key="crashpage.user" />
                                        </th>
                                        <th id="crash completed">
                                            <fmt:message key="crashpage.completed" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="crash" items="${allCrashs}">
                                        <tr>
                                            <td>${crash.id}</td>
                                            <td>${crash.damage}</td>
                                            <td>${crash.amount}</td>
                                            <td>
                                                <c:forEach var="car" items="${carsO}">
                                                    <c:if test="${crash.idCar == car.id }">
                                                        ${car.name}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="user" items="${usersO}">
                                                    <c:if test="${crash.idUser == user.id }">
                                                        ${user.fullName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:if test="${crash.complete == true}">
                                                    <fmt:message key="crashpage.completed" />
                                                </c:if>
                                                <c:if test="${crash.complete != true}">
                                                    <fmt:message key="crashpage.notcompleted" />
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>

                </div>
                <div class="footer">
                    <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                </div>

            </body>

            </html>
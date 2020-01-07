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
                                <fmt:message key="allorders.createaddbill" />
                            </h2>

                            <form action="addCrashCommand" method="post">
                                <input type="hidden" name="orderId" value="${orderId}" /> <input type="hidden" name="userId" value="${userId}" /> <input type="hidden" name="carId" value="${carId}" />
                                <table summary="add crash table" id="usertable">
                                    <tr>
                                        <th id="add crash" colspan=2>
                                            <font size=5>
                                                <fmt:message key="crashorders.addcrash" />
                                            </font>
                                        </th>
                                    </tr>
                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="crashorders.damagedescr" />
                                            <input type="text" name="description" value="" required placeholder=<fmt:message key="crashorders.damagedescr" /> size=37 maxlength=50>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="crashorders.amount" />
                                            <input type="number" min="0" name="amount" value="" required placeholder=<fmt:message key="crashorders.amount" /> size=37 maxlength=50>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <center>
                                                <input type="submit" value=<fmt:message key="crashorders.create" /> />
                                            </center>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>

                    </div>

                </div>
                <div class="footer">
                    <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                </div>

            </body>

            </html>
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

                <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
                <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
                <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
                <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

            </head>

            <body>

                <c:if test="${requestScope.availableCars == null}" var="testcif">
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
                                <c:import url="menu_user.jsp" charEncoding="utf-8" />
                            </div>
                            <div>
                                <table summary="position table">
                                    <!-- блок для позиционирования меню -->
                                </table>
                            </div>
                            <div id="page-content">
                                <form action="order_page_step2" method="post">
                                    <table summary="order data choose" id="usertable">
                                        <tr>
                                            <th id="order data">
                                                <center>
                                                    <font size=5>
                                                        <fmt:message key="userorders.datechoose" />
                                                    </font>
                                                </center>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <center>
                                                    <input type="text" name="dates" value="" />
                                                </center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <center>
                                                    <input type="submit" value=<fmt:message key="userorders.continue" /> />
                                                </center>
                                            </td>
                                        </tr>

                                    </table>
                                </form>

                                <script>
                                    $('input[name="dates"]').daterangepicker({
                                        minDate: new Date()
                                    });
                                </script>
                            </div>

                        </div>

                    </div>
                    <div class="footer">
                        <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                    </div>
                </c:if>

                <c:if test="${requestScope.availableCars != null}" var="testcif">

                    <div class="page-wrapper">
                        <div class="header">
                            <c:import url="../common/header.jsp" charEncoding="utf-8" />
                        </div>
                        <div class="page-buffer">
                            <div id="menu">

                                <br> <br>
                                <c:import url="menu_user.jsp" charEncoding="utf-8" />
                            </div>
                            <div>
                                <table summary="position table">
                                    <!-- блок для позиционирования меню -->
                                </table>
                            </div>

                            <div id="page-content">
                                <h2>
                                    <fmt:message key="userorders.availablecar" /> :
                                </h2>
                                <table summary="order car confirm" id="usertable">

                                    <tr>
                                        <th id="order car name">
                                            <fmt:message key="carcontrol.carname" />
                                        </th>
                                        <th id="order car fuel">
                                            <fmt:message key="carcontrol.carfuel" />
                                        </th>
                                        <th id="order car color">
                                            <fmt:message key="carcontrol.carcolor" />
                                        </th>
                                        <th id="order car body">
                                            <fmt:message key="carcontrol.carbody" />
                                        </th>
                                        <th id="order car transmission">
                                            <fmt:message key="carcontrol.transmission" />
                                        </th>
                                        <th id="order price for period">
                                            <fmt:message key="userorders.priceperiod" />
                                        </th>
                                    </tr>

                                    <c:forEach var="car" items="${requestScope.availableCars}">
                                        <c:if test="${car.active == true}" var="testcif">
                                            <tr>
                                                <td>${car.name}</td>
                                                <td>${car.fuel}</td>
                                                <td>${car.color}</td>
                                                <td>${car.body}</td>
                                                <td>
                                                    <c:if test="${car.transmissionType == 'AUTOMATIC'}">
                                                        <fmt:message key="carcontrol.automatic" />
                                                    </c:if>
                                                    <c:if test="${car.transmissionType == 'MANUAL'}">
                                                        <fmt:message key="carcontrol.manual" />
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <center>${car.price * requestScope.dayCol}</center>
                                                </td>
                                                <td>
                                                    <form action="final_order_page" method="post">
                                                        <input type="hidden" name="idCar" value="${car.id}" />
                                                        <input type="hidden" name="startDate" value="${requestScope.startDate1}" />
                                                        <input type="hidden" name="endDate" value="${requestScope.endDate1}" />
                                                        <input type="hidden" name="amount" value="${car.price * requestScope.dayCol}" /> <input type="hidden" name="dayCol" value="${dayCol}" />
                                                        <input type="submit" value=<fmt:message key="userorders.choose" /> /><br />
                                                    </form>

                                                </td>
                                            </tr>
                                        </c:if>

                                    </c:forEach>

                                </table>

                            </div>

                        </div>

                    </div>
                    <div class="footer">
                        <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                    </div>
                </c:if>

            </body>

            </html>
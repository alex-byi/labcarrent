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
                                <fmt:message key="userorders.choosencar" /> :
                            </h2>
                            <table summary="final order table car" id="usertable">
                                <thead>
                                    <tr>
                                        <th id="final order car">
                                            <fmt:message key="carcontrol.carname" />
                                        </th>
                                        <th id="final order car fuel">
                                            <fmt:message key="carcontrol.carfuel" />
                                        </th>
                                        <th id="final order car color">
                                            <fmt:message key="carcontrol.carcolor" />
                                        </th>
                                        <th id="final order car body">
                                            <fmt:message key="carcontrol.carbody" />
                                        </th>
                                        <th id="final order car transmission">
                                            <fmt:message key="carcontrol.transmission" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:set var="car" scope="request" value="${requestScope.selectCar}" />
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

                                    </tr>
                                </tbody>
                            </table>

                            <h3>
                                <fmt:message key="userorders.yourorder" />
                            </h3>
                            <table summary="final order table order" id="usertable">
                                <thead>
                                    <tr>
                                        <th id="final order date">
                                            <fmt:message key="allorders.date" />
                                        </th>
                                        <th id="final order start date">
                                            <fmt:message key="allorders.startdate" />
                                        </th>
                                        <th id="final order end date">
                                            <fmt:message key="allorders.enddate" />
                                        </th>
                                        <th id="final order car">
                                            <fmt:message key="allorders.car" />
                                        </th>
                                        <th id="final order amount">
                                            <fmt:message key="allorders.amount" />
                                        </th>
                                        <th id="final order days">
                                            <fmt:message key="allorders.days" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="order" scope="request" value="${currentOrder}" />
                                    <tr>
                                        <td>${order.dateOrder}</td>
                                        <td>${order.startDate}</td>
                                        <td>${order.endDate}</td>
                                        <td>${car.name}</td>
                                        <td>${order.amount}</td>
                                        <td>${order.dayCol}</td>

                                    </tr>
                                </tbody>
                            </table>
                            <h3>
                                <fmt:message key="userorders.correct" />
                            </h3>
                            <table summary="confirm" id="usertable">
                                <tr>
                                    <td>
                                        <center>
                                            <form action="confirmOrder" method="post">
                                                <input type="submit" value=<fmt:message key="addcar.submit" /> /><br />
                                            </form>
                                        </center>
                                    </td>
                                </tr>
                            </table>
                        </div>

                    </div>

                </div>
                <div class="footer">
                    <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                </div>


            </body>

            </html>
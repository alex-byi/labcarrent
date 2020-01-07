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
                                <fmt:message key="menu.allorders" />
                            </h2>


                            <table summary="all orders" id="usertable">
                                <thead>
                                    <tr>
                                        <th id="order id">
                                            <fmt:message key="allorders.id" />
                                        </th>
                                        <th id="order date">
                                            <fmt:message key="allorders.date" />
                                        </th>
                                        <th id="order start date">
                                            <fmt:message key="allorders.startdate" />
                                        </th>
                                        <th id="order end date">
                                            <fmt:message key="allorders.enddate" />
                                        </th>
                                        <th id="order payment">
                                            <fmt:message key="allorders.payment" />
                                        </th>
                                        <th id="order damage">
                                            <fmt:message key="allorders.damage" />
                                        </th>
                                        <th id="order car">
                                            <fmt:message key="allorders.car" />
                                        </th>
                                        <th id="order days">
                                            <fmt:message key="allorders.days" />
                                        </th>
                                        <th id="order additional bill"></th>
                                        <fmt:message key="allorders.additionalbill" />
                                        </th>
                                        <th id="order user">
                                            <fmt:message key="allorders.user" />
                                        </th>
                                        <th id="order amount">
                                            <fmt:message key="allorders.amount" />
                                        </th>
                                        <th id="order cancel">
                                            <fmt:message key="allorders.cancel" />
                                        </th>
                                        <th id="order complete">
                                            <fmt:message key="allorders.complete" />
                                        </th>
                                        <th id="order create additional bill">
                                            <fmt:message key="allorders.createaddbill" />
                                        </th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${allOrders}">
                                        <tr>

                                            <td>${order.id}</td>
                                            <c:set var="id" scope="session" value="${order.id}" />
                                            <td>${order.dateOrder}</td>
                                            <td>${order.startDate}</td>
                                            <td>${order.endDate}</td>
                                            <td>
                                                <c:if test="${order.paid == true}">
                                                    <fmt:message key="allorders.paid" />
                                                </c:if>
                                                <c:if test="${order.paid != true}">
                                                    <fmt:message key="allorders.notpaid" />
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${order.crash == true}">
                                                    <fmt:message key="allorders.crash" />
                                                </c:if>
                                                <c:if test="${order.crash != true}">
                                                    <fmt:message key="allorders.notcrash" />
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:forEach var="car" items="${carsO}">
                                                    <c:if test="${order.idCar == car.id }">
                                                        ${car.name}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${order.dayCol}</td>
                                            <td>${order.crashBill}</td>
                                            <td>
                                                <c:forEach var="user" items="${usersO}">
                                                    <c:if test="${order.idUser == user.id }">
                                                        ${user.fullName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${order.amount}</td>
                                            <td>
                                                <c:if test="${order.canceled == false && order.complete == false}">
                                                    <form action="cancelOrderAdmin" method="post">
                                                        <input type="hidden" name="orderId" value="${order.id}" />
                                                        <input type="hidden" name="userType" value="admin" />
                                                        <input type="text" required placeholder="<fmt:message key='allorders.cause'/>" size="8" maxlength="40" name="reason" value="" /><input type="submit" value="<fmt:message key='allorders.cancel'/>"
                                                            onclick="return confirm('?')" />
                                                    </form>
                                                </c:if>
                                                <c:if test="${order.canceled != false}"> ${order.rejectReason}</c:if>

                                            </td>
                                            <td>
                                                <c:if test="${order.complete == false && order.canceled == false}">
                                                    <form action="orderComplete" method="post">
                                                        <input type="hidden" name="orderId" value="${order.id}" /> <input type="submit" value="<fmt:message key='allorders.complete'/>" onclick="return confirm('?')" />
                                                    </form>
                                                </c:if>
                                                <c:if test="${order.complete == true}">
                                                    <fmt:message key='allorders.successfullyComplete' />
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:if test="${order.crash == false}">
                                                    <form action="add_crash" method="post">
                                                        <input type="hidden" name="orderId" value="${order.id}" />
                                                        <input type="hidden" name="carId" value="${order.idCar}" /> <input type="hidden" name="userId" value="${order.idUser}" /><input type="submit" value="<fmt:message key='allorders.createaddbill'/>"
                                                        />
                                                    </form>
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
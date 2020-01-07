<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <title>
        <fmt:message key="header.title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="..\css\style.css" type="text/css">
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
            <p>
            <table summary="search and add car table" id="usertable">
                <tr>
                    <th id="search car by transmission">
                        <fmt:message key="search.transmission"/>
                    </th>
                    <th id="search car by fuel">
                        <fmt:message key="search.fuel"/>
                    </th>
                    <th id="add new car">
                        <fmt:message key="carcontrol.addcar"/>
                    </th>
                </tr>
                <tr>
                    <td>
                        <form action="getTransmissionCars" method="post">
                            <select name="searchtransmission" required>
                                <option disabled>
                                    <fmt:message key="addcar.transmission"/>
                                </option>

                                <option value="AUTOMATIC">
                                    <fmt:message key="addcar.automatic"/>
                                </option>
                                <option value="MANUAL">
                                    <fmt:message key="addcar.manual"/>
                                </option>
                            </select><input type="submit" value=" <fmt:message
                                        key='search.search'/>"/><br/>
                        </form>
                    </td>
                    <td>
                        <form action="getFuelCars" method="post">
                            <select name="searchFuel" required>
                                <option disabled>
                                    <fmt:message key="addcar.fueltype"/>
                                </option>
                                <option value="бензин">
                                    <fmt:message key="addcar.gas"/>
                                </option>
                                <option value="дизель">
                                    <fmt:message key="addcar.diesel"/>
                                </option>
                                <option value="электричество">
                                    <fmt:message key="addcar.electricity"/>
                                </option>
                            </select><input type="submit" value=" <fmt:message
                                        key='search.search'/>"/><br/>
                        </form>
                    </td>
                    <td>

                        <a href="/carrent/admin/add_car">
                            <fmt:message key="carcontrol.addcar"/>
                        </a>
                    </td>
                </tr>
            </table>
            <p>
                <br>
                <c:if test="${requestScope.fuelCar != null}" var="testcif">
                    <table summary="fuel cars table" id="usertable">
                        <tr>
                            <th id="car id">
                                <fmt:message key="carcontrol.id"/>
                            </th>
                            <th id="car name">
                                <fmt:message key="carcontrol.carname"/>
                            </th>
                            <th id="car price">
                                <fmt:message key="carcontrol.carprice"/>
                            </th>
                            <th id="car fuel type">
                                <fmt:message key="carcontrol.carfuel"/>
                            </th>
                            <th id="car color">
                                <fmt:message key="carcontrol.carcolor"/>
                            </th>
                            <th id="car body type">
                                <fmt:message key="carcontrol.carbody"/>
                            </th>
                            <th id="car transmission type">
                                <fmt:message key="carcontrol.transmission"/>
                            </th>
                            <th id="car activity">
                                <fmt:message key="carcontrol.activity"/>
                            </th>
                            <th id="activate deactivate car">
                                <fmt:message key="carcontrol.activ"/>
                            </th>
                        </tr>
                        <c:forEach var="car" items="${fuelCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.id}</td>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
                                    <td>${car.fuel}</td>
                                    <td>${car.color}</td>
                                    <td>${car.body}</td>
                                    <td>
                                        <c:if test="${car.transmissionType == 'AUTOMATIC'}">
                                            <fmt:message key="carcontrol.automatic"/>
                                        </c:if>
                                        <c:if test="${car.transmissionType == 'MANUAL'}">
                                            <fmt:message key="carcontrol.manual"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true}">
                                            <fmt:message key="carcontrol.active"/>
                                        </c:if>
                                        <c:if test="${car.active != true}">
                                            <fmt:message key="carcontrol.notactive"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="del_car"/> <input type="submit"
                                                                                                          value="<fmt:message key='carcontrol.deactivate'/>"
                                                                                                          onclick="return confirm('?')"
                                            />
                                            </form>
                                        </c:if>
                                        <c:if test="${car.active == false }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="activate_car"/> <input
                                                    type="submit" value="<fmt:message key='carcontrol.activate'/>"
                                                    onclick="return confirm('?')"/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>

                    </table>
                </c:if>

                <c:if test="${requestScope.transmissionCar != null}" var="testcif">
                    <table summary="transmission cars table" id="usertable">
                        <tr>
                            <th id="car id">
                                <fmt:message key="carcontrol.id"/>
                            </th>
                            <th id="car name">
                                <fmt:message key="carcontrol.carname"/>
                            </th>
                            <th id="car price">
                                <fmt:message key="carcontrol.carprice"/>
                            </th>
                            <th id="car fuel type">
                                <fmt:message key="carcontrol.carfuel"/>
                            </th>
                            <th id="car color">
                                <fmt:message key="carcontrol.carcolor"/>
                            </th>
                            <th id="car body type">
                                <fmt:message key="carcontrol.carbody"/>
                            </th>
                            <th id="car transmission type">
                                <fmt:message key="carcontrol.transmission"/>
                            </th>
                            <th id="car activity">
                                <fmt:message key="carcontrol.activity"/>
                            </th>
                            <th id="activate deactivate car">
                                <fmt:message key="carcontrol.activ"/>
                            </th>
                        </tr>
                        <c:forEach var="car" items="${transmissionCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.id}</td>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
                                    <td>${car.fuel}</td>
                                    <td>${car.color}</td>
                                    <td>${car.body}</td>
                                    <td>
                                        <c:if test="${car.transmissionType == 'AUTOMATIC'}">
                                            <fmt:message key="carcontrol.automatic"/>
                                        </c:if>
                                        <c:if test="${car.transmissionType == 'MANUAL'}">
                                            <fmt:message key="carcontrol.manual"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true}">
                                            <fmt:message key="carcontrol.active"/>
                                        </c:if>
                                        <c:if test="${car.active != true}">
                                            <fmt:message key="carcontrol.notactive"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="del_car"/> <input type="submit"
                                                                                                          value="<fmt:message key='carcontrol.deactivate'/>"
                                                                                                          onclick="return confirm('?')"
                                            />
                                            </form>
                                        </c:if>
                                        <c:if test="${car.active == false }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="activate_car"/> <input
                                                    type="submit" value="<fmt:message key='carcontrol.activate'/>"
                                                    onclick="return confirm('?')"/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>

                    </table>
                </c:if>

                <c:if test="${requestScope.transmissionCar == null && requestScope.fuelCar == null}" var="testcif">
            <p>
                <br>
            <h2>
                <fmt:message key="menu.carcontrol"/>
            </h2>
            <table summary="all cars table" id="usertable">

                <tr>
                    <th id="car id">
                        <fmt:message key="carcontrol.id"/>
                    </th>
                    <th id="car name">
                        <fmt:message key="carcontrol.carname"/>
                    </th>
                    <th id="car price">
                        <fmt:message key="carcontrol.carprice"/>
                    </th>
                    <th id="car fuel type">
                        <fmt:message key="carcontrol.carfuel"/>
                    </th>
                    <th id="car color">
                        <fmt:message key="carcontrol.carcolor"/>
                    </th>
                    <th id="car body type">
                        <fmt:message key="carcontrol.carbody"/>
                    </th>
                    <th id="car transmission type">
                        <fmt:message key="carcontrol.transmission"/>
                    </th>
                    <th id="car activity">
                        <fmt:message key="carcontrol.activity"/>
                    </th>
                    <th id="activate deactivate car">
                        <fmt:message key="carcontrol.activ"/>
                    </th>
                </tr>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td>${car.id}</td>
                        <td>${car.name}</td>
                        <td>${car.price}</td>
                        <td>${car.fuel}</td>
                        <td>${car.color}</td>
                        <td>${car.body}</td>
                        <td>
                            <c:if test="${car.transmissionType == 'AUTOMATIC'}">
                                <fmt:message key="carcontrol.automatic"/>
                            </c:if>
                            <c:if test="${car.transmissionType == 'MANUAL'}">
                                <fmt:message key="carcontrol.manual"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${car.active == true}">
                                <fmt:message key="carcontrol.active"/>
                            </c:if>
                            <c:if test="${car.active != true}">
                                <fmt:message key="carcontrol.notactive"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${car.active == true }">
                                <form action="deactivateCar" method="post">
                                    <input type="hidden" name="id" value="${car.id}"/> <input type="submit"
                                                                                              value="<fmt:message key='carcontrol.deactivate'/>"
                                                                                              onclick="return confirm('?')"/>
                                </form>
                            </c:if>
                            <c:if test="${car.active == false }">
                                <form action="activateCar" method="post">
                                    <input type="hidden" name="id" value="${car.id}"/> <input type="submit"
                                                                                              value="<fmt:message key='carcontrol.activate'/>"
                                                                                              onclick="return confirm('?')"/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </c:if>
        </div>
    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>

</html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema"
      xmlns:spring="http://www.w3.org/1999/html">

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
                                <fmt:message key="menu.carcontrol" />
                            </h2>

                            <spring:form method="post" modelAttribute="car" action="addCarCommand">
                                <table summary="add car table" id="usertable">
                                    <tr>
                                        <th id="add car" colspan=2>
                                            <center>
                                                <font size=5>
                                                    <fmt:message key="carcontrol.addcar" />
                                                </font>
                                            </center>
                                        </th>
                                    </tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.carname" />
                                            <h7>*</h7>
                                            <spring:input path="name" size= '37' maxlength= '50' />
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.carprice" />
                                            <h7>*</h7>
                                            <spring:input path="price" min="0"  size= '26' maxlength= '125' />
                                        </td>
                                    <tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.carfuel" />
                                            <h7>*</h7>
                                            <spring:select path="fuel" >
                                                <spring:option value="бензин">
                                                    <fmt:message key="addcar.gas" />
                                                </spring:option>
                                                <spring:option value="дизель">
                                                    <fmt:message key="addcar.diesel" />
                                                </spring:option>
                                                <spring:option value="электричество">
                                                    <fmt:message key="addcar.electricity" />
                                                </spring:option>
                                            </spring:select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.carcolor" />
                                            <h7>*</h7>
                                            <spring:input path="color" type="text" size= '26'  maxlength= '10'  />
                                            </td>
                                    </tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.carbody" />
                                            <h7>*</h7>

                                            <spring:select path="body" >
                                                <spring:option value="седан">
                                                    <fmt:message key="addcar.sedan" />
                                                </spring:option>
                                                <spring:option value="универсал">
                                                    <fmt:message key="addcar.touring" />
                                                </spring:option>
                                                <spring:option value="купе">
                                                     <fmt:message key="addcar.coupe" />
                                                </spring:option>
                                                <spring:option value="кабриолет">
                                                    <fmt:message key="addcar.cabriolet" />
                                                </spring:option>
                                                <spring:option value="минивэн">
                                                    <fmt:message key="addcar.minivan" />
                                                </spring:option>
                                            </spring:select>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td valign=top>
                                            <fmt:message key="carcontrol.transmission" />
                                            <h7>*</h7>
                                            <spring:select path="transmissionType">
                                                <spring:option value="AUTOMATIC">
                                                    <fmt:message key="addcar.automatic" />
                                                </spring:option>
                                                <spring:option value="MANUAL">
                                                    <fmt:message key="addcar.manual" />
                                                </spring:option>
                                            </spring:select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align=center>
                                            <spring:button>
                                                <fmt:message key='addcar.submit'/>
                                            </spring:button>
                                        </td>
                                    </tr>
                                </table>
                            </spring:form>




                        </div>

                    </div>

                </div>
                <div class="footer">
                    <c:import url="../common/footer.jsp" charEncoding="utf-8" />
                </div>

            </body>

            </html>
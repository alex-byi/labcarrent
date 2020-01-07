<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

            <!DOCTYPE html>
            <html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">

            <head>
                <fmt:setLocale value="${locale}" scope="session" />
                <fmt:setBundle basename="title" />
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title></title>
                <link rel="stylesheet" href="css/style.css" type="text/css">
            </head>

            <body>
                <div id="login">

                 <form name="menu" class="menu" action="/carrent/user/user_page" method="get">
                            <input type="submit"
                                value="<fmt:message key='menu.main'/>" /><br />
                        </form>
                        <form name="menu" class="menu" action="/carrent/user/order_page" method="get">
                          <input type="submit"
                                value="<fmt:message key='menu.neworder'/>" /><br />
                        </form>
                        <form name="menu" class="menu" action="/carrent/user/orders" method="get">
                           <input type="submit"
                                value="<fmt:message key='menu.orders'/>" /><br />
                        </form>
                        <form name="menu" class="menu" action="/carrent/user/crash_page" method="get">
                           <input type="submit"
                                value="<fmt:message key='menu.crashbills'/>" /><br />
                        </form>

                        <form name="menu" class="menu" action="/carrent/logout" method="get">
                           <input type="submit"
                                value="<fmt:message key='menu.logout'/>" /><br />
                        </form>

                </div>
            </body>

            </html>
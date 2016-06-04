<%@page import="Models.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import  = "Models.Order"%>

<html>
    <head>
        <title>Peter Piper's Pickled Peppers</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheets/home.css">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        
        <style>
            table, th, td {
                border: 1px solid white;
            }
        </style>
    </head>
    <body>
        <a href="/"><h1>Peter sells peppers</h1></a>
        <h2>Order Confirmation</h2>
        <br>
        <%
            Order order = (Order) request.getAttribute("order");
        %>
        <table>
            <tbody>
                <tr>
                    <td><b>Name</b></td>
                    <td><%=order.FirstName%> <%=order.LastName%></td>
                </tr>
                <tr>
                    <td><b>Credit Card</b></td>
                    <td><%=order.CreditCardNumber%></td>
                </tr>
                <tr>
                    <td><b>Street Address</b></td>
                    <td><%=order.StreetAddress%></td>
                </tr>
                <tr>
                    <td><b>Zip Code</b></td>
                    <td><%=order.Zip%></td>
                </tr>
                <tr>
                    <td><b>State</b></td>
                    <td><%=order.State%></td>
                </tr>
                <tr>
                    <td><b>Phone Number</b></td>
                    <td><%=order.PhoneNumber%></td>
                </tr>
                <tr>
                    <td><b>Shipping Speed</b></td>
                    <td><%=order.ShippingSpeed%></td>
                </tr>
            </tbody>
        </table>
        <br><br>
        <!--Table for orders-->
        <table style="width:90%">
            <thead>
                <tr>
                    <th>
                        Order ID
                    </th>
                    <th>
                        Pepper Name
                    </th>
                    <th>
                        ID
                    </th>
                    <th>
                        Pepper ID
                    </th>
                    <th>
                        Quantity
                    </th>
                    <th>
                        Subtotal
                    </th>
                </tr>
            </thead>

        <tbody>
    <%
        ArrayList<OrderItem> orderItems = order.OrderItems;
        for(OrderItem orderItem : orderItems) {
    %>
            <tr style="text-align:left;">
                <td>
                    <%= orderItem.OrderID %>
                </td>
                <td>
                    <%= orderItem.getPepper().PepperName %>
                </td>
                <td>
                    <%= orderItem.ID %>
                </td>
                <td>
                    <%= orderItem.PepperID %>
                </td>
                <td>
                    <%= orderItem.Quantity %>
                </td>
                <td>
                    <%= orderItem.Subtotal %>
                </td>
            </tr>
        <%
            }
        %> 
            <tr>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                    TOTAL:
                </td>
                <td align="right">
                    <%= order.getTotal() %>
                </td>
            </tr>
        </tbody>
    </table>
                     
    </body>
</html>

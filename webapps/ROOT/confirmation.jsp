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
    </head>
    <body>
        <a href="/"><h1>Peter sells peppers</h1></a>
        <h2>Order Confirmation</h2>
        <br>
        <%
            Order order = (Order) request.getAttribute("order");
        %>
        <table>
            <thead></thead>
            <tbody>
                <tr>
                    <td> Name: <%=order.FirstName%> <%=order.LastName%></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Credit Card: <%=order.CreditCardNumber%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Street Address: <%=order.StreetAddress%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Zip Code: <%=order.Zip%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>State: <%=order.State%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Phone Number: <%=order.PhoneNumber%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Shipping Speed: <%=order.ShippingSpeed%></td>
                    <td></td>
                </tr>
            </tbody>
        </table>

        <table>
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
            </tbody>
        </table>
                    
    </body>
</html>

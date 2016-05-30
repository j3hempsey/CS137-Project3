<%-- 
    Document   : productdetail.jsp
    Created on : May 29, 2016, 5:53:08 AM
    Author     : peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import  = "Models.Pepper"%>
<%@page import  = "Models.ShoppingCart"%>
<%--@page import  = "Models.SessionCounter"--%>
<%@page import  = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Peter Sells Peppers - Assorted Peppers</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../stylesheets/home.css">
        <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
	<script src="../scripts/jquery_2.2.3.min.js"></script>
    </head>
    <body>
            <%
                ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("shoppingCart"); 
                if(sc != null) {
            %>
        <div style="text-align:right;padding-right:50px;">
            <a href="/shoppingcart">
                Shopping Cart: <%= sc.ShoppingCartItems.size()%>
            </a>
        </div>
            <%
                }
            %>
        <% Pepper p = (Pepper) request.getAttribute("pepper"); %>
        <a href="/" style="text-decoration: none;">
            <h1>Peter sells peppers</h1>
        </a>
        <div class='center-div' id='management' >
                    <a href='../management/management.html'>Management Team</a>
        </div>
        <h2 id="pepper-name"><%= p.PepperName %></h2>
	<hr>
        <div class='prod-info'>
            <div class='float-img inline'>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <div class='product-img'>
                                <img src='../<%= p.ImageUrl %>' alt='Guantanamo Pepper'
                     height="180" width="300" >
                                </div>
                                </td>
                        </tr>
                    </tbody>
                </table>   
            </div>
            <div class="prod-detail inline">
                <div class='description'>
                    <%= p.Description %>
                </div>
            <div class='price'> 
                Price: $<%= String.format("%.2f", p.Price) %>
            </div>
            
            <div class ="prod-checkout">
                <form name="addToCart" method="post">
                    <input type="hidden" name="pepperId" id="pepperId" value="<%=p.ID%>" />
                    <input type="number" name="quantity" id="quantity" placeholder="quantity"/>
                    <input type="submit" value="Add to Cart" id="submit-btn"/>
                    <%
                        String error = (String) request.getAttribute("error");
                        if(error != null && error != "") {
                    %>
                    <div style="color:red;">
                        <%= error %>
                    </div>
                    <%
                        }
                    %>
                </form>
            </div>
            </div>
            
        </div>
        
        <br>
        <br>  
    </body>
</html>
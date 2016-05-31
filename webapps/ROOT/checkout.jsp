<%--
    Document   : shoppingcart.jsp
    Created on : May 29, 2016, 4:00:45 PM
    Author     : peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import  = "Models.ShoppingCart"%>
<%@page import  = "Models.ShoppingCartItem"%>
<%@page import  = "java.util.ArrayList"%>

<!DOCTYPE html>
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
        <a href="/" style="text-decoration: none;">
            <h1>Peter sells peppers</h1>
        <a/>
        <div class="subtitle-div">
            Suppliers of the universe's best peppers money can buy.
        </div>
        <a href='management/management.html'>
            <div class='center-div' id='management'>
                Management Team
            </div>
        </a>
        <hr>
        <div class="order-summary">
            <%
                ShoppingCart shoppingCart = (ShoppingCart) request.getAttribute("shoppingCart");
                if(shoppingCart != null) {

            %>
            <table>
                <thead>
                    <tr>
                        <th>
                            Pepper Name
                        </th>
                        <th>
                            Description
                        </th>
                        <th>
                            Price
                        </th>
                        <th>
                            Quantity
                        </th>
                        <th>
                            Subtotal
                        </th>
                        <th>

                        </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<ShoppingCartItem> shoppingCartItems = shoppingCart.ShoppingCartItems;
                        for(ShoppingCartItem item : shoppingCartItems) {
                    %>
                    <tr style="text-align:left;">
                        <td>
                            <%= item.Pepper.PepperName %>
                        </td>
                        <td>
                            <%= item.Pepper.Description %>
                        </td>
                        <td>
                            $<%= String.format("%.2f", item.Pepper.Price) %>
                        </td>
                        <td>
                            <input type="number" value="<%= item.Quantity %>" id="quantity" style="width:60px;" onchange="updateQuantity(this, <%= item.Pepper.ID%>)"/>
                        </td>
                        <td>
                            $<span id="subtotal_<%= item.Pepper.ID%>"><%= String.format("%.2f",item.Pepper.Price * item.Quantity) %></span>
                        </td>
                        <td>
                            <a href="#" style="color:red;" onclick="removeItem(this, <%= item.Pepper.ID%>)">Remove</a>
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
                        Total:
                        </td>
                        <td>
                            $<span id="total"><%= String.format("%.2f", shoppingCart.getTotal()) %> </span>
                        </td>
                    </tr>
                </tbody>
            </table>
            <%
                } else {
            %>
            <div>
                You have no items in your shopping cart!
            </div>
            <%
                }
            %>

        </div>
        <div class='order'>
		<hr>
            <form method='post' id='order-form' onsubmit='return processForm();'>
                <table class='ship-form'>
                    <tbody>
                        <tr>
                            <td>First Name:</td>
                            <td><input type="text" class="in_txt" id="first-name" name="first_name" onkeyup="checkFirstNameWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="first-name-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="first-name-bad"></img>
                        </td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" class="in_txt" id="last-name" name="last_name" onkeyup="checkLastNameWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="last-name-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="last-name-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Credit Card:</td>
                            <td><input type="text" class="in_txt" id="credit-card" name="credit_card" onkeyup="checkCreditCardWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="credit-card-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="credit-card-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Street Address:</td>
                            <td><input type="text" class="in_txt" id="address" name="address" onkeyup="checkAddressWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="address-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="address-bad"></img>
							</td>
                        </tr>
						<tr>
                            <td>Phone Number:</td>
                            <td><input type="text" class="in_txt" id="phone" name="phone" onkeyup="checkPhoneWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="phone-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="phone-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Zip code:</td>
                            <td><input type="text" class="in_txt" id="zip-code" name="zip_code" onkeyup="checkZipCodeWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="zip-code-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="zip-code-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td><input type="text" class="in_txt" id="state" name="state" onkeyup="checkStateWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="state-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="state-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Shipping speed:</td>
                            <td>
                                <input type="radio" name="ship" value="one-day"> Burn my mouth tomorrow<br>
                                <input type="radio" name="ship" value="two-day"> Burn my mouth two days from now<br>
                                <input type="radio" name="ship" value="other" checked="checked"> Burn my mouth whenever it gets here
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <div id='submit-btn-div'>
                    <input type='submit' value='Purchase' id='submit-btn'>
                    <hr>
                </div>
            </form>
        </div>

        <script src="../scripts/jquery_2.2.3.min.js"></script>
        <script type="text/javascript">
            function updateQuantity(self, pepperId) {
                var quantity = self.value;
                $.ajax({
                   type: 'POST',
                   url: '/updateshoppingcart',
                   data: {
                       updateType: 'quantity',
                       quantity: quantity,
                       pepperId: pepperId
                   },
                   success: function(result) {
                       if(result.Success) {
                           var total = result.Total;
                           var subtotal = result.Subtotal;
                           $('#subtotal_' + pepperId).html(subtotal);
                           $('#total').html(total);
                       }
                   },
                   failure: function(){ }
                });
            }

            function removeItem(self, pepperId) {
                $.ajax({
                   type: 'POST',
                   url: '/updateshoppingcart',
                   data: {
                       updateType: 'remove',
                       pepperId: pepperId
                   },
                   success: function(result) {
                       if(result.Success) {
                           var total = result.Total;
                           self.parentElement.parentElement.remove();
                           $('#total').html(total);
                       }
                   },
                   failure: function(){ }
                });
            }
        </script>
    </body>
</html>

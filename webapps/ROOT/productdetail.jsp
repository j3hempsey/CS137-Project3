<%-- 
    Document   : productdetail.jsp
    Created on : May 29, 2016, 5:53:08 AM
    Author     : peter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import  = "Models.Pepper"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Peter Sells Peppers - Assorted Peppers</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../stylesheets/home.css">
        <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
		<script type="text/javascript" src="../scripts/validate_form.js"></script>
		<script src="../scripts/jquery_2.2.3.min.js"></script>
    </head>
    <body>
        <% Pepper p = (Pepper) request.getAttribute("pepper"); %>
        <h1>Peter sells peppers</h1>
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
                                <div class='product-img'>
                <img src='../images/assorted_pepper1.jpeg' alt='Guantanamo Pepper'
                    height="180" width="300" > 
                                </div>
                                </td>
                        </tr>
                    </tbody>
                </table>   
            </div>
            <div class="prod-detail inline">
                <div class='description'>
                So mild, it'll drive you wild. These assorted peppers will change the way you view peppers. This pepper pack includes assorted peppers that rate above 9000 in the HTH (Hotter than Hell) Index.  
                            </div>
            <div class='price'> 
                Price: $<%= p.Price %>
            </div>
            </div>
        </div>
        <div class='order'>
		<hr>
            <form method='post' id='order-form' onsubmit='return processForm();'>
                <table class='ship-form'>
                    <tbody>
                        <tr>
                            <td>First Name:</td>
                            <td><input type="text" class="in_txt" id="first-name" onkeyup="checkFirstNameWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="first-name-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="first-name-bad"></img>
                        </td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" class="in_txt" id="last-name" onkeyup="checkLastNameWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="last-name-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="last-name-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Credit Card:</td>
                            <td><input type="text" class="in_txt" id="credit-card" onkeyup="checkCreditCardWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="credit-card-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="credit-card-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Street Address:</td>
                            <td><input type="text" class="in_txt" id="address" onkeyup="checkAddressWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="address-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="address-bad"></img>
							</td>
                        </tr>
						<tr>
                            <td>Phone Number:</td>
                            <td><input type="text" class="in_txt" id="phone" onkeyup="checkPhoneWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="phone-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="phone-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>Zip code:</td>
                            <td><input type="text" class="in_txt" id="zip-code" onkeyup="checkZipCodeWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="zip-code-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="zip-code-bad"></img>
							</td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td><input type="text" class="in_txt" id="state" onkeyup="checkStateWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="state-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="state-bad"></img>
							</td>
                        </tr>
                        <tr> 
                            <td>Quantity:</td>
                            <td><input type="text" class="in_txt" id="quantity" onkeyup="checkQuantityWhileTyping()">
							<img src="../images/red_x.gif" alt="Error!" style="width: 20px; height: 20px;" class="hidden" id="quantity-good"></img>
							<img src="../images/green_check.png" alt="Success!" style="width: 20px; height: 20px;" class="hidden" id="quantity-bad"></img>
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
        
        <br><br>
                <div class='center-div' id='management' >
                    <a href='../management/management.html'>Management Team</a> | <a href='/index.html'>Home</a>
                </div>
            </a>
    </body>
</html>
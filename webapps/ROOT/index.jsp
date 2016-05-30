<%@page import  = "java.util.ArrayList" %>
<%@page import  = "Models.Pepper"%>
<%@page import  = "Models.ShoppingCart"%>

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
        <h1>Peter sells peppers</h1>
        <div class="subtitle-div">
            Suppliers of the universe's best peppers money can buy.
        </div>
        <a href='management/management.html'>
            <div class='center-div' id='management'>
                Management Team
            </div>
        </a>
        
        <div align="center">
            
        <%
            ArrayList<Pepper> recentItems = (ArrayList<Pepper>) request.getAttribute("recentItems");
            if(recentItems != null && recentItems.size() > 0){
        %>
        <div style="text-align:center;">Recently Viewed Items</div>
        <%
                for (int i = recentItems.size() - 1; i >= 0; i--) {
                    Pepper p = recentItems.get(i);
        %>
        <div>
            <a href="/product?id=<%= p.ID %>">
                <%= recentItems.get(i).PepperName %>
            </a>
        </div>
        <% }
        }%>
        </div>
<% ArrayList<Pepper> peppers = (ArrayList<Pepper>)request.getAttribute("peppers");
  if(peppers.size() > 0) {
  %>
    <table class='home-table'>
        <thead>
        <td>Image</td>
        <td>Pepper Name</td>
        <td>Pepper Type</td>
        <td>Spicy-ness</td>
        <td>Price</td>
        </thead>
        <tbody>
        </tbody>    
            <% for (Pepper pepper : peppers) { %>
                <tr>
                    <td>
                        <a href="/product?id=<%= pepper.ID%>">
                            <img src="<%= pepper.ImageUrl %>" width="300"/>
                        </a>
                    </td>
                    <td>
                        <%= pepper.PepperName %>
                    </td>
                    <td>                   
                        <%= pepper.PepperType %>
                    </td>
                    <td>
                        <%= pepper.SpicyCreative %>
                    </td>
                    <td>
                        <%= String.format("%.2f", pepper.Price) %>
                    </td>
                </tr>
                <% } %>
        <tbody>
    </body>
   <% 
    }
   %>
</html>

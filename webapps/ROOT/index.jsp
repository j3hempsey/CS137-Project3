<%@page import  = "java.util.ArrayList" %>
<%@page import  = "Models.Pepper"%>

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
        <h1>Peter sells peppers</h1>
        <div class="subtitle-div">
            Suppliers of the universe's best peppers money can buy.
        </div>
        <a href='management/management.html'>
            <div class='center-div' id='management'>
                Management Team
            </div>
        </a>
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
                        <%= pepper.Price %>
                    </td>
                </tr>
                <% } %>
        <tbody>
    </body>
   <% 
    }
   %>
</html>

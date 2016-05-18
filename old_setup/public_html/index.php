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

        <?php
            $servername = "sylvester-mccoy-v3.ics.uci.edu";
            $db="inf124grp13";
            $username = "inf124grp13";
            $password = "4a=eDuVu";

            // Create connection
            $conn = new mysqli($servername, $username, $password, $db);
            // Check connection
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            } 
            // echo "Connected successfully";
            $sql = "SELECT * FROM  `peppers`";
            $result = $conn->query($sql);
    
            if ($result->num_rows > 0) {
                echo "<table class='home-table'>
                    <thead>
                    <td>Image</td>
                    <td>Pepper Name</td>
                    <td>Pepper Type</td>
                    <td>Spicy-ness</td>
                    <td>Price</td>
                    </thead>
                    <tbody>";
                // output data of each row
                while($row = $result->fetch_assoc())
                {
                    $image_url = $row['image_url'];
                    $url = $row['url'];
                    $pepper_name = $row['pepper_name'];
                    $pepper_type = $row['pepper_type'];                    
                    $spicy_creative = $row['spicy_creative'];
                    $price = $row['price'];
                    echo "<tr>
                            <td><div class='hover-img'>
                                <a href='".$url."'>
                                <img src='".$image_url."'
                                alt='".$pepper_name."'
                                width='250'
                                height='200'></a>
                            </div></td>
                            <td>".$pepper_name."</td>
                            <td>".$pepper_type."</td>
                            <td>".$spicy_creative."</td>
                            <td>$".$price."</td>
                        </tr>";   
                }
                echo "</tbody>";
                echo "</table>";
            } else {
                echo "0 results";
            }
            $conn->close();
        ?>
    </body>
</html>

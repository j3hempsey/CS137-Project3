<html>
    <head>
        <title>Confirmation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../stylesheets/home.css">
        <link rel="shortcut icon" href="../images/favicon.ico" type="../image/x-icon">
        <link rel="icon" href="../images/favicon.ico" type="../image/x-icon">
    </head>
    <body>
        <h1>Peter sells peppers</h1>
        <h2>Order Confirmation</h2>
<?php
    $confirm_id = htmlspecialchars($_GET["id"]);
    $servername = "sylvester-mccoy-v3.ics.uci.edu";
    $db="inf124grp13";
    $username = "inf124grp13";
    $password = "4a=eDuVu";

    try {
        $conn = new PDO("mysql:host=$servername; dbname=$db", $username, $password);
        // set the PDO error mode to exception
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //Output confirmation from SQL database
        if ($confirm_id){
            $stmt = $conn->query("SELECT * FROM orders WHERE ID=".$confirm_id);
            $stmt->execute();
            echo "<table>\n";
            while ( $row = $stmt->fetch(PDO::FETCH_ASSOC) ) {
                echo "<tr>";
                echo "<td> Order number::</td>";
                echo "<td>".$row['ID'];
                echo "</tr>";

                echo "<tr>";
                echo "<td> Name:</td>";
                echo "<td>".$row['first_name']." ".$row['last_name'];
                echo "</tr>";

                echo "<tr>";
                //phone
                echo "<td> Phone Number: </td>";
                echo "<td>".$row['phone_num']."<td>";
                echo "</tr>";

                echo "<tr>";
                //addr
                echo "<td>Address: </td>";
                echo "<td>".$row['street_addr']."<td>";
                echo "</tr><tr><td>Zip Code:</td>";
                echo "<td>".$row['zip']."</td>";
                echo "</tr><tr><td>State:</td>";
                echo "<td>".$row['state']."</td>";
                echo "</tr>";

                echo "<tr>";
                //ship speed
                echo "<td> Shipping Speed:</td>";
                echo "<td>";
                switch ($row['ship_speed']) {
                    case 1:
                        echo "One day";
                        break;
                    case 2:
                        echo "Two days";
                        break;
                    case 3:
                        echo "1-2 weeks";
                        break;
                    default:
                    //do nothing
                }
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td> Quantity:</td>";
                echo "<td>";
                echo $row['quantity'];
                $sql = "SELECT * FROM peppers WHERE ID=".$row['pepper_id'];
                $stmt = $conn->query($sql);
                $stmt->execute();
                echo "</tr><tr><td>Pepper:</td><td>";
                while ( $row = $stmt->fetch(PDO::FETCH_ASSOC) ) {
                    echo $row['pepper_name'];
                }
                echo "</td>";
                echo "</tr>";

            } echo "</table>\n";

        } else {
            ?>
            <div class='center-div'>
                ERROR: Invalid order ID number.
            </div>
            <?php
        }
    } catch(PDOException $e) {

        //echo "Connection failed: " . $e->getMessage();
    }

?>
        <br>
        <div class='center-div'>
            <b>Thanks for ordering! Hope to burn you again soon!</b>
        </div>
    </body>
</html>

<?php
    $servername = "sylvester-mccoy-v3.ics.uci.edu";
    $db="inf124grp13";
    $username = "inf124grp13";
    $password = "4a=eDuVu";
    $arg = $_REQUEST["q"];
    //Make SQL connection and get my answer.
    $conn = new PDO("mysql:host=$servername;dbname=$db", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    if($conn->connect_error) {
        echo "DATABASE CONNECTION FAILED.";
    }
    else
    {
        $query = "SELECT zip FROM `zipcodes` WHERE zip like '" . $arg . "%' LIMIT 0, 10";
        foreach($conn->query($query) as $column)
        {
            print "<option value=";
            print $column['zip'];
            print">\n";
        }
    }
?>

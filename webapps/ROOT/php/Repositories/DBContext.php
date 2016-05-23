<?php
    class DBContext {
        static function getSqlConnection() {
            try {
                $servername = "sylvester-mccoy-v3.ics.uci.edu";
                $db="inf124grp13";
                $username = "inf124grp13";
                $password = "4a=eDuVu";
                //Make SQL connection and get my answer.
                $conn = new PDO("mysql:host=$servername;dbname=$db", $username, $password);
                $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                if($conn->connect_error) {
                    echo "DATABASE CONNECTION FAILED.";
                }
                return $conn;
            } catch(PDOException $e) {
                    print "Connection failed: " . $e->getMessage();
            }
        }
    }
?>

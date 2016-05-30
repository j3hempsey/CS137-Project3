<?php
    require 'DBContext.php';
    class OrdersRepository {
     
        function createOrder($order) {
            $conn =  DBContext::getSqlConnection();
            $stmt = $conn->prepare("INSERT INTO orders (first_name, last_name, cc_number, street_addr, phone_num, zip, state, pepper_id, quantity, ship_speed) VALUES(?,?,?,?,?,?,?,?,?,?)"); 
            try{
                $conn->beginTransaction();
                $stmt->execute(array($order->first_name, $order->last_name, $order->credit_card, $order->address, $order->phone, $order->zip_code, $order->state, $order->pepper_id, $order->quantity, $order->ship));
                $orderId = $conn->lastInsertId();
                $conn->commit();
                
                return $orderId;
            } catch(PDOException $e) {
                $conn->rollback(); 
                echo "Error!: " . $e->getMessage() . "</br>"; 
            }
            
            //$sql = "INSERT INTO orders (first_name, last_name, cc_number, street_addr, phone_num, zip, state, pepper_id, quantity, ship_speed)" .
            //       "VALUES ('" . $order->first_name ."', '". $order->last_name ."', " . $order->credit_card .", '" . $order->address . "', " . $order->phone . ", " . $order->zip_code . ", '" . $order->state ."', " . $order->pepper_id .", " . $order->pepper_id  .", ". $order->ship.")";
   
           //$stmt = $conn->query($sql);
           //if($stmt) { return true; }
           
           return -1; 
        }
    }
?>
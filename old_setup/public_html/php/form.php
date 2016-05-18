<?php
require 'Models/Order.php';
require 'Repositories/OrdersRepository.php';

try {
    //Add to sql table
        $pepper_id = $_POST['pepper_id'];
        $first_name = $_POST['first_name'];
        $last_name = $_POST['last_name'];
        $credit_card = $_POST['credit_card'];
        $address = $_POST['address'];
        //Dashes are null terminated for the _POST, need to preprocess.
        $phone_val = $_POST['phone'];
        $phone = str_replace("-", "", $phone_val);
        $zip_code = $_POST['zip_code'];
        $state = $_POST['state'];
        $quantity = $_POST['quantity'];
        $ship = $_POST['ship'];
        
        $order = new Order;
        $order->pepper_id = $pepper_id;
        $order->first_name = $first_name;
        $order->last_name = $last_name;
        $order->credit_card = $credit_card;
        $order->address = $address;
        $order->phone = $phone;
        $order->zip_code = $zip_code;
        $order->state = $state;
        $order->quantity = $quantity;
        $order->ship = $ship;
        
        $ordersRepository = new OrdersRepository;
        $orderId = $ordersRepository->createOrder($order);
        echo $orderId;
} catch(PDOException $e) {
    echo "ERROR: Could not process order." . $e->getMessage();
}
?>

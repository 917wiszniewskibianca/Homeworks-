<?php
include ('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();

$id=$_POST['id'];
$category=$_POST['category'];
$type =$_POST['type'];
$price =$_POST['price'];
$hotel =$_POST["hotel"];

$query="";

if ($id!= "")
    $query = " UPDATE rooms SET category = '$category', type = '$type', price ='$price' , hotel = '$hotel' WHERE id= '$id'";



$result = mysqli_query($connect, $query);

if (!$result)
    echo 'Room can not be updated!';
else
    echo 'Room updated!';

CloseConnection($connect);

?>
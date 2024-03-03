<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();


$cnp = $_GET["cnp"];

$query="";

$query = "SELECT firstName,lastName FROM reservations WHERE cnp = '$cnp'";

$result = mysqli_query($connect, $query);

if(mysqli_num_rows($result)>0){
    $row = mysqli_fetch_array($result);
    $firstName = $row["firstName"];
    $lastName = $row["lastName"];
    $result = array("$firstName","$lastName");
    $myJSON = json_encode($result);
    echo $myJSON;
}
else
    echo 'Client with this cnp not found!';

CloseConnection($connect);

?>
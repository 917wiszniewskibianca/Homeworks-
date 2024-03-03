<?php
include ('/Applications/MAMP/htdocs/HotelBooking/Resources/connection.php');
$connect = OpenConnection();


$roomId = $_GET["roomId"];

$query="";

$query = "SELECT startDate,endDate FROM reservations WHERE roomId = '$roomId'";

$result = mysqli_query($connect, $query);

if(mysqli_num_rows($result)>0){
    while ($row = mysqli_fetch_array($result)){
        echo "<tr>";
        echo "<td>".$row['startDate']." <===> ".$row['endDate']."</th>";
        echo "</tr>";
    }

}
else
{
    echo "<tr>";
    echo "<td> -- </th>";
    echo "</tr>";
}

CloseConnection($connect);

?>
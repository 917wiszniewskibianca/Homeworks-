<?php
include('/Applications/MAMP/htdocs/6/Resources/connection.php');
$connect = OpenConnection();


$roomID = $_GET["roomID"];

$query="";

$query = "SELECT startDate,endDate FROM reservations WHERE roomID = '$roomID'";

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
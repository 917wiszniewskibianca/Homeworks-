<?php
include('/Applications/MAMP/htdocs/php_1/connection.php');
$connect = OpenConnection();

session_start();

$pg =  $_GET["pg"];
$offset = intval($pg) * 3 ;

$username = $_SESSION['username'];
$query1 = "SELECT id FROM Users WHERE username = '$username'";
$result1 = mysqli_query($connect, $query1);

if (mysqli_num_rows($result1) > 0) {
    $usernameId = mysqli_fetch_array($result1)['id'];
    $query = "SELECT * FROM Files WHERE userid = '$usernameId' LIMIT $offset, 3 ";

    $result = mysqli_query($connect, $query);

    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {
            echo "<tr>";
            echo "<td>" . $row['id'] . "</th>";
            echo "<td>" . $username . "</th>";
            echo "<td>" . $row['filename'] . "</th>";
            echo "<td>" . $row['filepath'] . "</th>";
            echo "<td>" . $row['size'] . "</th>";
            echo "</tr>";
        }
    }
}


CloseConnection($connect);


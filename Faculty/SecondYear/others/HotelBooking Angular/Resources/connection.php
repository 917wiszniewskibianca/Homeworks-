<?php
function OpenConnection()
{
    $host = "localhost";
    $username = "Bianca";
    $password = "muzicuta123";
    $database = "hotelbooking";

    $conn = mysqli_connect($host, $username, $password, $database);

    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    return $conn;
}

function CloseConnection(mysqli $con)
{
    $con->close();
}
?>

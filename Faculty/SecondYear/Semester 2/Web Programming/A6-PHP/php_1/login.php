<?php
include('/Applications/MAMP/htdocs/php_1/connection.php');
$connect = OpenConnection();
session_start(); // Start the session


if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    // Check if username is provided in the form
    if (isset($_POST['username']) && !empty($_POST['username'])) {
        $username = $_POST['username'];

        if (isset($_POST['password']) && !empty($_POST['password'])) {
            $password = $_POST['password'];
            $query1="Select password from Users where username= '$username'";
            $result1=mysqli_query($connect, $query1);
            if (mysqli_num_rows($result1) > 0) {

                $dbPassword =mysqli_fetch_array($result1)['password'];
                if ($dbPassword == $password){

                    $_SESSION["username"]=$username;
                    header('Location: homePage.html');
                    exit;
                }
            }
        }
        else {
            // Username not provided in the form, handle the case
            echo "Please enter a valid password.";
        }
    }else {
        // Username not provided in the form, handle the case
        echo "Please enter a valid username.";
    }
} else {
    // Handle cases where the form is not submitted
    echo "Invalid request.";
}

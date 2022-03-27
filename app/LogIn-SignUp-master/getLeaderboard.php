<?php
require "DataBase.php";
$db = new DataBase();


if (isset($_POST['classCode'])) {
    if ($db->dbConnect()) {
        if ($db->getLeaderboard($_POST['classCode'])) {
            echo $db->getLeaderboard($_POST['classCode']);
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>

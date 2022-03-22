<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['userID'])) {
    if ($db->dbConnect()) {
        if ($db->getTotalScore( $_POST['userID'])) {
            echo $db->getTotalScore( $_POST['userID']);
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>


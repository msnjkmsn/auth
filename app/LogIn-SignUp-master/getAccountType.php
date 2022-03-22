<?php
require "DataBase.php";
$db = new DataBase();


if (isset($_POST['username'])) {
    if ($db->dbConnect()) {
        if ($db->getAccountType($_POST['username'])) {
            echo $db->getAccountType($_POST['username']);
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>

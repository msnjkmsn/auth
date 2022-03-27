<?php
require "DataBase.php";
$db = new DataBase();


if (isset($_POST['userID']) && isset($_POST['score']) && isset($_POST['quizID'])) {
    if ($db->dbConnect()) {
        if ($db->getScore($_POST['userID'], $_POST['score'], $_POST['quizID'])) {
            echo quizID;
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>

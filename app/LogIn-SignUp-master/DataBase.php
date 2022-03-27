
<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, $data);
    }

    function logIn($table, $username, $password)
    {
	$username = $username;
	$password = $password;
	

      
	
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {

            $dbusername = $row['username'];

            $dbpassword = $row['password'];

		
            if ($dbusername == $username || $password == $dbpassword) {
	
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        
        $this->sql =
            "INSERT INTO " . $table . " (fullname, username, password, email) VALUES ('" . $fullname . "','" . $username . "','" . $password . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

	function getScore($userID, $score, $quizID){
	$userID = $userID;
	$score = $score;
	$quizID = $quizID;

	$this->sql = "Select * from userscore where userID = '" . $userID . "' && quizID = '" . $quizID . "'";
	$result = mysqli_query($this->connect, $this->sql);
	$row = mysqli_fetch_assoc($result);
	if(mysqli_num_rows($result) != 0){
		$this->sql = "Update userscore set score = '" . $score . "' where userID = '" . $userID . "' && quizID = '" . $quizID . "'";
		$result = mysqli_query($this->connect, $this->sql);
	}
	else{
		$this->sql = "INSERT INTO userscore VALUES ('" . $userID . "','" . $score . "', '" . $quizID . "')";
		$result = mysqli_query($this->connect, $this->sql);

	}
        return $quizID;
}

	

	function getID($username){
	
	$username = $username;


	$this->sql = "select id from account where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
	return $row['id'];
	}

    function getAccountType($username){

        $username = $username;


        $this->sql = "select accType from account where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        return $row['accType'];
    }
    function getClassCode($username){

        $username = $username;


        $this->sql = "SELECT classcode from account where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        return $row['classcode'];
    }
    function getLeaderboard($classCode){

        $classCode = $classCode;


        $this->sql = "select username from account where classcode = '" . $classCode . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        return $row['accType'];
    }


	function getTotalScore($userID){
	$userID = $userID;



	$this->sql = "Select Sum(score) as 'Tot' from userscore where userID = '" . $userID . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        return $row['Tot'];
}


}

?>

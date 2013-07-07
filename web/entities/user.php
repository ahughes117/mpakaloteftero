<?php

require_once(dirname(__FILE__) . '/../mysql.php');

class User {

    public $userID;
    public $email;
    public $password;
    public $name;
    public $surname;
    public $type;
    public $date_created;
    public $last_login;
    public $last_ip;
    public $date_modified;

}

/**
 * Authenticates a user and returns its full details
 * 
 * @global type $con
 * @param type $email
 * @param type $password
 * @return boolean
 * @throws Exception
 */
function authenticate($email, $password) {
    global $con;
    $query = "
        SELECT * 
        FROM user 
        WHERE Email = ? AND Password = ? ";

    try {
        $stmt = $con->prepare_statement($query);

        //checking if query well written
        if (!$stmt)
            throw new Exception();

        $stmt->bind_param("ss", $email, $password);
        $stmt->execute();

        $res = $stmt->get_result();
        if ($res->num_rows != 1)
            throw new Exception();

        while ($row = $res->fetch_array(MYSQLI_ASSOC))
            $user = row_to_user_object($row);

        //housekeeping and returning
        $stmt->close();
        return $user;
    } catch (Exception $x) {
        return false;
    }
}

/**
 * Fetches a user from the database by userID
 * 
 * @global type $con
 * @param type $userID
 * @return boolean
 * @throws Exception
 */
function fetch_user($userID) {
    global $con;
    $query = "
        SELECT * 
        FROM user 
        WHERE userID = ? ";

    try {
        $stmt = $con->prepare_statement($query);

        //checking if query well written
        if (!$stmt)
            throw new Exception();

        $stmt->bind_param("i", $userID);
        $stmt->execute();

        $res = $stmt->get_result();
        while ($row = $res->fetch_array(MYSQLI_ASSOC))
            $user = row_to_user_object($row);
        
        //housekeeping and returning
        $stmt->close();
        return $user;
    } catch (Exception $x) {
        return false;
    }
}

/**
 * Converts a user result set to a user object
 * 
 * @param type $row
 * @return \User
 */
function row_to_user_object($row) {
    $user = new User();

    $user->userID = $row['userID'];
    $user->email = $row['Email'];
    $user->password = $row['Password'];
    $user->name = $row['Name'];
    $user->surname = $row['Surname'];
    $user->type = $row['Type'];
    $user->date_created = $row['DateCreated'];
    $user->last_login = $row['LastLogin'];
    $user->last_ip = $row['LastIp'];
    $user->date_modified = $row['_dateModified'];

    return $user;
}

?>

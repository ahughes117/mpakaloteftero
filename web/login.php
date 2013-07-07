<?php

/*
 * This snippet contains the functionality for authenticating the user, storing
 * his details in the $_SESSION variable and redirecting him to the correct page
 * for both cases of failure and success.
 * 
 * Alex Hughes
 */

session_start();
require_once('entities/user.php');

$user = authenticate($_POST['email'], $_POST['pass']);

if(!$user)
    header('Location: index.php?error=1');
else {
    //if the authentication function returns a user object, then we proceed normally
    $_SESSION['userID'] = $user->userID;
    $_SESSION['email'] = $user->userID;
    $_SESSION['name'] = $user->userID;
    $_SESSION['surname'] = $user->userID;
    $_SESSION['type'] = $user->userID;
    
    ip_logger($user->userID, $_SERVER['REMOTE_ADDR']);
    header('Location: mpakaloteftero.php');
}

?>

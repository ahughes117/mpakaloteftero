<?php

/*
 * Destroys a session and redirects the user
 */

session_destroy();

header('Location: index.php');

?>

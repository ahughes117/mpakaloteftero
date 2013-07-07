<?php
session_start();

//preventing unauthorised access
if (!isset($_SESSION['userID']))
    header('Location: index.php');

//importing functionality
require_once('entities/user.php');
require_once('entities/expense.php');
require_once ('config.php');

$config = new Config();
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><?php echo "{$config->site_title} - {$_SESSION['name']} {$_SESSION['surname']}"; ?></title>
    </head>
    <body>
        <?php
        if (!isset($_GET['expenseID']))
            print_expenses();
        else
            print_expense($_GET['expenseID']);
        ?>
    </body>
</html>
<?php

function print_expenses() {
    $expenses = fetch_expenses("DESC", 0, 25);
    foreach ($expenses as $e)
        echo "<p>{$e->expenseID}";
}

function print_expense($expenseID) {
    $expense = fetch_expense($expenseID);
    echo "<p>{$expense->expenseID}</p>";
}
?>
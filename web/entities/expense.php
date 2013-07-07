<?php

require_once(dirname(__FILE__) . '/../mysql.php');

class Expense {

    public $expenseID;
    public $debiter;
    public $crediter;
    public $name;
    public $desc;
    public $price;
    public $paid;
    public $date_created;
    public $date_modified;

}

/**
 * Fetches expenses from the DB in a particular order
 * 
 * @global type $con
 * @param type $order
 * @param type $start
 * @param type $finish
 * @return boolean
 * @throws Exception
 */
function fetch_expenses($order, $start, $finish) {
    global $con;
    $expenses = array();

    $query = "
        SELECT * 
        FROM expense 
        ORDER BY DateCreated {$order} 
        LIMIT ? 
        OFFSET ? ";

    $limit = $finish - $start;
    $offset = $start;

    try {
        $stmt = $con->prepare_statement($query);

        //checking if query well written
        if (!$stmt)
            throw new Exception();

        $stmt->bind_param("ii", $limit, $offset);
        $stmt->execute();

        $res = $stmt->get_result();
        while ($row = $res->fetch_array(MYSQLI_ASSOC)) {
            $expenses [] = row_to_mysql($row);
        }
        //housekeeping and returning
        $stmt->close();
        return $expenses;
    } catch (Exception $x) {
        return false;
    }
}

/**
 * Marks an expense as paid in the database.
 * 
 * @global type $con
 * @param type $expenseID
 * @return boolean
 * @throws Exception
 */
function mark_paid_request($expenseID) {
    global $con;
    $query = "
        UPDATE expense 
        SET PaidRequest = 1 
        WHERE expenseID = ? ";

    try {
        $stmt = $con->prepare_statement($query);

        //checking if query well written
        if (!$stmt)
            throw new Exception();

        $stmt->bind_param("i", $expenseID);
        $stmt->execute();

        if ($stmt->affected_rows != 1)
            throw new Exception();
        else {
            $stmt->close();
            return true;
        }
    } catch (Exception $x) {
        return false;
    }
}

/**
 * Fetches a particular expense from the database by ID
 * 
 * @global type $con
 * @param type $expenseID
 * @return boolean
 * @throws Exception
 */
function fetch_expense($expenseID) {
    global $con;
    $query = "
        SELECT * 
        FROM expense 
        WHERE expenseID = ? ";

    try {
        $stmt = $con->prepare_statement($query);

        //checking if query well written
        if (!$stmt)
            throw new Exception();

        $stmt->bind_param("i", $expenseID);
        $stmt->execute();

        $res = $stmt->get_result();
        while ($row = $res->fetch_array(MYSQLI_ASSOC)) {
            $expense = row_to_expense_object($row);
        }
        if (!isset($expense))
            throw new Exception();
        else {
            //housekeeping and returning
            $stmt->close();
            return $expense;
        }
    } catch (Exception $x) {
        return false;
    }
}

/**
 * Converts a expense result set to an object
 * 
 * @param type $row
 * @return \Expense
 */
function row_to_expense_object($row) {
    $expense = new Expense();

    $expense->expenseID = $row['expenseID'];
    $expense->name = $row['Name'];
    $expense->desc = $row['Desc'];
    $expense->price = $row['Price'];
    $expense->paid = $row['Paid'];
    $expense->date_created = $row['DateCreated'];
    $expense->date_modified = $row['_dateModified'];

    return $expense;
}

?>

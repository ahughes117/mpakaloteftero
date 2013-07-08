
package entities;

import java.sql.Timestamp;

/**
 * Class Expense
 * The entity class for the expense
 * 
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class Expense {
    
    private int expenseID;
    private int debiterID;
    private int crediterID;
    private String name;
    private String desc;
    private double price;
    private boolean paid;
    private boolean paidRequest;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    /**
     * Constructor for inserting new expenses in the database
     * 
     * @param debiterID
     * @param crediterID
     * @param name
     * @param desc
     * @param price 
     */
    public Expense(int debiterID, int crediterID, String name, String desc, double price) {
        this.debiterID = debiterID;
        this.crediterID = crediterID;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    /**
     * Full Constructor for fetching expenses from the database
     * 
     * @param expenseID
     * @param debiterID
     * @param crediterID
     * @param name
     * @param desc
     * @param price
     * @param paid
     * @param paidRequest
     * @param dateCreated
     * @param dateModified 
     */
    public Expense(int expenseID, int debiterID, int crediterID, String name, String desc, double price, boolean paid, boolean paidRequest, Timestamp dateCreated, Timestamp dateModified) {
        this.expenseID = expenseID;
        this.debiterID = debiterID;
        this.crediterID = crediterID;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.paid = paid;
        this.paidRequest = paidRequest;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public int getDebiterID() {
        return debiterID;
    }

    public void setDebiterID(int debiterID) {
        this.debiterID = debiterID;
    }

    public int getCrediterID() {
        return crediterID;
    }

    public void setCrediterID(int crediterID) {
        this.crediterID = crediterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaidRequest() {
        return paidRequest;
    }

    public void setPaidRequest(boolean paidRequest) {
        this.paidRequest = paidRequest;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }
}

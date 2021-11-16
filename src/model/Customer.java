package model;

import java.time.LocalDateTime;

/**
 * The model class for all customers.
 * This is the customers class. It models the base of what every customer should be capable of having and doing.
 */
public class Customer {

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
    private int divisionID;
    private String country;



    /**
     * used for the main customers table
     * @param customerID customer id
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
    }



    /**
     * for customer modifying
     * @param customerID customer id
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone
     * @param divisionID the customer division id
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    /**
     * For updating the database.
     * @param customerID customer id
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone
     * @param createDate customer create date
     * @param createdBy customer was created by
     * @param lastUpdate customer info last update
     * @param lastUpdateBy the customer last updated by
     * @param divisionID the customer division id
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.divisionID = divisionID;

    }

public Customer(){};

    /**
     * The method returns the customer ID.
     * This method accesses the customerID and returns it.
     * @return the customer id.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * The method takes in the id and sets it.
     * This method takes in a integer and sets it to be a Customerid.
     * @param customerID id to set.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The method returns the customer name.
     * This method accesses the customer name and returns it.
     * @return the customer name.
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * The method takes in the name and sets it.
     * This method takes in a string and sets it to be a customerName.
     * @param customerName  to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * The method returns the customer Address.
     * This method accesses the customer Address and returns it.
     * @return the customer Address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * The method takes in the name and sets it.
     * This method takes in a string and sets it to be a customer Address.
     * @param address to set customer Address.
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * The method returns the customer postal code.
     * This method accesses the customer postal code and returns it.
     * @return postal code string.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * The method takes in the name and sets it.
     * This method takes in a string and sets it to be a customer PostalCode.
     * @param postalCode to set customer PostalCode.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * The method returns the customer phone number.
     * This method accesses the customer phone number and returns it.
     * @return phone string.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * The method takes in the phone and sets it.
     * This method takes in a string and sets it to be a customer phone number.
     * @param phone to set customer phone number.
     */
    public void setPhone(String phone) {
        phone = phone;
    }
    /**
     * The method returns the customer  Create Date.
     * This method accesses the Create Date and returns it.
     * @return Create Date.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * The method sets the customer createDate.
     * This method takes in a LocalDateTime and sets it as the create date.
     * @param  createDate the  time to set.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * The method returns who created the customer .
     * This method accesses who created the customer  and returns it.
     * @return CreatedBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * The method takes in the createdBy and sets it.
     * This method takes in a string and sets it to be the created by element.
     * @param createdBy  string to set.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * The method returns the customer  LastUpdate.
     * This method accesses the customer  LastUpdate and returns it.
     * @return LastUpdate.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * The method sets the customer LastUpdate.
     * This method takes in a LocalDateTime and sets it as the last update.
     * @param  lastUpdate the update time to set.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * The method returns the customer  LastUpdatedBy.
     * This method accesses the customer  LastUpdatedBy and returns it.
     * @return LastUpdatedBy.
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * The method sets the lastUpdateBy.
     * This method takes in a string and sets it to be a lastUpdateBy.
     * @param lastUpdateBy the id to set
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * The method returns the  customer DivisionID.
     * This method accesses the customer DivisionID and returns it.
     * @return DivisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * The method sets the divisionID.
     * This method takes in a integer and sets it to be a divisionID.
     * @param divisionID the id to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }



}

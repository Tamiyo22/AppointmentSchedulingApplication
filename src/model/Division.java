package model;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The model class for all divisions.
 * This is the customers class. It models the base of working with divisions.
 */
public class Division {



    private int divisionID;
    private String division;
    private int countryID;


    public Division(String division){
        this.division = division;
    }

    public Division(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }


    /**
     * The method returns the division id.
     * Returns division ID associated with the division.
     * @return divisionID Integer.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the divisionID of the division.
     * @param divisionID Integer.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Returns the division name.
     * @return String division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the division name of the division.
     * @param division String
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *  Returns the country ID associated with the division.
     * @return countryID Integer.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     *  Sets the country ID of the division.
     * @param countryID Integer
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     *  Overrides the default toString method. Returns the division name.
     * @return division String
     */
    @Override
    public String toString() {
        return division;
    }

}

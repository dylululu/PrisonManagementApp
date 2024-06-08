/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prisonmanagement.model;

import java.util.Date;

/**
 *
 * @author t460
 */
public class Criminal {

    private String criminalID;
    private String nameCriminal;
    private String dateOfBirth;
    private String gender;
    private String year;
    private String room;
    private String crime;
    private String crimeScale;

    public Criminal() {

    }

    public Criminal(String criminalID, String nameCriminal, String dateOfBirth, String gender, String year, String room, String crime, String crimeScale) {
        this.criminalID = criminalID;
        this.nameCriminal = nameCriminal;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.year = year;
        this.room = room;
        this.crime = crime;
        this.crimeScale = crimeScale;

    }

    Criminal(String criminalID, String nameCriminal, Date dateOfBirth, String gender, String year, String room, String crime,String crimeScale) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getcriminalID() {
        return criminalID;
    }

    public void setcriminalID(String criminalID) {
        this.criminalID = criminalID;
    }

    public String getcriminalName() {
        return nameCriminal;
    }

    public void setcriminalName(String nameCriminal) {
        this.nameCriminal = nameCriminal;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public void setCrimeScale(String crimeScale) {
        this.crimeScale = crimeScale;
    }

    public String getCrimeScale() {
        return crimeScale;
    }

    @Override
    public String toString() {
        return "Criminal{" + "criminalID=" + criminalID + ", nameCriminal=" + nameCriminal + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", year=" + year + ", room=" + room + ", crime=" + crime + ", crimeScale=" + crimeScale +'}';
    }

}

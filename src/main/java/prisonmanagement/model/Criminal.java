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
    private String ID;
    private String name;
    private String dateOfBitrh;
    private String gender;
    private String year;
    private String room;
    private String crime;
    
    public Criminal(){
        
    }

    public Criminal(String ID, String name, String dateOfBitrh, String gender, String year, String room, String crime) {
        this.ID = ID;
        this.name = name;
        this.dateOfBitrh = dateOfBitrh;
        this.gender = gender;
        this.year = year;
        this.room = room;
        this.crime = crime;
    }

    Criminal(String ID, String name, Date dateOfBirth, String gender, String year, String room, String crime) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBitrh() {
        return dateOfBitrh;
    }

    public void setDateOfBitrh(String dateOfBitrh) {
        this.dateOfBitrh = dateOfBitrh;
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

    @Override
    public String toString() {
        return "Criminal{" + "ID=" + ID + ", name=" + name + ", dateOfBitrh=" + dateOfBitrh + ", gender=" + gender + ", year=" + year +  ", room=" + room + ", crime=" + crime + '}';
    }

    
    
}

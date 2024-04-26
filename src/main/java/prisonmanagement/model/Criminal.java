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
    private Date dateOfBitrh;
    private String gender;
    private String year;
    private Date yearStart;
    private String room;
    private String crime;
    
    public Criminal(){
        
    }

    public Criminal(String ID, String name, Date dateOfBitrh, String gender, String year, Date yearStart, String room, String crime) {
        this.ID = ID;
        this.name = name;
        this.dateOfBitrh = dateOfBitrh;
        this.gender = gender;
        this.year = year;
        this.yearStart = yearStart;
        this.room = room;
        this.crime = crime;
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

    public Date getDateOfBitrh() {
        return dateOfBitrh;
    }

    public void setDateOfBitrh(Date dateOfBitrh) {
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

    public Date getYearStart() {
        return yearStart;
    }

    public void setYearStart(Date yearStart) {
        this.yearStart = yearStart;
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
        return "Criminal{" + "ID=" + ID + ", name=" + name + ", dateOfBitrh=" + dateOfBitrh + ", gender=" + gender + ", year=" + year + ", yearStart=" + yearStart + ", room=" + room + ", crime=" + crime + '}';
    }

    
}

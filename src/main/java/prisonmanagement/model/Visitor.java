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
public class Visitor {
    private String visitorID;
    private String criminalID;
    private String dateOfVisit;
    private String time;
    private String amountOfTime;
    private String location;
    private String nameVisitor;
    private String relationship;
    
    public Visitor(){
        
    }

    public Visitor( String visitorID, String criminalID, String dateOfVisit, String time, String amountOfTime, String location, String nameVisitor, String relationship) {
        this.visitorID = visitorID;
        this.criminalID = criminalID;
        this.dateOfVisit = dateOfVisit;
        this.time = time;
        this.amountOfTime = amountOfTime;
        this.location = location;
        this.nameVisitor = nameVisitor;
        this.relationship = relationship;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(String visitorID) {
        this.visitorID = visitorID;
    }

    public String getCriminalID() {
        return criminalID;
    }

    public void setCriminalID(String criminalID) {
        this.criminalID = criminalID;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmountOfTime() {
        return amountOfTime;
    }

    public void setAmountOfTime(String amountOfTime) {
        this.amountOfTime = amountOfTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNameVisitor() {
        return nameVisitor;
    }

    public void setNameVisitor(String nameVisitor) {
        this.nameVisitor = nameVisitor;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Visitor{");
        sb.append(", visitorID=").append(visitorID);
        sb.append(", criminalID=").append(criminalID);
        sb.append(", dateOfVisit=").append(dateOfVisit);
        sb.append(", time=").append(time);
        sb.append(", amountOfTime=").append(amountOfTime);
        sb.append(", location=").append(location);
        sb.append(", nameVisitor=").append(nameVisitor);
        sb.append(", relationship=").append(relationship);
        sb.append('}');
        return sb.toString();
    }

    
}

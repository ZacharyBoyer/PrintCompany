/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Zach
 */
public class Location {

    int id, distrCap;
    String locationName;

    public Location() {
    }

    public Location(int Id) {
        id = Id;
    }

    public Location(int ID, String Name, int CAP) {
        id = ID;
        locationName = Name;
        distrCap = CAP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrCap() {
        return distrCap;
    }

    public void setDistrCap(int distrCap) {
        this.distrCap = distrCap;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

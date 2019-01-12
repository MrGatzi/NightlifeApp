package com.example.nightlife.nightlife;

public class Event extends Location {

    private int eventID;
    private String date;

    public Event(int eventID, String name, String type, double locLat, double locLong, String date, double priceIndex, double entryFee, int age, String longDescription, String shortDescription, String addressCity, String addressPLZ, String addressStreet, String addressNr, double distance) {
        super(name, type, locLat, locLong, priceIndex, entryFee, age, longDescription, shortDescription, addressCity, addressPLZ, addressStreet, addressNr, distance);
        this.eventID = eventID;
        this.date = date;
    }

    public int getEventID() {
        return eventID;
    }

    public String getDate() {
        return date;
    }
}

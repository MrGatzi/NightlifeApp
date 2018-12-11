package com.example.nightlife.nightlife;

public class Event extends Location {

    private int eventID;
    private String date;

    public Event(int eventID, String name, String type, String locLat, String locLong, String date, float priceIndex, float entryFee, int age, String longDescription, String shortDescription, String addressCity, int addressPLZ, String addressStreet, int addressNr) {
        super(name, type, locLat, locLong, priceIndex, entryFee, age, longDescription, shortDescription, addressCity, addressPLZ, addressStreet, addressNr);
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

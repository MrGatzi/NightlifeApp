package com.example.nightlife.nightlife;

public class Venue extends Location {

    private int venueID;
    private String venueEventName;

    public Venue(int venueID, String name, String venueEventName, String type, String locLat, String locLong, float priceIndex, float entryFee, int age, String longDescription, String shortDescription, String addressCity, int addressPLZ, String addressStreet, int addressNr, OpeningHours openingHours)  {
        super(name, type, locLat, locLong, priceIndex, entryFee, age, longDescription, shortDescription, addressCity, addressPLZ, addressStreet, addressNr);
        this.venueID = venueID;
        this.venueEventName = venueEventName;
    }

    public int getVenueID() {
        return venueID;
    }

    public String getVenueEventName() {
        return venueEventName;
    }
}

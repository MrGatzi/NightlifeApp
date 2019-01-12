package com.example.nightlife.nightlife;

public class Venue extends Location {

    private int venueID;
    private String venueEventName;
    private OpeningHours openingHours;

    public Venue(int venueID, String name, String venueEventName, String type, double locLat, double locLong, double priceIndex, double entryFee, int age, String longDescription, String shortDescription, String addressCity, String addressPLZ, String addressStreet, String addressNr, double distance, OpeningHours openingHours)  {
        super(name, type, locLat, locLong, priceIndex, entryFee, age, longDescription, shortDescription, addressCity, addressPLZ, addressStreet, addressNr, distance);
        this.venueID = venueID;
        this.venueEventName = venueEventName;
        this.openingHours = openingHours;
    }

    public int getVenueID() {
        return venueID;
    }

    public String getVenueEventName() {
        return venueEventName;
    }

    public OpeningHours getOpeningHours() { return openingHours; }
}

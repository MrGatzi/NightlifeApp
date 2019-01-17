package com.example.nightlife.nightlife;

public class Venue extends Location {

    private int venueID;
    private VenueEvent[] venueEvents;
    private OpeningHours[] openingHours;

    public Venue(int venueID, String name, String type, double locLat, double locLong, double priceIndex, double entryFee, int age, String longDescription, String shortDescription, String addressCity, String addressPLZ, String addressStreet, String addressNr, double distance, VenueEvent[] venueEvent, OpeningHours[] openingHours)  {
        super(name, type, locLat, locLong, priceIndex, entryFee, age, longDescription, shortDescription, addressCity, addressPLZ, addressStreet, addressNr, distance);
        this.venueID = venueID;
        this.venueEvents = venueEvent;
        this.openingHours = openingHours;
    }

    public int getVenueID() { return venueID; }

    public VenueEvent[] getVenueEvents() { return venueEvents; }

    public OpeningHours[] getOpeningHours() { return openingHours; }

    public VenueEvent getVenueEventByWeekday(int weekday) {
        for (int i = 0; i < venueEvents.length; i++){
            if (venueEvents[i].getWeekday() == weekday) {
                return venueEvents[i];
            }
        }
        return null;
    }

    public String getAllVenueEvents() {
        StringBuilder venueEventsString = new StringBuilder();
        for (int i = 0; i < venueEvents.length; i++) {
            venueEventsString.append(venueEvents[i].toString());
        }
        return venueEventsString.toString();
    }

    public String getOpeningHoursByWeekday(int weekday) {
        for (int i = 0; i < openingHours.length; i++){
            if (openingHours[i].getWeekday() == weekday) {
                return openingHours[i].toString();
            }
        }
        return "Keine Öffnungszeiten gefunden.";
    }

    public String getAllOpeningHours() {
        StringBuilder ohs = new StringBuilder();
        for (int i = 0; i < openingHours.length; i++) {
            ohs.append(openingHours[i].toString());
        }
        return ohs.toString();
    }


}

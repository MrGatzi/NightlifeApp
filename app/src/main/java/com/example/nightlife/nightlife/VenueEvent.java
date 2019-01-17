package com.example.nightlife.nightlife;

public class VenueEvent {

    private int weekday;
    private String venueEventName;
    private String longDescription;
    private String shortDescription;

    public VenueEvent(int weekday, String venueEventName, String longDescription, String shortDescription) {
        this.weekday = weekday;
        this.venueEventName = venueEventName;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getVenueEventName() {
        return venueEventName;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {

        String weekdayDE;
        switch (weekday) {
            case 1:
                weekdayDE = "Montag";
                break;
            case 2:
                weekdayDE = "Dienstag";
                break;
            case 3:
                weekdayDE = "Mittwoch";
                break;
            case 4:
                weekdayDE = "Donnerstag";
                break;
            case 5:
                weekdayDE = "Freitag";
                break;
            case 6:
                weekdayDE = "Samstag";
                break;
            case 7:
                weekdayDE = "Sonntag";
                break;
            default:
                weekdayDE = "Ungültiger Wochentag";
                break;
        }

        return weekdayDE + ": " + venueEventName;
    }
}

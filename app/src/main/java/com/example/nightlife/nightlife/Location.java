package com.example.nightlife.nightlife;

public class Location {

    private String name;
    private String type;
    private double locLat;
    private double locLong;
    private double priceIndex;
    private double entryFee;
    private int age;
    private String longDescription;
    private String shortDescription;
    private String addressCity;
    private String addressPLZ;
    private String addressStreet;
    private String addressNr;
    private double distance;

    public Location(String name, String type, double locLat, double locLong, double priceIndex, double entryFee, int age, String longDescription, String shortDescription, String addressCity, String addressPLZ, String addressStreet, String addressNr, double distance) {
        this.name = name;
        this.type = type;
        this.locLat = locLat;
        this.locLong = locLong;
        this.priceIndex = priceIndex;
        this.entryFee = entryFee;
        this.age = age;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.addressCity = addressCity;
        this.addressPLZ = addressPLZ;
        this.addressStreet = addressStreet;
        this.addressNr = addressNr;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLocLat() {
        return locLat;
    }

    public double getLocLong() {
        return locLong;
    }

    public double getPriceIndex() {
        return priceIndex;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public int getAge() {
        return age;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressPLZ() {
        return addressPLZ;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressNr() {
        return addressNr;
    }

    public double getDistance() { return distance; }

    public String getPriceIndexSymbol() {
        String priceIndexString = "";
        if (priceIndex > 7){
            priceIndexString = "€€€";
        } else if (priceIndex > 5) {
            priceIndexString = "€€";
        } else {
            priceIndexString = "€";
        }
        return priceIndexString;
    }

}
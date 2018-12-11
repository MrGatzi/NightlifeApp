package com.example.nightlife.nightlife;

public class Location {

    private String name;
    private String type;
    private String locLat;
    private String locLong;
    private float priceIndex;
    private float entryFee;
    private int age;
    private String longDescription;
    private String shortDescription;
    private String addressCity;
    private int addressPLZ;
    private String addressStreet;
    private int addressNr;

    public Location(String name, String type, String locLat, String locLong, float priceIndex, float entryFee, int age, String longDescription, String shortDescription, String addressCity, int addressPLZ, String addressStreet, int addressNr) {
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
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocLat() {
        return locLat;
    }

    public String getLocLong() {
        return locLong;
    }

    public float getPriceIndex() {
        return priceIndex;
    }

    public float getEntryFee() {
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

    public int getAddressPLZ() {
        return addressPLZ;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public int getAddressNr() {
        return addressNr;
    }

}
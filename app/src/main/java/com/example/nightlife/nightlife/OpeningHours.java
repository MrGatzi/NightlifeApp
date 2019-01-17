package com.example.nightlife.nightlife;

import java.util.Date;

public class OpeningHours {

    private int weekday;
    private String dopen;
    private String dclose;

    public OpeningHours(int weekday, String dopen, String dclose) {
        this.weekday = weekday;
        this.dopen = dopen;
        this.dclose = dclose;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getDopen() {
        return dopen;
    }

    public String getDclose() {
        return dclose;
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

        return weekdayDE + ": " + dopen + "-" + dclose;
    }
}

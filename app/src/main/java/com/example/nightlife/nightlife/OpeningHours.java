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
            case 0:
                weekdayDE = "Montag";
                break;
            case 1:
                weekdayDE = "Dienstag";
                break;
            case 2:
                weekdayDE = "Mittwoch";
                break;
            case 3:
                weekdayDE = "Donnerstag";
                break;
            case 4:
                weekdayDE = "Freitag";
                break;
            case 5:
                weekdayDE = "Samstag";
                break;
            case 6:
                weekdayDE = "Sonntag";
                break;
            default:
                weekdayDE = "Ungültiger Wochentag";
                break;
        }

        return weekdayDE + ": " + dopen.substring(0, 5) + "-" + dclose.substring(0, 5);
    }
}

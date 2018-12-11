package com.example.nightlife.nightlife;

import java.util.Date;

public class OpeningHours {

    private int Weekday;
    // private Date Dopen;
    private String Dopen;
    // private Date Dclose;
    private String Dclose;

    public OpeningHours(int weekday, String dopen, String dclose) {
        Weekday = weekday;
        Dopen = dopen;
        Dclose = dclose;
    }

    public int getWeekday() {
        return Weekday;
    }

    public String getDopen() {
        return Dopen;
    }

    public String getDclose() {
        return Dclose;
    }
}

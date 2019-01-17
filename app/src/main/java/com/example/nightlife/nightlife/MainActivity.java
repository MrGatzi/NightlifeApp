package com.example.nightlife.nightlife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private ArrayList<Location> locations = new ArrayList<Location>();

    // global date (default: today) with calendar
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    // weekday (in English: "Monday", …)
    int dayOfWeek = getWeekdayInt(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        ImageView search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Search clicked!", Toast.LENGTH_SHORT).show();
            }
        });


        /*
         * DUMMY OBJECT START -----------------------------------------------------------------------

        List<Location> venues = new ArrayList<Location>();
        List<Location> events = new ArrayList<Location>();
        List<List<Location>> locationTest = new ArrayList<List<Location>>();

        venues.add(
                new Venue(
                        5,
                        "MANGLO",
                        "Cocktailnight",
                        "Jazz",
                        "-86.914175",
                        "-89.130904",
                        2,
                        28,
                        27,
                        "Nulla cillum occaecat magna in consectetur eiusmod incididunt. Do laboris dolore commodo mollit ipsum consectetur. Veniam velit qui esse occaecat exercitation esse cillum occaecat veniam. Nulla cillum ex sit aliqua ad aliquip commodo proident. Ad commodo ex consequat laboris esse culpa enim. Lorem excepteur sint tempor culpa ut ullamco eu Lorem duis voluptate velit pariatur.",
                        "amet id sint ea aliquip cupidatat occaecat ea anim fugiat irure eu proident ullamco mollit",
                        "Singer",
                        1628,
                        "Seigel Street",
                        2,
                        new OpeningHours(
                                1,
                                "Tue Apr 29 2014 23:43:50 GMT+0000 (UTC)",
                                "Wed Mar 05 2014 16:42:44 GMT+0000 (UTC)"
                        )
                )
        );

        events.add(

                new Event(
                        8,
                        "SULTRAX",
                        "Chill",
                        "-63.619657",
                        "-48.589644",
                        "Fri Oct 12 2018 01:10:34 GMT+0000 (UTC)",
                        4,
                        33,
                        24,
                        "Eiusmod veniam cillum cillum culpa aliquip laboris fugiat sunt excepteur elit duis excepteur minim. Sunt do non commodo ullamco nisi voluptate esse esse commodo id quis est qui. Pariatur consectetur reprehenderit non nostrud incididunt laboris labore sunt aliqua.",
                        "dolor consequat mollit voluptate excepteur consequat ut adipisicing fugiat culpa id do quis minim commodo",
                        "Sexton",
                        1415,
                        "Kossuth Place",
                        21
                )
        );

        locationTest.add(venues);
        locationTest.add(events);

        // test: get long and lat of all locations
        for (int i=0; i<locationTest.size(); i++){
            for (int j=0; j<locationTest.get(i).size(); j++){
                double locLat = Double.parseDouble(locationTest.get(i).get(j).getLocLat());
                double locLong = Double.parseDouble(locationTest.get(i).get(j).getLocLong());

                // do fancy map shit with the coordinates

            }
        }

        // merge listlist to one list
        ArrayList<Location> mergedList = new ArrayList<Location>(locationTest.get(0));
        mergedList.addAll(locationTest.get(1));

        * DUMMY OBJECT END ------------------------------------------------------------------------
        */


        queue = Volley.newRequestQueue(this);
        jsonParse();

        final ListView previewList = (ListView)findViewById(R.id.list_previewList);
        PreviewListAdapter previewListAdapter = new PreviewListAdapter(getApplicationContext(), R.layout.preview_venue, locations, dayOfWeek);
        previewList.setAdapter(previewListAdapter);

    }

    private void jsonParse() {

        String url ="http://nightlifeapi.projekte.fh-hagenberg.at/laravel/public/api/location/0/0";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // get venues
                            JSONArray venueArray = response.getJSONArray("Venues");
                            for (int i = 0; i < venueArray.length(); i++) {
                                JSONObject venue = venueArray.getJSONObject(i);

                                // get venueEvents
                                JSONArray venueEventsArray = venue.getJSONArray("Week");
                                VenueEvent[] venueEvents = new VenueEvent[venueEventsArray.length()];
                                if (venueEventsArray.length() != 0) {
                                    for (int h = 0; h < venueEventsArray.length(); h++) {
                                        JSONObject venueEventsObject = venueEventsArray.getJSONObject(h);

                                        VenueEvent tempVE = new VenueEvent( venueEventsObject.getInt("WeekDay"),
                                                                            venueEventsObject.getString("VenueEventName"),
                                                                            venueEventsObject.getString("LongDescription"),
                                                                            venueEventsObject.getString("ShortDescription"));
                                        venueEvents[h] = tempVE;
                                    }
                                }

                                // get openingHours
                                JSONArray openingHoursArray = venue.getJSONArray("OpeningHours");
                                OpeningHours[] openingHours = new OpeningHours[openingHoursArray.length()];
                                if (openingHoursArray.length() != 0) {
                                    for (int h = 0; h < openingHoursArray.length(); h++) {
                                        JSONObject openingHoursObject = openingHoursArray.getJSONObject(h);
                                        OpeningHours tempOH = new OpeningHours( openingHoursObject.getInt("WeekDay"),
                                                                                openingHoursObject.getString("DOpen"),
                                                                                openingHoursObject.getString("DClose"));
                                        openingHours[h] = tempOH;
                                    }
                                }

                                locations.add(new Venue(
                                                venue.getInt("VenueID"),
                                                venue.getString("Name"),
                                                venue.getString("Type"),
                                                venue.getDouble("LocLat"),
                                                venue.getDouble("LocLong"),
                                                venue.getDouble("PriceIndex"),
                                                venue.getDouble("EntryFee"),
                                                venue.getInt("Age"),
                                                venue.getString("LongDescription"),
                                                venue.getString("ShortDescription"),
                                                venue.getString("AddressCity"),
                                                venue.getString("AddressPLZ"),
                                                venue.getString("AddressStreet"),
                                                venue.getString("AddressNr"),
                                                venue.getDouble("distance"),
                                                venueEvents,
                                                openingHours
                                        )
                                );
                            }

                            // get events
                            JSONArray eventArray = response.getJSONArray("Events");
                            for (int i = 0; i < eventArray.length(); i++) {
                                JSONObject event = eventArray.getJSONObject(i);

                                locations.add(new Event(
                                                event.getInt("EventID"),
                                                event.getString("Name"),
                                                event.getString("Type"),
                                                event.getDouble("LocLat"),
                                                event.getDouble("LocLong"),
                                                event.getString("Date"),
                                                event.getDouble("PriceIndex"),
                                                event.getDouble("EntryFee"),
                                                event.getInt("Age"),
                                                event.getString("LongDescription"),
                                                event.getString("ShortDescription"),
                                                event.getString("AddressCity"),
                                                event.getString("AddressPLZ"),
                                                event.getString("AddressStreet"),
                                                event.getString("AddressNr"),
                                                event.getDouble("distance")
                                        )
                                );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public int getWeekdayInt(String weekday){
        switch (weekday) {
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
            case "Saturday":
                return 5;
            case "Sunday":
                return 6;
            default:
                return -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.contact:
                Toast.makeText(this, "Contact clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.impressum:
                Toast.makeText(this, "Impressum clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filter:
                Toast.makeText(this, "Filter clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calendar:

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
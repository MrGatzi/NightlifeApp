package com.example.nightlife.nightlife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private ArrayList<Location> locations = new ArrayList<Location>();

    private boolean[] filter;

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

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.getMenu().getItem(0).setChecked(true);
        bottomNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_list:
                        break;
                    case R.id.navigation_map:
                        Intent intent = new Intent(MainActivity.this, MapActivity.class);
                        intent.putExtra("year", year);
                        intent.putExtra("month", month);
                        intent.putExtra("dayOfMonth", dayOfMonth);
                        startActivity(intent);
                        break;
                }
            }
        });

        queue = Volley.newRequestQueue(this);
        jsonParse();

        final ListView previewList = (ListView)findViewById(R.id.list_previewList);
        PreviewListAdapter previewListAdapter = new PreviewListAdapter(getApplicationContext(), R.layout.preview_venue, locations, dayOfWeek);
        previewList.setAdapter(previewListAdapter);

    }

    private void jsonParse() {

        // String url ="http://nightlifeapi.projekte.fh-hagenberg.at/laravel/public/api/location/0/0";
        String url ="http://nightlifeapi.projekte.fh-hagenberg.at/laravel/public/api/location/48.373027/14.516546";

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
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(intent);
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
package com.example.nightlife.nightlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemActivity extends AppCompatActivity {

    boolean filter_active;
    boolean filter1_disco;
    boolean filter1_bar;
    boolean filter1_event;
    boolean filter2_poor;
    boolean filter2_medium;
    boolean filter2_rich;
    boolean filter3_near;
    boolean filter3_medium;
    boolean filter3_far;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);

        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar_listItem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("name"));

        // get each view of the location layout
        TextView locationName = findViewById(R.id.location_name);
        TextView eventDate = findViewById(R.id.location_eventDate);
        TextView venueEventName = findViewById(R.id.location_venueEventName);
        TextView locationLongDescription = findViewById(R.id.location_content_longDescription);
        TextView locationOpeningHours = findViewById(R.id.location_content_openingHours);
        TextView venueEvents = findViewById(R.id.location_content_venueEvents);
        TextView locationAddress = findViewById(R.id.location_content_address);
        TextView locationDistancePriceIndex = findViewById(R.id.location_content_facts_km_price);
        TextView locationType = findViewById(R.id.location_content_facts_type);
        TextView locationAge = findViewById(R.id.location_content_facts_age);
        TextView locationEntrance = findViewById(R.id.location_content_facts_entrance);

        // set common values
        locationName.setText(intent.getStringExtra("name"));
        eventDate.setText(intent.getStringExtra("eventDate"));
        venueEvents.setText(intent.getStringExtra("venueEvents"));
        venueEventName.setText(intent.getStringExtra("venueEventName"));
        locationLongDescription.setText(intent.getStringExtra("longDescription"));
        locationOpeningHours.setText(intent.getStringExtra("openingHours"));
        locationAddress.setText(intent.getStringExtra("address"));
        locationDistancePriceIndex.setText(String.valueOf(intent.getDoubleExtra("distance", 0.00)) + "km   " + intent.getStringExtra("priceIndex"));
        locationType.setText(intent.getStringExtra("type"));
        locationAge.setText("Alter: " + String.valueOf(intent.getIntExtra("age", 0)));
        locationEntrance.setText("Eintritt: " + String.valueOf(intent.getDoubleExtra("entrance", 0.00)) + "0€");

        if (intent.getStringExtra("openingHours").equals("")) {
            ImageView icon = findViewById(R.id.location_content_openingHoursIcon);
            ((ViewGroup) icon.getParent()).removeView(icon);
            ((ViewGroup) locationOpeningHours.getParent()).removeView(locationOpeningHours);
        }

        if (intent.getStringExtra("venueEvents").equals("")) {
            ((ViewGroup) venueEvents.getParent()).removeView(venueEvents);
        }

        filter1_disco = intent.getBooleanExtra("state_filter1_disco", false);
        filter1_bar = intent.getBooleanExtra("state_filter1_bar", false);
        filter1_event = intent.getBooleanExtra("state_filter1_event", false);
        filter2_poor = intent.getBooleanExtra("state_filter2_poor", false);
        filter2_medium = intent.getBooleanExtra("state_filter2_medium", false);
        filter2_rich = intent.getBooleanExtra("state_filter2_rich", false);
        filter3_near = intent.getBooleanExtra("state_filter3_near", false);
        filter3_medium = intent.getBooleanExtra("state_filter3_medium", false);
        filter3_far = intent.getBooleanExtra("state_filter3_far", false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent intent = new Intent(ListItemActivity.this, MainActivity.class);
            intent.putExtra("state_filter1_disco", filter1_disco);
            intent.putExtra("state_filter1_bar", filter1_bar);
            intent.putExtra("state_filter1_event", filter1_event);
            intent.putExtra("state_filter2_poor", filter2_poor);
            intent.putExtra("state_filter2_medium", filter2_medium);
            intent.putExtra("state_filter2_rich", filter2_rich);
            intent.putExtra("state_filter3_near", filter3_near);
            intent.putExtra("state_filter3_medium", filter3_medium);
            intent.putExtra("state_filter3_far", filter3_far);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

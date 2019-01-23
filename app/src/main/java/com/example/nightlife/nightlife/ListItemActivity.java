package com.example.nightlife.nightlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ListItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);

        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar_listItem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get each view of the location layout
        TextView locationName = findViewById(R.id.location_name);
        TextView eventDate = findViewById(R.id.location_eventDate);
        TextView venueEventName = findViewById(R.id.location_venueEventName);
        TextView locationLongDescription = findViewById(R.id.location_content_longDescription);
        TextView locationOpeningHours = findViewById(R.id.location_content_openingHours);
        TextView locationAddress = findViewById(R.id.location_content_address);
        TextView locationDistance = findViewById(R.id.location_content_facts_km);
        TextView locationPriceIndex = findViewById(R.id.location_content_facts_priceIndex);
        TextView locationType = findViewById(R.id.location_content_facts_type);
        TextView locationAge = findViewById(R.id.location_content_facts_age);
        TextView locationEntrance = findViewById(R.id.location_content_facts_entrance);

        // set values
        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        locationName.setText(intent.getStringExtra("name"));
        eventDate.setText(intent.getStringExtra("eventDate"));
        venueEventName.setText(intent.getStringExtra("venueEventName"));
        locationLongDescription.setText(intent.getStringExtra("longDescription"));
        locationOpeningHours.setText(intent.getStringExtra("openingHours"));
        locationAddress.setText(intent.getStringExtra("address"));
        locationDistance.setText(String.valueOf(intent.getDoubleExtra("distance", 0.00)) + "km");
        locationPriceIndex.setText(intent.getStringExtra("priceIndex"));
        locationType.setText(intent.getStringExtra("type"));
        locationAge.setText("Alter: " + String.valueOf(intent.getIntExtra("age", 0)));
        locationEntrance.setText("Eintritt: " + String.valueOf(intent.getDoubleExtra("entrance", 0.00)) + "0€");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

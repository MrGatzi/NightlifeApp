package com.example.nightlife.nightlife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreviewListAdapter extends ArrayAdapter<Location> {

    Context context;
    int resource;
    ArrayList<Location> locations = null;

    public PreviewListAdapter(@NonNull Context context, int resource, ArrayList<Location> locations) {
        super(context, resource, locations);
        this.context = context;
        this.resource = resource;
        this.locations = locations;
    }

    // gets view (row layout) for each location preview item
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (locations.get(position) instanceof Venue){
            // get current location
            Venue currentLocation = (Venue)locations.get(position);

            // instantiate only for the first time
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.preview_venue, parent, false);
            }

            // get each view of the venue layout
            ImageView venueImage = (ImageView)convertView.findViewById(R.id.preview_venue_venueImage);
            TextView venueEventName = (TextView)convertView.findViewById(R.id.preview_venue_venueEventName);
            TextView venueName = (TextView)convertView.findViewById(R.id.preview_venue_venueName);
            TextView venueShortDescription = (TextView)convertView.findViewById(R.id.preview_venue_venueShortDescription);
            TextView venueKm = (TextView)convertView.findViewById(R.id.preview_venue_venueKm);
            TextView venuePriceIndex = (TextView)convertView.findViewById(R.id.preview_venue_venuePriceIndex);

            // set values with the current location data
            venueImage.setImageResource(R.drawable.pub);
            venueEventName.setText(currentLocation.getVenueEventName());
            venueName.setText(currentLocation.getName());
            venueShortDescription.setText(currentLocation.getShortDescription());
            //venueKm.setText();
            venuePriceIndex.setText(String.valueOf(currentLocation.getPriceIndex()));

        } else {
            // get current location
            Event currentLocation = (Event)locations.get(position);

            // instantiate only for the first time
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.preview_event, parent, false);
            }

            // get each view of the event layout
            ImageView eventImage = (ImageView)convertView.findViewById(R.id.preview_event_eventImage);
            TextView eventName = (TextView)convertView.findViewById(R.id.preview_event_eventName);
            TextView eventDate = (TextView)convertView.findViewById(R.id.preview_event_eventDate);
            TextView eventShortDescription = (TextView)convertView.findViewById(R.id.preview_event_eventShortDescription);
            TextView eventKm = (TextView)convertView.findViewById(R.id.preview_event_eventKm);
            TextView eventPriceIndex = (TextView)convertView.findViewById(R.id.preview_event_eventPriceIndex);

            // set values with the current location data
            //eventImage.setImageResource();
            eventName.setText(currentLocation.getName());
            //eventDate.setText(currentLocation.getDate());
            eventShortDescription.setText(currentLocation.getShortDescription());
            //eventKm.setText();
            eventPriceIndex.setText(String.valueOf(currentLocation.getPriceIndex()));
        }

        return convertView;
    }
}



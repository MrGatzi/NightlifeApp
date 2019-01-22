package com.example.nightlife.nightlife;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PreviewListAdapter extends ArrayAdapter<Location> {

    Context context;
    int resource;

    public static final int VENUE = 0;
    public static final int EVENT = 1;

    private ArrayList<Location> locations = null;
    int dayOfWeek;

    public PreviewListAdapter(@NonNull Context context, int resource, ArrayList<Location> locations, int dayOfWeek) {
        super(context, resource, locations);
        this.context = context;
        this.resource = resource;
        this.locations = locations;
        this.dayOfWeek = dayOfWeek;
    }

    // gets view (row layout) for each location preview item
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // instantiate only for the first time
        if (convertView == null) {
            if (getItemViewType(position) == VENUE) {
                convertView = LayoutInflater.from(context).inflate(R.layout.preview_venue, parent, false);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.preview_event, parent, false);
            }
        }

        if (getItemViewType(position) == VENUE) {
            // get current location
            Venue currentLocation = (Venue)locations.get(position);

            // get each view of the venue layout
            ImageView venueImage = (ImageView)convertView.findViewById(R.id.preview_venue_venueImage);
            TextView venueEventName = (TextView)convertView.findViewById(R.id.preview_venue_venueEventName);
            TextView venueName = (TextView)convertView.findViewById(R.id.preview_venue_venueName);
            TextView venueShortDescription = (TextView)convertView.findViewById(R.id.preview_venue_venueShortDescription);
            TextView venueKm = (TextView)convertView.findViewById(R.id.preview_venue_venueKm);
            TextView venuePriceIndex = (TextView)convertView.findViewById(R.id.preview_venue_venuePriceIndex);

            // set values with the current location data
            venueImage.setImageResource(R.drawable.pub);
            venueName.setText(currentLocation.getName());
            venueShortDescription.setText(currentLocation.getShortDescription());
            venueKm.setText(String.valueOf(currentLocation.getDistanceShort()) + "km");
            venuePriceIndex.setText(currentLocation.getPriceIndexSymbol());

            // check if there are venueEvents
            if (currentLocation.getVenueEvents().length != 0) {
                if (currentLocation.getVenueEventByWeekday(dayOfWeek) != null) {
                    venueEventName.setText(currentLocation.getVenueEventByWeekday(dayOfWeek).getVenueEventName());
                    venueShortDescription.setText(currentLocation.getVenueEventByWeekday(dayOfWeek).getShortDescription());
                }
            }

            // implement buttons
            Button venueMore = (Button)convertView.findViewById(R.id.preview_venue_moreButton);
            Button venueRoute = (Button)convertView.findViewById(R.id.preview_venue_routeButton);

            venueMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openListItemActivity(position);
                }
            });

            venueRoute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Route Button clicked! Item: " + position, Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // get current location
            Event currentLocation = (Event)locations.get(position);

            // get each view of the event layout
            ImageView eventImage = (ImageView)convertView.findViewById(R.id.preview_event_eventImage);
            TextView eventName = (TextView)convertView.findViewById(R.id.preview_event_eventName);
            TextView eventDate = (TextView)convertView.findViewById(R.id.preview_event_eventDate);
            TextView eventShortDescription = (TextView)convertView.findViewById(R.id.preview_event_eventShortDescription);
            TextView eventKm = (TextView)convertView.findViewById(R.id.preview_event_eventKm);
            TextView eventPriceIndex = (TextView)convertView.findViewById(R.id.preview_event_eventPriceIndex);

            // set values with the current location data
            eventImage.setImageResource(R.drawable.party);
            eventName.setText(currentLocation.getName());
            eventDate.setText(currentLocation.getDate());
            eventShortDescription.setText(currentLocation.getShortDescription());
            eventKm.setText(String.valueOf(currentLocation.getDistanceShort()) + "km");
            eventPriceIndex.setText(currentLocation.getPriceIndexSymbol());

            // implement buttons
            Button eventMore = (Button)convertView.findViewById(R.id.preview_event_moreButton);
            Button eventRoute = (Button)convertView.findViewById(R.id.preview_event_routeButton);

            eventMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openListItemActivity(position);
                }
            });

            eventRoute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Route Button clicked! Item: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        convertView.setClipToOutline(true);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Location currentLocation = locations.get(position);
        if (currentLocation instanceof Venue) {
            return VENUE;
        } else {
            return EVENT;
        }
    }

    private void openListItemActivity(int position) {
        Intent intent= new Intent(context, ListItemActivity.class);

        Location currentLocation = locations.get(position);
        // get common values for passing to the list item activity
        intent.putExtra("name", currentLocation.getName());
        intent.putExtra("longDescription", currentLocation.getLongDescription());
        intent.putExtra("distance", currentLocation.getDistanceShort());
        intent.putExtra("priceIndex", currentLocation.getPriceIndexSymbol());
        intent.putExtra("type", currentLocation.getType());
        intent.putExtra("age", currentLocation.getAge());
        intent.putExtra("entrance", currentLocation.getEntryFee());
        intent.putExtra("address", currentLocation.getAddressStreet() + " " + currentLocation.getAddressNr() + ", " +  currentLocation.getAddressPLZ() + " " +  currentLocation.getAddressCity());

        // get individual values for passing to the list item activity
        intent.putExtra("eventDate", "");
        intent.putExtra("venueEventName", "");
        intent.putExtra("openingHours", "");

        if (getItemViewType(position) == VENUE) {

            if (((Venue) currentLocation).getVenueEvents().length == 0) {
                if (((Venue)currentLocation).getVenueEventByWeekday(dayOfWeek) != null) {
                    intent.putExtra("venueEventName", ((Venue) currentLocation).getVenueEventByWeekday(dayOfWeek).getVenueEventName());
                    intent.putExtra("longDescription", ((Venue) currentLocation).getVenueEventByWeekday(dayOfWeek).getLongDescription());
                }
            }
            if (((Venue) currentLocation).getOpeningHours().length == 0) {
                intent.putExtra("openingHours", ((Venue) currentLocation).getAllOpeningHours());
            }

        } else {
            intent.putExtra("eventDate", ((Event) currentLocation).getDate());
        }

        context.startActivity(intent);
    }
}



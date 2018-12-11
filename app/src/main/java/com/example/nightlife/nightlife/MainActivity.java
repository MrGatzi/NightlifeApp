package com.example.nightlife.nightlife;

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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // array with content for the list view elements (preview_event and preview_venue layouts)
    String[] EVENT_NAMES = {"Event Name 1", "Event Name 2", "Event Name 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        * DUMMY OBJECT START -----------------------------------------------------------------------
        */
        List<Location> venues = new ArrayList<Location>();
        List<Location> events = new ArrayList<Location>();
        List<List<Location>> locations = new ArrayList<List<Location>>();

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

        venues.add(
                new Venue(
                        32,
                        "QABOOS",
                        "",
                        "Dübelstepü",
                        "33.825512",
                        "-82.156488",
                        4,
                        1,
                        19,
                        "Nostrud ipsum aliquip laboris veniam aliqua labore amet pariatur magna enim aute consectetur. In consequat amet ea elit minim minim cillum do reprehenderit magna commodo deserunt. Tempor non id Lorem aliquip deserunt id sit duis voluptate sit qui esse Lorem nulla. Aliquip do proident nostrud dolor reprehenderit ea laborum cillum non in reprehenderit mollit occaecat. Elit aute aute dolore dolor quis voluptate est occaecat pariatur. Reprehenderit tempor sit ad sunt in sunt occaecat excepteur velit dolore in mollit.",
                        "dolor reprehenderit nostrud aliquip eu dolore fugiat cupidatat nostrud ullamco amet ad qui tempor et",
                        "Cumminsville",
                        4932,
                        "Dahl Court",
                        39,
                        new OpeningHours(1,
                                "Sun Jan 07 2018 17:05:08 GMT+0000 (UTC)",
                                "Wed Jan 04 2017 20:14:00 GMT+0000 (UTC)"
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

        events.add(new Event(
                        5,
                        "OBLIQ",
                        "Chill",
                        "10.75869",
                        "85.740765",
                        "Tue May 01 2018 06:07:33 GMT+0000 (UTC)",
                        4,
                        27,
                        27,
                        "Deserunt irure et culpa cillum. Dolor et laboris aliquip aute. Amet non do deserunt est non adipisicing mollit aliquip ea cillum duis. Duis aute velit non magna tempor ex qui enim ullamco dolor voluptate. Mollit ex est esse id ex Lorem cillum aute ipsum reprehenderit eiusmod deserunt labore eu. Proident ea dolore ex nostrud irure esse dolore quis.",
                        "sint sunt qui elit ipsum veniam ullamco duis esse consectetur excepteur id minim sit proident",
                        "Wanamie",
                        4574,
                        "Beekman Place",
                        27
                )
        );

        locations.add(venues);
        locations.add(events);
        /*
         * DUMMY OBJECT END ------------------------------------------------------------------------
         */

        // test: get long and lat of all locations
        for (int i=0; i<locations.size(); i++){
            for (int j=0; j<locations.get(i).size(); j++){
                double locLat = Double.parseDouble(locations.get(i).get(j).getLocLat());
                double locLong = Double.parseDouble(locations.get(i).get(j).getLocLong());

                // do fancy map shit with the coordinates

            }
        }

        // merge listlist to one list
        ArrayList<Location> mergedList = new ArrayList<Location>(locations.get(0));
        mergedList.addAll(locations.get(1));

        // test: list preview
        ListView previewList = (ListView)findViewById(R.id.list_previewList);

        PreviewListAdapter previewListAdapter = new PreviewListAdapter(getApplicationContext(), R.layout.preview_venue, mergedList);
        // CustomAdapter customAdapter = new CustomAdapter();
        previewList.setAdapter(previewListAdapter);
        // previewList.setAdapter(customAdapter);




        /*
        // template floating button (bottom right corner)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hallo Isi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    /*
    // create Adapter for listView
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return EVENT_NAMES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.preview_event, null);

            // get elements in preview_event layout
            TextView event_names = (TextView)convertView.findViewById(R.id.preview_event_eventName);

            // set elements in preview_event layout
            event_names.setText(EVENT_NAMES[position]);

            convertView.setClipToOutline(true);
            return convertView;
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
    // template menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    /*
    // template menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
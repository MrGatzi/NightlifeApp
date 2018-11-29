package com.example.nightlife.nightlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // array with content for the list view elements (preview_event and preview_venue layouts)
    String[] EVENT_NAMES = {"Event Name 1", "Event Name 2", "Event Name 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hallo Isi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // set up listView
        ListView list_listView = (ListView)findViewById(R.id.list_listView);
        CustomAdapter customAdapter = new CustomAdapter();
        list_listView.setAdapter(customAdapter);
        }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



  /*  public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/


/*    @Override
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
    }*/
}

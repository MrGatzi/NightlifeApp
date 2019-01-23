package com.example.nightlife.nightlife;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FilterActivity extends AppCompatActivity {

    // save states in bundle
    private static Bundle states = new Bundle();

    // buttons
    private ToggleButton filter1_disco;
    private ToggleButton filter1_bar;
    private ToggleButton filter1_event;
    private ToggleButton filter2_poor;
    private ToggleButton filter2_medium;
    private ToggleButton filter2_rich;
    private ToggleButton filter3_near;
    private ToggleButton filter3_medium;
    private ToggleButton filter3_far;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Toolbar toolbar = findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filter");

        // get all filter buttons
        filter1_disco = (ToggleButton) findViewById(R.id.filter1_button1_disco);
        filter1_bar = findViewById(R.id.filter1_button2_bar);
        filter1_event = findViewById(R.id.filter1_button3_event);
        filter2_poor = findViewById(R.id.filter2_button1_poor);
        filter2_medium = findViewById(R.id.filter2_button2_medium);
        filter2_rich = findViewById(R.id.filter2_button3_rich);
        filter3_near = findViewById(R.id.filter3_button1_near);
        filter3_medium = findViewById(R.id.filter3_button2_medium);
        filter3_far = findViewById(R.id.filter3_button3_far);

        submit = findViewById(R.id.filter_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPause() {
        super.onPause();
        states.putBoolean("state_filter1_disco", filter1_disco.isChecked());
        states.putBoolean("state_filter1_bar", filter1_bar.isChecked());
        states.putBoolean("state_filter1_event", filter1_event.isChecked());

        states.putBoolean("state_filter2_poor", filter2_poor.isChecked());
        states.putBoolean("state_filter2_medium", filter2_medium.isChecked());
        states.putBoolean("state_filter2_rich", filter2_rich.isChecked());

        states.putBoolean("state_filter3_near", filter3_near.isChecked());
        states.putBoolean("state_filter3_medium", filter3_medium.isChecked());
        states.putBoolean("state_filter3_far", filter3_far.isChecked());

        Intent intent_filter = new Intent(FilterActivity.this, MainActivity.class);
        intent_filter.putExtras(states);
        startActivity(intent_filter);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onResume() {
        super.onResume();
        filter1_disco.setChecked(states.getBoolean("state_filter1_disco",false));
        filter1_bar.setChecked(states.getBoolean("state_filter1_bar",false));
        filter1_event.setChecked(states.getBoolean("state_filter1_event",false));

        filter2_poor.setChecked(states.getBoolean("state_filter2_poor",false));
        filter2_medium.setChecked(states.getBoolean("state_filter2_medium",false));
        filter2_rich.setChecked(states.getBoolean("state_filter2_rich",false));

        filter3_near.setChecked(states.getBoolean("state_filter3_near",false));
        filter3_medium.setChecked(states.getBoolean("state_filter3_medium",false));
        filter3_far.setChecked(states.getBoolean("state_filter3_far",false));
    }
}

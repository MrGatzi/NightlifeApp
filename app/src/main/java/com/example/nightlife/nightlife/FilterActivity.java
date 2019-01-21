package com.example.nightlife.nightlife;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FilterActivity extends AppCompatActivity {

    private boolean[] filter = new boolean[9];

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
        final ToggleButton filter1_disco = (ToggleButton) findViewById(R.id.filter1_button1_disco);
        final ToggleButton filter1_bar = findViewById(R.id.filter1_button2_bar);
        final ToggleButton filter1_event = findViewById(R.id.filter1_button3_event);
        final ToggleButton filter2_poor = findViewById(R.id.filter2_button1_poor);
        final ToggleButton filter2_medium = findViewById(R.id.filter2_button2_medium);
        final ToggleButton filter2_rich = findViewById(R.id.filter2_button3_rich);
        final ToggleButton filter3_near = findViewById(R.id.filter3_button1_near);
        final ToggleButton filter3_medium = findViewById(R.id.filter3_button2_medium);
        final ToggleButton filter3_far = findViewById(R.id.filter3_button3_far);

        Button submit = findViewById(R.id.filter_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter[0] = filter1_disco.isChecked();
                filter[1] = filter1_bar.isChecked();
                filter[2] = filter1_event.isChecked();
                filter[3] = filter2_poor.isChecked();
                filter[4] = filter2_medium.isChecked();
                filter[5] = filter2_rich.isChecked();
                filter[6] = filter3_near.isChecked();
                filter[7] = filter3_medium.isChecked();
                filter[8] = filter3_far.isChecked();

                Intent intent = new Intent(FilterActivity.this, MainActivity.class);
                intent.putExtra("filter", filter);
                startActivity(intent);
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
}

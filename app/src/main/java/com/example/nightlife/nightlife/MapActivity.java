package com.example.nightlife.nightlife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    int year;
    int month;
    int dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        dayOfMonth = intent.getIntExtra("dayOfMonth", 0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        ImageView search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapActivity.this, "Search clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.getMenu().getItem(1).setChecked(true);
        bottomNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_list:
                        Intent intent = new Intent(MapActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_map:
                        break;
                }
            }
        });

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(MapActivity.this,
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

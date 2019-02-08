package com.example.nightlife.nightlife;

import android.Manifest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nightlife.nightlife.models.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //coordinates that cover the entire world
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));

    //widgets
    private AutoCompleteTextView mSearchText;

    //variables
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;

    // datepicker + long, lats from Main Activity
    private int year;
    private int month;
    private int dayOfMonth;
    int dayOfWeek;

    // request queue
    private RequestQueue queue;
    private ArrayList<com.example.nightlife.nightlife.Location> locations = new ArrayList<com.example.nightlife.nightlife.Location>();
    private ArrayList<com.example.nightlife.nightlife.Location> locations_filtered = new ArrayList<>();

    // filter
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

    //onCreate -> set activity_map layout and get location permissions
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mSearchText = (AutoCompleteTextView) findViewById(R.id.searchbar);

        filter_active = false;
        filter1_disco = false;
        filter1_bar = false;
        filter1_event = false;
        filter2_poor = false;
        filter2_medium = false;
        filter2_rich = false;
        filter3_near = false;
        filter3_medium = false;
        filter3_far = false;

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
        bottomNavigation.setItemIconTintList(null);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigation.getChildAt(0);
        //item size
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        bottomNavigation.getMenu().getItem(1).setChecked(true);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_list:
                        Intent intent = new Intent(MapActivity.this, MainActivity.class);
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
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        onStop();
                        break;
                    case R.id.navigation_map:
                        break;
                }
                return false;
            }
        });

        getLocationPermission();
    }

    //checks if we have location permissions from the user, if yes -> initialize the map, if not -> request location permissions via onRequestPermissionsResult
    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions ");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        //checks the permissions for FINE_LOCATION and COARSE_LOCATION, else request the permissions with request code 1234
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    //if we don't have the user's location permissions -> get permissions and initialize map
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        //checks if some permissions were granted (FINE or COARSE), else
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize map
                    initMap();
                }
            }
        }
    }

    //initialize the map with the mapFragment from activity_map
    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }

    //if map initialized and is ready -> get current location of the user
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {

            getDeviceLocation();

            //advanced permission checker provided by Android/Google
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }

    //get current location of the user (updates every 5 minutes now - change?) and move camera to users current location
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        //umschreiben?? Location wird halt alle 5min neu gesucht und Kamera wird gemoved -> will man das?
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(300000);
        mLocationRequest.setFastestInterval(300000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(mLocationPermissionsGranted){
                Log.d(TAG, "getDeviceLocation: Location found!");
                mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: " + e.getMessage() );
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<android.location.Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(0);
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                if (mMarker != null) {
                    mMarker.remove();
                }

                moveCamera(new LatLng(location.getLatitude(), location.getLongitude()), DEFAULT_ZOOM, "My Location");
            }
        }
    };

    //move the camera to current location
    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to lat: " + latLng.latitude + ". lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));



        if (!title.equals("My Location")) {
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMarker = mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    //move the camera to inserted location
    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving the camera to lat: " + latLng.latitude + ". lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        //mMap.clear();

        mMap.addMarker(new MarkerOptions().position(latLng));

        hideSoftKeyboard();
    }

    //initialize
    private void init() {
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        AutocompleteFilter countryFilter = new AutocompleteFilter.Builder()
                .setCountry("AT")
                .build();

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, countryFilter);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        //change Enter key to action Search
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //execute our method for searching
                    geoLocate();
                    return true;
                }
                return false;
            }
        });

        /*//Change enter key on keyboard to action (search)
        mSearchText.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                    //execute our method for searching
                    geoLocate();
                }
                return false;
            }
        });*/

        addVenueMarkers();

    }

    //autocomplete adapter
    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    //get place informations
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                mPlace.setAddress(place.getAddress().toString());
                if (place.getAttributions() == null) {
                    mPlace.setAttributions("null");
                }else{
                    mPlace.setAttributions(place.getAttributions().toString());
                }
                mPlace.setId(place.getId());
                mPlace.setLatlng(place.getLatLng());
                mPlace.setRating(place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                mPlace.setWebsiteUri(place.getWebsiteUri());

                Log.d(TAG, "onResult: place: " + mPlace.getName() + " lat/lng: " + mPlace.getLatlng());
            }catch (NullPointerException e){
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
            }

            moveCamera(mPlace.getLatlng(),DEFAULT_ZOOM);

            places.release();
        }
    };

    //hides keyboard
    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
    }

    //search for the inserted location in the searchbar and move the camera to its position
    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        if (mMarker != null) {
            mMarker.remove();
        }

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
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
                Intent intent = new Intent(MapActivity.this, FilterActivity.class);
                startActivityForResult(intent, 222);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                onStop();
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

    public void addVenueMarkers(){

        String name = "";
        double lat;
        double lng;

        MarkerOptions options = new MarkerOptions();

        if (!filter1_disco && !filter1_bar && !filter1_event && !filter2_poor && !filter2_medium && !filter2_rich && !filter3_near && !filter3_medium && !filter3_far) {
            for (int i = 0; i < locations.size(); i++) {
                locations_filtered.add(locations.get(i));
            }
        }

        for(int i = 0; i < locations_filtered.size(); i++) {
            name = locations_filtered.get(i).getName();
            options.title(name);
            Log.d(TAG, "addVenueMarkers: Venue name: " + name);

            lat = locations_filtered.get(i).getLocLat();
            Log.d(TAG, "addVenueMarkers: Venue lat: " + lat);

            lng = locations_filtered.get(i).getLocLong();
            Log.d(TAG, "addVenueMarkers: Venue lng: " + lng);

            options.position(new LatLng(lat, lng));
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(options);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (intent.getExtras() != null && !filter_active) {
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

        locations.clear();
        locations_filtered.clear();

        // get data from database
        queue = Volley.newRequestQueue(this);
        jsonParse();

        // set data for map activity
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JSONObject>() {
            @Override
            public void onRequestFinished(Request<JSONObject> request) {

                // check if the intent is null (user never set a filter and no extras had been put in filter activity)
                if (filter1_disco || filter1_bar || filter1_event || filter2_poor || filter2_medium || filter2_rich || filter3_near || filter3_medium || filter3_far){
                    // if one of the filter1 buttons is active > filter them
                    if (filter1_disco || filter1_bar || filter1_event) {
                        for (int i = 0; i < locations.size(); i++) {
                            if (!filter1_disco && locations.get(i).getType().equals("Diskothek")) {
                                locations.get(i).setVisible(false);
                            } else if ((!filter1_bar && locations.get(i).getType().equals("Bar")) || (!filter1_bar && locations.get(i).getType().equals("Pub"))) {
                                locations.get(i).setVisible(false);
                            } else if (!filter1_event && locations.get(i).getType().equals("Event")) {
                                locations.get(i).setVisible(false);
                            }
                        }
                    }

                    // if one of the filter2 buttons is active > filter them
                    if (filter2_poor || filter2_medium || filter2_rich) {
                        for (int i = 0; i < locations.size(); i++) {
                            if (!filter2_poor && locations.get(i).getPriceIndex() == 1) {
                                locations.get(i).setVisible(false);
                            } else if (!filter2_medium && locations.get(i).getPriceIndex() == 2) {
                                locations.get(i).setVisible(false);
                            } else if (!filter2_rich && locations.get(i).getPriceIndex() == 3) {
                                locations.get(i).setVisible(false);
                            }
                        }
                    }

                    // if one of the filter3 buttons is active > filter them
                    if (filter3_near || filter3_medium || filter3_far) {
                        for (int i = 0; i < locations.size(); i++) {
                            if (!filter3_near && locations.get(i).getDistance() <= 5) {
                                locations.get(i).setVisible(false);
                            } else if (!filter3_medium && locations.get(i).getDistance() > 5 && locations.get(i).getDistance() <= 20){
                                locations.get(i).setVisible(false);
                            } else if (!filter3_far && locations.get(i).getDistance() > 20) {
                                locations.get(i).setVisible(false);
                            }
                        }
                    }
                }

                // get all visible locations
                for (int i = 0; i < locations.size(); i++){
                    if (locations.get(i).isVisible()) {
                        locations_filtered.add(locations.get(i));
                    }
                }

                if (mMap != null) {
                    mMap.clear();
                }
                addVenueMarkers();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        filter_active = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 222 && resultCode == RESULT_OK && data != null) {
            if (data.getExtras() != null) {
                filter_active = true;

                filter1_disco = data.getBooleanExtra("state_filter1_disco", false);
                filter1_bar = data.getBooleanExtra("state_filter1_bar", false);
                filter1_event = data.getBooleanExtra("state_filter1_event", false);

                filter2_poor = data.getBooleanExtra("state_filter2_poor", false);
                filter2_medium = data.getBooleanExtra("state_filter2_medium", false);
                filter2_rich = data.getBooleanExtra("state_filter2_rich", false);

                filter3_near = data.getBooleanExtra("state_filter3_near", false);
                filter3_medium = data.getBooleanExtra("state_filter3_medium", false);
                filter3_far = data.getBooleanExtra("state_filter3_far", false);
            }
        }
    }
}

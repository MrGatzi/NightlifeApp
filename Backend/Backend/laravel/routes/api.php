<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
//Events: 
Route::get('events', 'EventController@showAll');
Route::post('events', 'EventController@store');
Route::get('events/{EventID}', 'EventController@getSingle');
Route::put('events/{EventID}', 'EventController@update');
Route::delete('events/{EventID}', 'EventController@delete');
//Venues:
Route::get('venues', 'VenueController@showAll');
Route::post('venues', 'VenueController@store');
Route::get('venues/{VenueID}', 'VenueController@getwSingle');
Route::put('venues/{VenueID}', 'VenueController@update');
Route::delete('venues/{VenueID}', 'VenueController@delete');
//VenueEvents:
Route::get('venueevent', 'VenueEventsController@showAll');

Route::get('venueevent/{VenueID}', 'VenueEventsController@getSingle');
Route::delete('venueevent/{VenueID}', 'VenueEventsController@delete');

Route::get('venueevent/{VenueID}/{WeekDayID}', 'VenueEventsController@getSingleWeekDay');
Route::post('venueevent/{VenueID}/{WeekDayID}', 'VenueEventsController@storeSingleWeekDay');
Route::put('venueevent/{VenueID}/{WeekDayID}', 'VenueEventsController@updateSingleWeekDay');
Route::delete('venueevent/{VenueID}/{WeekDayID}', 'VenueEventsController@deleteSingleWeekDay');
//OpeningHours:
Route::get('openinghours', 'OpeningHoursController@showAll');

Route::get('openinghours/{VenueID}', 'OpeningHoursController@getSingle');
Route::delete('openinghours/{VenueID}', 'OpeningHoursController@delete');

Route::get('openinghours/{VenueID}/{WeekDayID}', 'OpeningHoursController@getSingleWeekDay');
Route::post('openinghours/{VenueID}/{WeekDayID}', 'OpeningHoursController@storeSingleWeekDay');
Route::put('openinghours/{VenueID}/{WeekDayID}', 'OpeningHoursController@updateSingleWeekDay');
Route::delete('openinghours/{VenueID}/{WeekDayID}', 'OpeningHoursController@deleteSingleWeekDay');
//Locations:
Route::get('location/{lat}/{long}', 'LocationController@getVenuesAndEventsForLocation');
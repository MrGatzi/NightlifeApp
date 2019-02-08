<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use App\Event;
use App\VenueEvents;
use App\Venue;
use App\OpeningHours;

class LocationController extends Controller
{
    public function getVenuesAndEventsForLocation($lat,$long){
        /*$orders = DB::table('events')
                //->selectRaw('2 * 6371 * asin(sqrt((sin(radians((LocLat - ?) / 2))) ^ 2 + cos(radians(?)) * cos(radians(LocLat)) * (sin(radians((LocLong - ?) / 2))) ^ 2)) as distance', [$lat,$lat,$long])
                ->selectRaw('radians((LocLat) as dd')
                ->get();
       return $orders;*/
        $events= DB::table('events')
        ->select('EventID','Name', 'Type','LocLat','LocLong','Date','PriceIndex','Age','EntryFee','LongDescription','ShortDescription','AddressCity','AddressPLZ','AddressStreet','AddressNr', DB::raw(sprintf(
            '(6371 * acos(cos(radians(%1$.7f)) * cos(radians(LocLat)) * cos(radians(LocLong) - radians(%2$.7f)) + sin(radians(%1$.7f)) * sin(radians(LocLat)))) AS distance',
            $lat,
            $long
        )))
        ->having('distance', '<', 30)
        ->orderBy('distance', 'asc')
        ->get();
        
        $venues = DB::table('venues')
        ->select('VenueID','Name', 'Type','LocLat','LocLong','PriceIndex','Age','EntryFee','LongDescription','ShortDescription','AddressCity','AddressPLZ','AddressStreet','AddressNr', DB::raw(sprintf(
            '(6371 * acos(cos(radians(%1$.7f)) * cos(radians(LocLat)) * cos(radians(LocLong) - radians(%2$.7f)) + sin(radians(%1$.7f)) * sin(radians(LocLat)))) AS distance',
            $lat,
            $long
        )))
        ->having('distance', '<', 30)
        ->orderBy('distance', 'asc')
        ->get();
        
        foreach ($venues as $venue)
        {
            $venue->Week=array();
            $VenueEvents=VenueEvents::where('VenueID', $venue->VenueID)->get();
            foreach($VenueEvents as $VenueEvent){
               $SingleVenueEventObject = new \stdClass;
               $SingleVenueEventObject->WeekDay=$VenueEvent->WeekDay;
               $SingleVenueEventObject->LongDescription=$VenueEvent->LongDescription;
               $SingleVenueEventObject->ShortDescription=$VenueEvent->ShortDescription;
               $SingleVenueEventObject->VenueEventName=$VenueEvent->VenueEventName;
               array_push($venue->Week, $SingleVenueEventObject);
            }
        }
         foreach ($venues as $venue)
        {
            $venue->OpeningHours=array();
            $OpeningHours=OpeningHours::where('VenueID', $venue->VenueID)->get();
            foreach($OpeningHours as $OpeningHour){
               $SingleVenueEventObject = new \stdClass;
               $SingleVenueEventObject->WeekDay=$OpeningHour->WeekDay;
               $SingleVenueEventObject->DOpen=$OpeningHour->DOpen;
               $SingleVenueEventObject->DClose=$OpeningHour->DClose;
               array_push($venue->OpeningHours, $SingleVenueEventObject);
            }
        }
    $location=new \stdClass;
    $location->Venues=$venues;
    $location->Events=$events;
    
    $json = json_encode($location,JSON_UNESCAPED_UNICODE);
    return $json;
    }
    
}
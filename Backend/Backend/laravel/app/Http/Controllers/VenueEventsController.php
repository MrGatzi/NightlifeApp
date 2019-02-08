<?php

namespace App\Http\Controllers;

use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\VenueEvents;
//use App\Http\Controllers\stdClass;

class VenueEventsController extends Controller
{
    public function showAll(){
        $VenueEventIDs=VenueEvents::where('VenueID' ,'>' ,0)->pluck('VenueID')->toArray();
        $VenueEventIDs = array_unique($VenueEventIDs);
        //$VenueEvents=Event::all();
        $transformedRow = array();
        foreach ($VenueEventIDs as $VenueEventID)
        {
            $VenueObject = new \stdClass;
            $VenueObject->VenueID = $VenueEventID;
            $VenueObject->Week=array();
            $VenueEvents=VenueEvents::where('VenueID', $VenueEventID)->get();
            foreach($VenueEvents as $VenueEvent){
               $SingleVenueEventObject = new \stdClass;
               $SingleVenueEventObject->WeekDay=$VenueEvent->WeekDay;
               $SingleVenueEventObject->LongDescription=$VenueEvent->LongDescription;
               $SingleVenueEventObject->ShortDescription=$VenueEvent->ShortDescription;
               $SingleVenueEventObject->VenueEventName=$VenueEvent->VenueEventName;
               array_push($VenueObject->Week, $SingleVenueEventObject);
            }
            
            array_push($transformedRow, $VenueObject);
        }
        $json = json_encode($transformedRow);
        return $json;
    }
    
    public function getSingle($VenueID){
        
       $VenueEventIDs=VenueEvents::where('VenueID' ,$VenueID)->pluck('VenueID')->toArray();
        $VenueEventIDs = array_unique($VenueEventIDs);
        //$VenueEvents=Event::all();
        $transformedRow = array();
        foreach ($VenueEventIDs as $VenueEventID)
        {
            $VenueObject = new \stdClass;
            $VenueObject->VenueID = $VenueEventID;
            $VenueObject->Week=array();
            $VenueEvents=VenueEvents::where('VenueID', $VenueEventID)->get();
            foreach($VenueEvents as $VenueEvent){
               $SingleVenueEventObject = new \stdClass;
               $SingleVenueEventObject->WeekDay=$VenueEvent->WeekDay;
               $SingleVenueEventObject->LongDescription=$VenueEvent->LongDescription;
               $SingleVenueEventObject->ShortDescription=$VenueEvent->ShortDescription;
               $SingleVenueEventObject->VenueEventName=$VenueEvent->VenueEventName;
               array_push($VenueObject->Week, $SingleVenueEventObject);
            }
            
            array_push($transformedRow, $VenueObject);
        }
        $json = json_encode($transformedRow);
        return $json;
    }
    public function delete($VenueID){
        $VenueEventIDs=VenueEvents::where('VenueID' ,$VenueID)->delete();

        return 200;
    }
    public function getSingleWeekDay($VenueID,$WeekDayID){
        $VenueEvent=VenueEvents::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->first();
        $SingleVenueEventObject = new \stdClass;
        $SingleVenueEventObject->WeekDay=$VenueEvent->WeekDay;
        $SingleVenueEventObject->LongDescription=$VenueEvent->LongDescription;
        $SingleVenueEventObject->ShortDescription=$VenueEvent->ShortDescription;
        $SingleVenueEventObject->VenueEventName=$VenueEvent->VenueEventName;
        $json = json_encode($SingleVenueEventObject);
        return $json;
    }
    public function storeSingleWeekDay(Request $request,$VenueID,$WeekDayID){

        $VenueObject = array();
        $VenueObject['VenueID'] = $VenueID;
        $VenueObject['WeekDay'] = $WeekDayID;
        $VenueObject['LongDescription'] = $request->get('LongDescription');
        $VenueObject['ShortDescription'] = $request->get('ShortDescription');
        $VenueObject['VenueEventName'] = $request->get('VenueEventName');
        VenueEvents::create($VenueObject);
    }
    public function updateSingleWeekDay(Request $request,$VenueID,$WeekDayID){
        $VenueEvent=VenueEvents::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->first();
        $VenueEventID=$VenueEvent->VenueEventID;
        $rules = array(
            'LongDescription'=>'required',
            'ShortDescription'=>'required',
            'VenueEventName'=>'required'
        );
        $validator = Validator::make($request->all(), $rules);

        // process the login
        if ($validator->fails()) {
           return 405;
        } else {
            // store
            $VenueEventUpdate = VenueEvents::find($VenueEventID);
            $VenueEventUpdate->LongDescription = $request->get('LongDescription');
            $VenueEventUpdate->ShortDescription = $request->get('ShortDescription'); 
            $VenueEventUpdate->VenueEventName = $request->get('VenueEventName');  
            $VenueEventUpdate->save();
            return 200;
        }
    }
    public function deleteSingleWeekDay($VenueID,$WeekDayID){
         $VenueEvent=VenueEvents::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->delete();

        return 200;
    }
    
}

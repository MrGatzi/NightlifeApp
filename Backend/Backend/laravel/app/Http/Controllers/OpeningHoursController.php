<?php

namespace App\Http\Controllers;

use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\OpeningHours;


class OpeningHoursController extends Controller
{
    public function showAll(){
        $OpeningHoursIDs=OpeningHours::where('VenueID' ,'>' ,0)->pluck('VenueID')->toArray();
        $OpeningHoursIDs = array_unique($OpeningHoursIDs);
        //$VenueEvents=Event::all();
        $transformedRow = array();
        foreach ($OpeningHoursIDs as $OpeningHoursID)
        {
            $OpeningHourObject = new \stdClass;
            $OpeningHourObject->VenueID = $OpeningHoursID;
            $OpeningHourObject->Week=array();
            $OpeningHours=OpeningHours::where('VenueID', $OpeningHoursID)->get();
            foreach($OpeningHours as $OpeningHour){
               $SingleOpeningHourObject = new \stdClass;
               $SingleOpeningHourObject->WeekDay=$OpeningHour->WeekDay;
               $SingleOpeningHourObject->DOpen=$OpeningHour->DOpen;
               $SingleOpeningHourObject->DClose=$OpeningHour->DClose;
               array_push($OpeningHourObject->Week, $SingleOpeningHourObject);
            }
            
            array_push($transformedRow, $OpeningHourObject);
        }
        $json = json_encode($transformedRow);
        return $json;
    }
    public function getSingle($VenueID){
        
        $OpeningHoursIDs=OpeningHours::where('VenueID' ,$VenueID)->pluck('VenueID')->toArray();
        $OpeningHoursIDs = array_unique($OpeningHoursIDs);
        $transformedRow = array();
        foreach ($OpeningHoursIDs as $OpeningHoursID)
        {
            $OpeningHourObject = new \stdClass;
            $OpeningHourObject->VenueID = $OpeningHoursID;
            $OpeningHourObject->Week=array();
            $OpeningHours=OpeningHours::where('VenueID', $OpeningHoursID)->get();
            foreach($OpeningHours as $OpeningHour){
               $SingleOpeningHourObject = new \stdClass;
               $SingleOpeningHourObject->WeekDay=$OpeningHour->WeekDay;
               $SingleOpeningHourObject->DOpen=$OpeningHour->DOpen;
               $SingleOpeningHourObject->DClose=$OpeningHour->DClose;
               array_push($OpeningHourObject->Week, $SingleOpeningHourObject);
            }
            
            array_push($transformedRow, $OpeningHourObject);
        }
        $json = json_encode($transformedRow);
        return $json;
    }
    public function delete($VenueID){
        $OpeningHourIDs=OpeningHours::where('VenueID' ,$VenueID)->delete();

        return 200;
    }
    public function getSingleWeekDay($VenueID,$WeekDayID){
        $OpeningHour=OpeningHours::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->first();
        $SingleOpeningHourObject = new \stdClass;
        $SingleOpeningHourObject->WeekDay=$OpeningHour->WeekDay;
        $SingleOpeningHourObject->DOpen=$OpeningHour->DOpen;
        $SingleOpeningHourObject->DClose=$OpeningHour->DClose;
        $json = json_encode($SingleOpeningHourObject);
        return $json;
    }
    public function storeSingleWeekDay(Request $request,$VenueID,$WeekDayID){

        $OpeningHoursObject = array();
        $OpeningHoursObject['VenueID'] = $VenueID;
        $OpeningHoursObject['WeekDay'] = $WeekDayID;
        $OpeningHoursObject['DOpen'] = $request->get('DOpen');
        $OpeningHoursObject['DClose'] = $request->get('DClose');
        OpeningHours::create($OpeningHoursObject);
    }
    public function updateSingleWeekDay(Request $request,$VenueID,$WeekDayID){
        $OpeningHour=OpeningHours::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->first();
        $OpeningHourID=$OpeningHour->OpeningHoursID;
        $rules = array(
            'DOpen'=>'required',
            'DClose'=>'required',
        );
        $validator = Validator::make($request->all(), $rules);

        // process the login
        if ($validator->fails()) {
           return 405;
        } else {
            // store
            $OpeningHourUpdate = OpeningHours::find($OpeningHourID);
            $OpeningHourUpdate->DOpen = $request->get('DOpen');
            $OpeningHourUpdate->DClose = $request->get('DClose');               
            $OpeningHourUpdate->save();
            return 200;
        }
    }
    public function deleteSingleWeekDay($VenueID,$WeekDayID){
         $OpeningHours=OpeningHours::where('VenueID' ,$VenueID)->where('WeekDay',$WeekDayID)->delete();

        return 200;
    }
}

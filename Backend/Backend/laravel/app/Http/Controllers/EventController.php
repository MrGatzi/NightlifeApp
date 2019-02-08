<?php

namespace App\Http\Controllers;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\Event;

class EventController extends Controller
{
    public function showAll()
    {
        return Event::all();
    }
 
    public function getSingle($EventID)
    {
        
        try
        {
            return  Event::findOrFail($EventID);
        }catch(ModelNotFoundException $e)
        {
            return 404;
        }
    }

    public function store(Request $request)
    {
        return Event::create($request->all());
    }

    public function update(Request $request, $id)
    {
        //$event = Event::findOrFail($id);
        //$event->update();
        
        $rules = array(
            'Name'       => 'required',
            'Type'      => 'required',
            'LocLat' => 'required|numeric',
            'LocLong' => 'required|numeric',
            'Date'=>'required',
            'PriceIndex'=>'required|numeric',
            'EntryFee'=>'required|numeric',
            'Age'=>'required|numeric',
            'LongDescription'=>'required',
            'ShortDescription'=>'required',
            'AddressCity'=>'required',
            'AddressPLZ'=>'required',
            'AddressStreet'=>'required',
            'AddressNr'=>'required'
        );
        $validator = Validator::make($request->all(), $rules);

        // process the login
        if ($validator->fails()) {
           return 405;
        } else {
            // store
            $event = Event::find($id);
            $event->Name       = $request->get('Name');
            $event->Type      = $request->get('Type');
            $event->LocLat = $request->get('LocLat');
            $event->LocLong = $request->get('LocLong');
            $event->Date = $request->get('Date');
            $event->PriceIndex = $request->get('PriceIndex');
            $event->EntryFee = $request->get('EntryFee');
            $event->Age = $request->get('Age');
            $event->LongDescription = $request->get('LongDescription');
            $event->ShortDescription = $request->get('ShortDescription');
            $event->AddressCity = $request->get('AddressCity');
            $event->AddressPLZ =$request->get('AddressPLZ');
            $event->AddressStreet = $request->get('AddressStreet');
            $event->AddressNr = $request->get('AddressNr');
                
                
            $event->save();
            return 200;
        }
    }

    public function delete($id)
    {
        $event = Event::findOrFail($id);
        $event->delete();

        return 204;
    }
}

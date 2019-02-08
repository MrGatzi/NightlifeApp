<?php

namespace App\Http\Controllers;
use Illuminate\Database\Eloquent\ModelNotFoundException;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\Venue;

class VenueController extends Controller
{
     public function showAll()
    {
         
        return Venue::all();
    }
 
    public function getSingle($id)
    {  
        try
        {
            return Venue::find($id);
        }catch(ModelNotFoundException $e)
        {
            return 404;
        }
    }

    public function store(Request $request)
    {
        return Venue::create($request->all());
    }

    public function update(Request $request, $id)
    {
        $rules = array(
            'Name'       => 'required',
            'Type'      => 'required',
            'LocLat' => 'required|numeric',
            'LocLong' => 'required|numeric',
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
            $venue = Venue::find($id);
            $venue->Name       = $request->get('Name');
            $venue->Type      = $request->get('Type');
            $venue->LocLat = $request->get('LocLat');
            $venue->LocLong = $request->get('LocLong');
            $venue->PriceIndex = $request->get('PriceIndex');
            $venue->EntryFee = $request->get('EntryFee');
            $venue->Age = $request->get('Age');
            $venue->LongDescription = $request->get('LongDescription');
            $venue->ShortDescription = $request->get('ShortDescription');
            $venue->AddressCity = $request->get('AddressCity');
            $venue->AddressPLZ =$request->get('AddressPLZ');
            $venue->AddressStreet = $request->get('AddressStreet');
            $venue->AddressNr = $request->get('AddressNr');
                
                
            $venue->save();
            return 200;
        }
        return $venue;
    }

    public function delete(Request $request, $id)
    {
        try
        {
            $venue = Venue::findOrFail($id);
        }catch(ModelNotFoundException $e)
        {
            return 404;
        }

        $venue->delete();

        return 204;
    }
}

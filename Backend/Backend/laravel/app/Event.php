<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Event extends Model
{
    //
     protected $fillable = ['Name', 'Type','LocLat','LocLong','Date','PriceIndex','Age','EntryFee','LongDescription','ShortDescription','AddressCity','AddressPLZ','AddressStreet','AddressNr'];
     public $timestamps = false;
     protected $primaryKey = 'EventID';
     
}

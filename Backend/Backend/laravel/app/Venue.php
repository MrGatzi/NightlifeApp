<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Venue extends Model
{
    //
    protected $fillable = ['Name', 'Type','LocLat','LocLong','PriceIndex','Age','EntryFee','LongDescription','ShortDescription','AddressCity','AddressPLZ','AddressStreet','AddressNr'];
    public $timestamps = false;
    protected $primaryKey = 'VenueID';
    
}

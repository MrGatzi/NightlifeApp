<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class VenueEvents extends Model
{
    //
    protected $fillable = ['VenueID','WeekDay','LongDescription','ShortDescription','VenueEventName'];
    public $timestamps = false;
    protected $primaryKey = 'VenueEventID';
}

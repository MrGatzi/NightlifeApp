<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class OpeningHours extends Model
{
    //
    protected $fillable = ['VenueID','WeekDay','DOpen','DClose'];
    public $timestamps = false;
    protected $primaryKey = 'OpeningHoursID';
    protected $casts = [
    'DOpen' => 'time:hh:mm:ss',
    'DClose' => 'time:hh:mm:ss'
    ];
}

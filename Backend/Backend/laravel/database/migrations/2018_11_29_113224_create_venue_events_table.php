<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateVenueEventsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('venue_events', function (Blueprint $table) {
            $table->increments('VenueEventID');
            $table->integer('VenueID')->unsigned();
            $table->integer('WeekDay');
            $table->text('LongDescription');
            $table->text('ShortDescription');
            $table->text('VenueEventName');
        });
        Schema::table('venue_events', function($table) {
            $table->foreign('VenueID')->references('VenueID')->on('Venues');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('venue_events');
    }
}

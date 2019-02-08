<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateOpeningHoursTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('opening_hours', function (Blueprint $table) {
            $table->increments('OpeningHoursID');
            $table->integer('VenueID')->unsigned();
            $table->integer('WeekDay');
            $table->time('DOpen');
            $table->time('DClose');
        });
        Schema::table('opening_hours', function($table) {
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
        Schema::dropIfExists('opening_hours');
    }
}

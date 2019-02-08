<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateEventsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Events', function (Blueprint $table) {
            $table->increments('EventID');
            $table->string('Name');
            $table->string('Type');
            $table->double('LocLat',15,8);
            $table->double('LocLong',15,8);
            $table->date('Date');
            $table->integer('PriceIndex');
            $table->integer('Age');
            $table->float('EntryFee');
            $table->text('LongDescription');
            $table->text('ShortDescription');
            $table->string('AddressCity');
            $table->string('AddressPLZ');
            $table->string('AddressStreet');
            $table->string('AddressNr');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Events');
    }
}

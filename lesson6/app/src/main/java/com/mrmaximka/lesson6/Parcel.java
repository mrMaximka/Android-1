package com.mrmaximka.lesson6;

import java.io.Serializable;

final class Parcel implements Serializable {

//    final static Integer[] resources = {R.drawable.msc, R.drawable.spb, R.drawable.ebrg, R.drawable.nsk, R.drawable.sam};

    /*String[] precipitationList = getResources().getStringArray(R.array.sky);  // Лист с осадками

        if (arguments != null) {
        town = arguments.getString(TownsFragment.TOWN_NAME);
        isWind = arguments.getBoolean(TownsFragment.SWITCH_WIND);
        isWet = arguments.getBoolean(TownsFragment.SWITCH_WET);
        isPressure = arguments.getBoolean(TownsFragment.SWITCH_PRESSURE);
        uriTown = arguments.getString(TownsFragment.URI_TOWN);

    }*/

    private boolean sw1;
    private final String cityName;

    Parcel(boolean sw1, String cityName) {
        this.sw1 = sw1;
        this.cityName = cityName;
    }

    boolean getSw1() {
        return sw1;
    }

    String getCityName() {
        return cityName;
    }
}

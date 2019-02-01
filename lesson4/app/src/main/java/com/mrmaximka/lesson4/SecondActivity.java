package com.mrmaximka.lesson4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] precipitationList = {getString(R.string.sky_1), getString(R.string.sky_2), getString(R.string.sky_3), getString(R.string.sky_4)};  // Лист с осадками

        Bundle arguments = getIntent().getExtras();         // Получаем элементы из первой активити
        String town = null;
        if (arguments != null) {
            town = arguments.getString("townName");
        }
        boolean isWind = false;
        if (arguments != null) {
            isWind = arguments.getBoolean("switchWind");
        }
        boolean isWet = false;
        if (arguments != null) {
            isWet = arguments.getBoolean("switchWet");
        }
        boolean isPressure = false;
        if (arguments != null) {
            isPressure = arguments.getBoolean("switchPressure");
        }

        TextView secondTitle = findViewById(R.id.townName);         // Объявляем все TextView т.к. меняться будут программно
        TextView windTxt = findViewById(R.id.wind);
        TextView wetTxt = findViewById(R.id.wet);
        TextView pressureTxt = findViewById(R.id.pressure);
        TextView temperatureTxt = findViewById(R.id.temperature);
        TextView precipitation = findViewById(R.id.precipitation);

        secondTitle.setText(String.format(getString(R.string.second_title), town));
        precipitation.setText(precipitationList[0]);

        int n = 5;
        temperatureTxt.setText(String.format(getString(R.string.temperature_txt), n));
        windTxt.setText(String.format(getString(R.string.wind_txt), n));
        wetTxt.setText(String.format(getString(R.string.wet_txt), n));
        pressureTxt.setText(String.format(getString(R.string.pressure_txt), n));

        if (isWind) windTxt.setVisibility(View.VISIBLE);
        if (isWet) wetTxt.setVisibility(View.VISIBLE);
        if (isPressure) pressureTxt.setVisibility(View.VISIBLE);

    }
}

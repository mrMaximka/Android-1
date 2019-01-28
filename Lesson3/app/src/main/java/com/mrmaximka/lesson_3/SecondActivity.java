package com.mrmaximka.lesson_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    String[] precipitationList = {"Ясно", "Облачно", "Дождь", "Снег"};  // Лист с осадками

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();         // Получаем элементы из первой активити
        String town = arguments.getString("townName");
        boolean isWind = arguments.getBoolean("switchWind");
        boolean isWet = arguments.getBoolean("switchWet");
        boolean isPressure = arguments.getBoolean("switchPressure");

        TextView secondTitle = findViewById(R.id.townName);         // Объявляем все TextView т.к. меняться будут программно
        TextView windTxt = findViewById(R.id.wind);
        TextView wetTxt = findViewById(R.id.wet);
        TextView pressureTxt = findViewById(R.id.pressure);
        TextView temperatureTxt = findViewById(R.id.temperature);
        TextView precipitation = findViewById(R.id.precipitation);

        secondTitle.setText("Погода в городе\n" + town);
        precipitation.setText(precipitationList[0]);

        int n = 5;
        temperatureTxt.setText("+" + n + "°");
        windTxt.setText("Ветер:\t\t" + n + " м/с");
        wetTxt.setText("Влажность:\t\t" + n + "%");
        pressureTxt.setText("Давление:\t\t" + n + " мм. рт. ст.");

        if (isWind) windTxt.setVisibility(View.VISIBLE);
        if (isWet) wetTxt.setVisibility(View.VISIBLE);
        if (isPressure) pressureTxt.setVisibility(View.VISIBLE);

    }
}


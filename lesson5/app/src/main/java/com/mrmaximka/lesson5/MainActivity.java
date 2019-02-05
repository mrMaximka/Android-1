package com.mrmaximka.lesson5;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String TOWN_NAME = "townName";
    public static final String SWITCH_WIND = "switchWind";
    public static final String SWITCH_WET = "switchWet";
    public static final String SWITCH_PRESSURE = "switchPressure";
    public static final String URI_TOWN = "UriTown";
    private Spinner townList;       // Выпадающий список
    String[] towns;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);    // Открываем второе активити
            intent.putExtra(TOWN_NAME, getTown());                 // Передаем туда название города
            intent.putExtra(SWITCH_WIND, getSwWind());             // И все switch
            intent.putExtra(SWITCH_WET, getSwWet());
            intent.putExtra(SWITCH_PRESSURE, getSwPressure());
            intent.putExtra(URI_TOWN, getTownID());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        towns = getResources().getStringArray(R.array.towns); // Его элементы

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, towns); // Адаптер на spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button button = findViewById(R.id.to_second_btn);
        button.setOnClickListener(onClickListener);

        townList = findViewById(R.id.towns);
        townList.setAdapter(adapter);
        townList.setSelection(0);       // Выбран 0й элемент по умолчанию
    }

    public String getTown(){        // Гетеры для интента
        return (String) townList.getSelectedItem();
    }

    public Boolean getSwWind(){
        Switch sw = findViewById(R.id.wind_switch);
        return sw.isChecked();
    }

    public Boolean getSwWet(){
        Switch sw = findViewById(R.id.wet_switch);
        return sw.isChecked();
    }

    public Boolean getSwPressure(){
        Switch sw = findViewById(R.id.pressure_switch);
        return sw.isChecked();
    }

    public String getTownID(){        // При любой локали нужно вытащить слово на английском языке для uri
        int townID = (int) townList.getSelectedItemId();
        Locale firstLocale = Locale.getDefault();
        Locale locale = new Locale("en");       // Поэтому меням язык на англ
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);

        String[] enTowns = getResources().getStringArray(R.array.towns);

        String UriTown = enTowns[townID];       // Берем слово

        Locale.setDefault(firstLocale);             // Возвращаем локаль
        Configuration backConfiguration = new Configuration();
        backConfiguration.locale = firstLocale;
        getBaseContext().getResources().updateConfiguration(backConfiguration, null);
        return UriTown;
    }
}

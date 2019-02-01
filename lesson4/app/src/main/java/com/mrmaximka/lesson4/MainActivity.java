package com.mrmaximka.lesson4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Spinner townList;       // Выпадающий список

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);    // Открываем второе активити
            intent.putExtra("townName", getTown());                 // Передаем туда название города
            intent.putExtra("switchWind", getSwWind());             // И все switch
            intent.putExtra("switchWet", getSwWet());
            intent.putExtra("switchPressure", getSwPressure());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] towns = {getString(R.string.town_1), getString(R.string.town_2), getString(R.string.town_3)}; // Его элементы

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
}

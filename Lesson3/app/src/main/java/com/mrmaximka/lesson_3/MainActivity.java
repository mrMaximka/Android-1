package com.mrmaximka.lesson_3;

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
    String[] towns = {"Москва", "Санкт-Петербург", "Мурманск"}; // Его элементы

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, towns); // Адаптер на spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button button = findViewById(R.id.toSecondBtn);
        button.setOnClickListener(onClickListener);

        townList = findViewById(R.id.spTownList);
        townList.setAdapter(adapter);
        townList.setSelection(0);       // Выбран 0й элемент по умолчанию
    }

    public String getTown(){        // Гетеры для интента
        return (String) townList.getSelectedItem();
    }

    public Boolean getSwWind(){
        Switch sw = findViewById(R.id.swWind);
        return sw.isChecked();
    }

    public Boolean getSwWet(){
        Switch sw = findViewById(R.id.swWet);
        return sw.isChecked();
    }

    public Boolean getSwPressure(){
        Switch sw = findViewById(R.id.swPressure);
        return sw.isChecked();
    }
}

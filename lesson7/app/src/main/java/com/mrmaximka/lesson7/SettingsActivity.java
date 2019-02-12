package com.mrmaximka.lesson7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CheckBox swWind;
        CheckBox swWet;
        CheckBox swPressure;

        swWind = findViewById(R.id.swWind);
        swWet = findViewById(R.id.swWet);
        swPressure = findViewById(R.id.swPressure);

        swWind.setChecked(FirstFragment.getSwWind());
        swWet.setChecked(FirstFragment.getSwWet());
        swPressure.setChecked(FirstFragment.getSwPressure());

        swWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    FirstFragment.setSwWind(true);
                }
                else{
                    FirstFragment.setSwWind(false);
                }
            }
        });

        swWet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    FirstFragment.setSwWet(true);
                }
                else{
                    FirstFragment.setSwWet(false);
                }
            }
        });

        swPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    FirstFragment.setSwPressure(true);
                }
                else{
                    FirstFragment.setSwPressure(false);
                }
            }
        });
    }
}

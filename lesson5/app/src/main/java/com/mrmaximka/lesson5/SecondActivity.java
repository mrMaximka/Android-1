package com.mrmaximka.lesson5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    String uriTown = null;
    String town = null;
    boolean isWind = false;
    boolean isWet = false;
    boolean isPressure = false;
    TextView precipitation;
    int n = 5;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(getString(R.string.uri_txt) + uriTown); // Ссылка на погоду выбранного города
            intent.setData(uri);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }
            else {
                Toast.makeText(SecondActivity.this, getString(R.string.app_not_found), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener onClickListenerShare = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String message = String.format(getString(R.string.message), town, n, precipitation.getText());
            if (isWind) message += String.format(getString(R.string.wind_txt), n);
            if (isWet) message += String.format(getString(R.string.wet_txt), n);
            if (isPressure) message += String.format(getString(R.string.pressure_txt), n);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("mailto:");
            intent.setData(uri);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }
            else {
                Toast.makeText(SecondActivity.this, getString(R.string.app_not_found), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] precipitationList = getResources().getStringArray(R.array.sky);  // Лист с осадками

        Bundle arguments = getIntent().getExtras();         // Получаем элементы из первой активити

        if (arguments != null) {
            town = arguments.getString(MainActivity.TOWN_NAME);
            isWind = arguments.getBoolean(MainActivity.SWITCH_WIND);
            isWet = arguments.getBoolean(MainActivity.SWITCH_WET);
            isPressure = arguments.getBoolean(MainActivity.SWITCH_PRESSURE);
            uriTown = arguments.getString(MainActivity.URI_TOWN);

        }

        TextView secondTitle = findViewById(R.id.townName);         // Объявляем все TextView т.к. меняться будут программно
        TextView windTxt = findViewById(R.id.wind);
        TextView wetTxt = findViewById(R.id.wet);
        TextView pressureTxt = findViewById(R.id.pressure);
        TextView temperatureTxt = findViewById(R.id.temperature);
        precipitation = findViewById(R.id.precipitation);
        Button inBrowserBtn = findViewById(R.id.inBrowser);
        ImageButton shareBtn = findViewById(R.id.share);

        inBrowserBtn.setOnClickListener(onClickListener);
        shareBtn.setOnClickListener(onClickListenerShare);

        secondTitle.setText(String.format(getString(R.string.second_title), town));
        precipitation.setText(precipitationList[0]);

        temperatureTxt.setText(String.format(getString(R.string.temperature_txt), n));
        windTxt.setText(String.format(getString(R.string.wind_txt), n));
        wetTxt.setText(String.format(getString(R.string.wet_txt), n));
        pressureTxt.setText(String.format(getString(R.string.pressure_txt), n));

        if (isWind) windTxt.setVisibility(View.VISIBLE);
        if (isWet) wetTxt.setVisibility(View.VISIBLE);
        if (isPressure) pressureTxt.setVisibility(View.VISIBLE);

    }
}

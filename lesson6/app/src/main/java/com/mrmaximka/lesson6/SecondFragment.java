package com.mrmaximka.lesson6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public static final String PARCEL = "parcel";

    String uriTown = null;
    String town = null;
    boolean isWind = false;
    boolean isWet = false;
    boolean isPressure = false;
    TextView precipitation;
    int n = 5;

    /*private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(getString(R.string.uri_txt) + uriTown); // Ссылка на погоду выбранного города
            intent.setData(uri);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }
            else {
                Toast.makeText(getActivity(), getString(R.string.app_not_found), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), getString(R.string.app_not_found), Toast.LENGTH_SHORT).show();
            }
        }
    };*/

    // фабричный метод, создает фрагмент и передадет параметр
    public static SecondFragment init(Parcel parcel) {
        SecondFragment f = new SecondFragment();    // создание

        // передача параметра
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_second, container, false);

        TextView secondTitle = layout.findViewById(R.id.townName);         // Объявляем все TextView т.к. меняться будут программно
        TextView windTxt = layout.findViewById(R.id.wind);
        TextView wetTxt = layout.findViewById(R.id.wet);
        TextView pressureTxt = layout.findViewById(R.id.pressure);
        TextView temperatureTxt = layout.findViewById(R.id.temperature);
        precipitation = layout.findViewById(R.id.precipitation);
        Button inBrowserBtn = layout.findViewById(R.id.inBrowser);
        ImageButton shareBtn = layout.findViewById(R.id.share);

//        inBrowserBtn.setOnClickListener(onClickListener);
//        shareBtn.setOnClickListener(onClickListenerShare);

        Parcel parcel = getParcel();


        secondTitle.setText(String.format(getString(R.string.second_title), parcel.getCityName()));
//        precipitation.setText(precipitationList[0]);

        temperatureTxt.setText(String.format(getString(R.string.temperature_txt), n));
        windTxt.setText(String.format(getString(R.string.wind_txt), n));
        wetTxt.setText(String.format(getString(R.string.wet_txt), n));
        pressureTxt.setText(String.format(getString(R.string.pressure_txt), n));

        if (parcel.getSw1()) windTxt.setVisibility(View.VISIBLE);
        if (isWet) wetTxt.setVisibility(View.VISIBLE);
        if (isPressure) pressureTxt.setVisibility(View.VISIBLE);


        return layout; // Вместо макета используем сразу картинку
    }

    public Parcel getParcel() {
        Parcel parcel = (Parcel) getArguments().getSerializable(PARCEL);
        return parcel;
    }

}
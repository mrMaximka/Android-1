package com.mrmaximka.lesson6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class TownsFragment extends ListFragment {

    com.mrmaximka.lesson6.Parcel currentParcel;


    public static final String TOWN_NAME = "townName";
    public static final String SWITCH_WIND = "switchWind";
    public static final String SWITCH_WET = "switchWet";
    public static final String SWITCH_PRESSURE = "switchPressure";
    public static final String URI_TOWN = "UriTown";
//    private Spinner townList;       // Выпадающий список
//    Button button;
//    TextView firstTitle;
    Switch sw1;
    Switch sw2;
    Switch sw3;
    String[] towns;

    /*private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SecondActivity.class);    // Открываем второе активити
            intent.putExtra(TOWN_NAME, getTown());                 // Передаем туда название города
            intent.putExtra(SWITCH_WIND, getSwWind());             // И все switch
            intent.putExtra(SWITCH_WET, getSwWet());
            intent.putExtra(SWITCH_PRESSURE, getSwPressure());
            intent.putExtra(URI_TOWN, getTownID());
            startActivity(intent);
        }
    };*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

//        townList = view.findViewById(R.id.towns);
//        button = view.findViewById(R.id.to_second_btn);
//        firstTitle = view.findViewById(R.id.first_title);
        sw1 = view.findViewById(R.id.wind_switch);
        sw2 = view.findViewById(R.id.wet_switch);
        sw3 = view.findViewById(R.id.pressure_switch);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        towns = getResources().getStringArray(R.array.towns); // Его элементы

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.towns,
                android.R.layout.simple_list_item_activated_1);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.towns, android.R.layout.simple_spinner_dropdown_item); // Адаптер на spinner
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        setListAdapter(adapter);

//        button.setOnClickListener(onClickListener);

//        townList.setAdapter(adapter);
//        townList.setSelection(0);       // Выбран 0й элемент по умолчанию
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView cityNameView = (TextView) v;
//        currentParcel = new com.mrmaximka.lesson6.Parcel(getSwWind(), cityNameView.getText().toString());

        showCoatOfArms(getParcel());
    }

    public Parcel getParcel(){
        currentParcel = new Parcel(getSwWind(), "");
        return currentParcel;
    }

    private void showCoatOfArms(Parcel parcel) {

        Intent intent = new Intent();
        currentParcel = new Parcel(getSwWind(), "1");
        intent.setClass(getActivity(), SecondActivity.class);
        intent.putExtra(SecondFragment.PARCEL, parcel);
        startActivity(intent);
    }


//    public String getTown(){        // Гетеры для интента
//        return (String) townList.getSelectedItem();
//    }

    public Boolean getSwWind(){

        return sw1.isChecked();
    }

    public Boolean getSwWet(){

        return sw2.isChecked();
    }

    public Boolean getSwPressure(){

        return sw3.isChecked();
    }

    /*public String getTownID(){        // При любой локали нужно вытащить слово на английском языке для uri
        int townID = (int) townList.getSelectedItemId();
        Locale firstLocale = Locale.getDefault();
        Locale locale = new Locale("en");       // Поэтому меням язык на англ
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
//        getBaseContext().getResources().updateConfiguration(configuration, null);

        String[] enTowns = getResources().getStringArray(R.array.towns);

        String UriTown = enTowns[townID];       // Берем слово

        Locale.setDefault(firstLocale);             // Возвращаем локаль
        Configuration backConfiguration = new Configuration();
        backConfiguration.locale = firstLocale;
//        getBaseContext().getResources().updateConfiguration(backConfiguration, null);
        return UriTown;
    }*/
}

package com.mrmaximka.lesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

    private boolean isExistCoatofarms;  // Возможность поместить 2 фрагмента
    private Parcel currentParcel;
    static boolean swWind;      // Включен ли checkBox с ветром
    static boolean swWet;       // Влажностью
    static boolean swPressure;  // И давлением


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private OnClick onClickListener = new OnClick() {
        @Override
        public void onListItemClick(int position, String city) {
            currentParcel = new Parcel(position, city);
            showCoatOfArms(currentParcel);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] cities = getActivity().getResources().getStringArray(R.array.Cities);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        CityAdapter adapter = new CityAdapter(cities, onClickListener);
        recyclerView.setAdapter(adapter);
        swWind = false;
        swWet = false;
        swPressure = false;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExistCoatofarms = getActivity().findViewById(R.id.coat_of_arms) != null;

        if (savedInstanceState != null) {   // Создавалась ли уже активити
            currentParcel = (Parcel) savedInstanceState.getSerializable("CurrentCity");
            swWind = (boolean) savedInstanceState.getSerializable("WindKey");
            swWet = (boolean) savedInstanceState.getSerializable("WetKey");
            swPressure = (boolean) savedInstanceState.getSerializable("PressureKey");
        } else {
            currentParcel = new Parcel(0, getResources().getTextArray(R.array.Cities)[0].toString());
        }

        // Если есть место по 2 фрагмента, то рисуем
        if (isExistCoatofarms) {
            showCoatOfArms(currentParcel);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("CurrentCity", currentParcel);     // Сохраним позицию
        outState.putSerializable("WindKey", swWind);        // И чекбоксы
        outState.putSerializable("WetKey", swWet);
        outState.putSerializable("PressureKey", swPressure);
    }

    // Отрисовка второго фрагмента
    private void showCoatOfArms(Parcel parcel) {
        if (isExistCoatofarms) {        // Если можно отобразить 2 фрагмента

            SecondFragment secondFragment = (SecondFragment)
                    getFragmentManager().findFragmentById(R.id.coat_of_arms);

            if (secondFragment == null || secondFragment.getParcel().getImageIndex() != parcel.getImageIndex()) {


                secondFragment = SecondFragment.init(parcel);


                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, secondFragment);
                ft.commit();
            }
        } else {    // Иначе вторая активити
            Intent intent = new Intent();
            intent.setClass(getActivity(), SecondActivity.class);
            intent.putExtra(SecondFragment.PARCEL, parcel);
            startActivity(intent);
        }
    }

    public static void setSwWind(boolean value){
        swWind = value;
    }       // Гетеры и сетеры на чекбоксы

    public static boolean getSwWind(){
        return swWind;
    }

    public static void setSwWet(boolean value){
        swWet = value;
    }

    public static boolean getSwWet(){
        return swWet;
    }

    public static void setSwPressure(boolean value){
        swPressure = value;
    }

    public static boolean getSwPressure(){
        return swPressure;
    }

    interface OnClick {

        void onListItemClick(int position, String city);
    }
}

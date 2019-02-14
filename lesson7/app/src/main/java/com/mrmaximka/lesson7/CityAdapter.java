package com.mrmaximka.lesson7;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Aleksandr Anikin
 */
class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final String[] cities;
    private FirstFragment.OnClick onClickListener;

    CityAdapter(String[] cities, FirstFragment.OnClick onClickListener) {
        this.cities = cities;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_item, viewGroup, false);
        CityViewHolder cityViewHolder = new CityViewHolder(textView);
        return cityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.textView.setText(cities[i]);
    }

    @Override
    public int getItemCount() {
        return cities.length;
    }


    class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        CityViewHolder(@NonNull final TextView textView) {
            super(textView);
            this.textView = textView;
            this.textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onClickListener.onListItemClick(getLayoutPosition(), cities[getLayoutPosition()]);
                }
            });
        }
    }
}

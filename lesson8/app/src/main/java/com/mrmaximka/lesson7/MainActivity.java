package com.mrmaximka.lesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton settings = findViewById(R.id.add_button);
        ImageButton confirm = findViewById(R.id.confirm_town);
        final TextInputEditText newTown = findViewById(R.id.new_town_edit_text);

        final FrameLayout addLayout = findViewById(R.id.add_layout);
        final FrameLayout confirmLayout = findViewById(R.id.confirm_layout);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout.setVisibility(View.GONE);
                confirmLayout.setVisibility(View.VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstFragment.addTown(newTown.getText());
                newTown.setText("");
                addLayout.setVisibility(View.VISIBLE);
                confirmLayout.setVisibility(View.GONE);
            }
        });
    }
}

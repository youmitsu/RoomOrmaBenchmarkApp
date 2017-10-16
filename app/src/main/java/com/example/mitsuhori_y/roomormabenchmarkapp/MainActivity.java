package com.example.mitsuhori_y.roomormabenchmarkapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mitsuhori_y.roomormabenchmarkapp.orma.OrmaRepository;

public class MainActivity extends AppCompatActivity {

    Button roomBtn;
    Button ormaBtn;
    TextView roomResult;
    TextView ormaResult;

    OrmaRepository ormaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ormaRepository = new OrmaRepository(this);

        roomBtn = findViewById(R.id.room_btn);
        roomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ormaBtn = findViewById(R.id.orma_btn);
        ormaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ormaRepository.insertOne();
                ormaResult.setText(String.valueOf(ormaRepository.getBenchMarkTime()));
            }
        });

    }
}

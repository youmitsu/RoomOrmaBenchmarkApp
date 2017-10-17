package com.example.mitsuhori_y.roomormabenchmarkapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mitsuhori_y.roomormabenchmarkapp.orma.OrmaRepository;
import com.example.mitsuhori_y.roomormabenchmarkapp.orma.OrmaUserEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        ormaRepository = new OrmaRepository(this, new BenchMarker());

        ormaResult = findViewById(R.id.orma_result);
        roomResult = findViewById(R.id.room_result);

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
                OrmaUserEntity user = new OrmaUserEntity("hoge@hoge.com", "hoge", "1", "15");
                ormaRepository.getUserInsertSingle(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            ormaResult.setText(String.valueOf(s));
                        }, e -> {
                            Toast t = Toast.makeText(MainActivity.this, "なにがしかのエラー", Toast.LENGTH_SHORT);
                            t.show();
                        });
            }
        });

    }
}

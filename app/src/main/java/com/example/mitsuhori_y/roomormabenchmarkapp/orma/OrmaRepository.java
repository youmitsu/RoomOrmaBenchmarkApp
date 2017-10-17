package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.BenchMarker;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class OrmaRepository {
    private final OrmaDatabase ormaDB;
    private BenchMarker benchMarker;

    public OrmaRepository(Context context) {
        this.benchMarker = new BenchMarker();
        this.ormaDB = OrmaDatabase.builder(context)
                .build();
    }

    public void insertOne() {
        OrmaUserEntity user = new OrmaUserEntity("hoge@hoge.com", "hoge", "1", "15");
        BenchMarker benchMarker = new BenchMarker();
        benchMarker.startBenchMark(System.currentTimeMillis());
        getUserInsertSingle(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {

                }, e -> {

                });
        benchMarker.endBenchMark(System.currentTimeMillis());
    }

    private Single<Long> getUserInsertSingle(OrmaUserEntity user) {
        return Single.create(s -> {
            try {
                long result = ormaDB.insertIntoOrmaUserEntity(user);
                s.onSuccess(result);
            } catch (Exception e) {
                s.onError(e);
            }
        });
    }

    public long getBenchMarkTime() {
        return benchMarker.result();
    }
}

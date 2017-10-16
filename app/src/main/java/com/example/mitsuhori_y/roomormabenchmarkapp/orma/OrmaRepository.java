package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.BenchMarker;

import io.reactivex.Single;

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
        ormaDB.insertIntoOrmaUserEntity(user);
        benchMarker.endBenchMark(System.currentTimeMillis());
    }

    private Single<OrmaUserEntity> getUserInsertSingle() {
    }

    public long getBenchMarkTime() {
        return benchMarker.result();
    }
}

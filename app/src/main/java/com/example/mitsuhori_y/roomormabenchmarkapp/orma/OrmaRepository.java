package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.RandomGenerater;

import io.reactivex.Single;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class OrmaRepository {
    private final OrmaDatabase ormaDB;
    private final RandomGenerater generater;

    public OrmaRepository(Context context) {
        this.ormaDB = OrmaDatabase.builder(context)
                .build();
        this.generater = new RandomGenerater();
    }

    public Single<Long> getUserInsertSingle(BenchMarker benchMarker) {
        OrmaUserEntity user = new OrmaUserEntity(generater.getRandom(), "hoge", "1", "15");
        return Single.create(s -> {
            try {
                benchMarker.startBenchMark();
                long result = ormaDB.insertIntoOrmaUserEntity(user);
                benchMarker.endBenchMark();
                s.onSuccess(benchMarker.result());
            } catch (Exception e) {
                s.onError(e);
            }
        });
    }

}

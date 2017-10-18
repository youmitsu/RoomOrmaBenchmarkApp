package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;

import io.reactivex.Single;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class OrmaRepository {
    private final OrmaDatabase ormaDB;

    public OrmaRepository(Context context) {
        this.ormaDB = OrmaDatabase.builder(context)
                .build();
    }

    public Single<Long> getUserInsertSingle(OrmaUserEntity user, BenchMarker benchMarker) {
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

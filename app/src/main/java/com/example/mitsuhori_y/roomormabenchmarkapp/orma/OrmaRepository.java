package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.BaseRepository;
import com.example.mitsuhori_y.roomormabenchmarkapp.R;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.RandomGenerater;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.TestType;
import com.github.gfx.android.orma.Inserter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class OrmaRepository extends BaseRepository {
    private Context context;
    private final OrmaDatabase ormaDB;
    private final RandomGenerater generater;

    public OrmaRepository(Context context) {
        this.context = context;
        this.ormaDB = OrmaDatabase.builder(context)
                .build();
        this.generater = new RandomGenerater();
    }

    @Override
    public Single<Long> getDaoMethod(TestType type, BenchMarker benchMarker) {
        Single<Long> single = null;
        switch (type) {
            case INSERT_SINGLE:
                single = getUserInsertSingle(benchMarker);
                break;
            case INSERT_100:
                single = getUsersInsert100Single(benchMarker);
                break;
        }
        return single;
    }

    public Single<Long> getUserInsertSingle(BenchMarker benchMarker) {
        OrmaUserEntity user = new OrmaUserEntity(generater.getRandom(), context.getString(R.string.sample_name), context.getString(R.string.sample_gender), context.getString(R.string.sample_age));
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

    private Single<Long> getUsersInsert100Single(BenchMarker benchMarker) {
        List<OrmaUserEntity> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrmaUserEntity user = new OrmaUserEntity(generater.getRandom(), context.getString(R.string.sample_name), context.getString(R.string.sample_gender), context.getString(R.string.sample_age));
            users.add(user);
        }
        return Single.create(s -> {
            Inserter<OrmaUserEntity> inserter = ormaDB.prepareInsertIntoOrmaUserEntity();
            inserter.executeAllAsObservable(users)
                    .subscribeOn(Schedulers.io())
                    .subscribe(n -> {
                    }, e -> {
                        s.onError(e);
                    }, () -> {
                        benchMarker.endBenchMark();
                        s.onSuccess(benchMarker.result());
                    }, su -> {
                        benchMarker.startBenchMark();
                    });
        });
    }
}

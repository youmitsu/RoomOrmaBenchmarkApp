package com.example.mitsuhori_y.roomormabenchmarkapp.room;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.App;
import com.example.mitsuhori_y.roomormabenchmarkapp.BaseRepository;
import com.example.mitsuhori_y.roomormabenchmarkapp.R;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.RandomGenerater;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.TestType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

public class RoomRepository extends BaseRepository {
    private Context context;
    private final RmDatabase rmDatabase = App.get().getRoomDB();
    private final RandomGenerater generater;

    public RoomRepository(Context context, RandomGenerater generater) {
        this.context = context;
        this.generater = generater;
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

    private Single<Long> getUserInsertSingle(BenchMarker benchMarker) {
        RoomUserEntity roomUserEntity = new RoomUserEntity(generater.getRandom(), context.getString(R.string.sample_name), context.getString(R.string.sample_gender), context.getString(R.string.sample_age));
        return Single.create(s -> {
            try {
                benchMarker.startBenchMark();
                rmDatabase.roomUserDao().putUser(roomUserEntity);
                benchMarker.endBenchMark();
                s.onSuccess(benchMarker.result());
            } catch (Exception e) {
                s.onError(e);
            }
        });
    }

    private Single<Long> getUsersInsert100Single(BenchMarker benchMarker) {
        List<RoomUserEntity> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RoomUserEntity user = new RoomUserEntity(generater.getRandom(), context.getString(R.string.sample_name), context.getString(R.string.sample_gender), context.getString(R.string.sample_age));
            users.add(user);
        }
        return Single.create(s -> {
            try {
                benchMarker.startBenchMark();
                rmDatabase.roomUserDao().putUsers100(users);
                benchMarker.endBenchMark();
                s.onSuccess(benchMarker.result());
            } catch (Exception e) {
                s.onError(e);
            }
        });
    }
}

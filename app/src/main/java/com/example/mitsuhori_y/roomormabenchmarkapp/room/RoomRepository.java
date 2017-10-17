package com.example.mitsuhori_y.roomormabenchmarkapp.room;

import android.content.Context;

import com.example.mitsuhori_y.roomormabenchmarkapp.App;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;

import io.reactivex.Single;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

public class RoomRepository {
    private BenchMarker benchMarker;
    private Context context;
    private final RmDatabase rmDatabase = App.get().getRoomDB();

    public RoomRepository(Context context, BenchMarker marker) {
        this.benchMarker = marker;
        this.context = context;
    }

    public Single<Long> getUserInsertSingle(RoomUserEntity user) {
        return Single.create(s -> {
            try {
                benchMarker.startBenchMark(System.currentTimeMillis());
                rmDatabase.roomUserDao().putUser(user);
                benchMarker.endBenchMark(System.currentTimeMillis());
                s.onSuccess(benchMarker.result());
            } catch (Exception e) {
                s.onError(e);
            }
        });
    }
}

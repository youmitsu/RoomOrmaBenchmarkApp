package com.example.mitsuhori_y.roomormabenchmarkapp;

import com.example.mitsuhori_y.roomormabenchmarkapp.util.BenchMarker;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.TestType;

import io.reactivex.Single;

/**
 * Created by yumitsuhori on 2017/10/20.
 */

public abstract class BaseRepository {
    public abstract Single<Long> getDaoMethod(TestType type, BenchMarker benchMarker);
}

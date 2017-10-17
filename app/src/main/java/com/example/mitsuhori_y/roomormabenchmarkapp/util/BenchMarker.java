package com.example.mitsuhori_y.roomormabenchmarkapp.util;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class BenchMarker {
    long start;
    long end;

    public BenchMarker() {
    }

    public void startBenchMark(long start) {
        this.start = start;
    }

    public void endBenchMark(long end) {
        this.end = end;
    }

    public long result() {
        return end - start;
    }
}

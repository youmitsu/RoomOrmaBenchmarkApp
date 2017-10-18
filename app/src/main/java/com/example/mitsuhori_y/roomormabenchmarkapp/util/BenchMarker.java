package com.example.mitsuhori_y.roomormabenchmarkapp.util;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

public class BenchMarker {
    long start;
    long end;

    public BenchMarker() {
    }

    public void startBenchMark() {
        this.start = System.nanoTime();
    }

    public void endBenchMark() {
        this.end = System.nanoTime();
    }

    public long result() {
        return end - start;
    }
}

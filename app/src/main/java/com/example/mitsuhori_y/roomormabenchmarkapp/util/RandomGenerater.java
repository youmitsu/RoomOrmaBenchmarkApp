package com.example.mitsuhori_y.roomormabenchmarkapp.util;

import java.util.Random;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

public class RandomGenerater {
    private Random generater;

    public RandomGenerater() {
        this.generater = new Random(System.currentTimeMillis());
    }

    public String getRandom() {
        return String.valueOf(generater.nextDouble());
    }

}

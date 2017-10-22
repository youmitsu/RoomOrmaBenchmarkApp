package com.example.mitsuhori_y.roomormabenchmarkapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumitsuhori on 2017/10/20.
 */

public enum TestType {
    INSERT_SINGLE(0),
    INSERT_100(1),
    SELECT_ALL(2),
    UNKNOWN(99);

    private final int value;

    private TestType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TestType fromInteger(int i) {
        return fromInt(i);
    }

    private static final Map<Integer, TestType> intToTestTypeMap = new HashMap<>();

    static {
        for (TestType type : TestType.values()) {
            intToTestTypeMap.put(type.value, type);
        }
    }

    public static TestType fromInt(int i) {
        TestType type = intToTestTypeMap.get(Integer.valueOf(i));
        if (type == null) {
            return TestType.UNKNOWN;
        }
        return type;
    }
}

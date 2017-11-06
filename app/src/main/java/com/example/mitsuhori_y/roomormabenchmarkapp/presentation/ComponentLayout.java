package com.example.mitsuhori_y.roomormabenchmarkapp.presentation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mitsuhori_y.roomormabenchmarkapp.R;
import com.example.mitsuhori_y.roomormabenchmarkapp.orma.OrmaRepository;
import com.example.mitsuhori_y.roomormabenchmarkapp.room.RoomRepository;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.RandomGenerater;
import com.example.mitsuhori_y.roomormabenchmarkapp.util.TestType;

/**
 * Created by mitsuhori_y on 2017/10/18.
 */

public class ComponentLayout extends RelativeLayout {
    private TextView title;
    private TextView roomResult;
    private TextView ormaResult;
    private TestType testType;

    private RoomRepository roomRepository;
    private OrmaRepository ormaRepository;

    public ComponentLayout(Context context) {
        super(context);
    }

    public ComponentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTestType(context, attrs);
        initView(context);
    }

    public ComponentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTestType(context, attrs);
        initView(context);
    }

    private void initTestType(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.ComponentLayout, 0, 0);
        try {
            int type = arr.getInteger(R.styleable.ComponentLayout_TestType, 0);
            testType = TestType.fromInteger(type);
        } finally {
            arr.recycle();
        }
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.component, this);
        roomRepository = new RoomRepository(context, new RandomGenerater());
        ormaRepository = new OrmaRepository(context);

        title = findViewById(R.id.title);
        ormaResult = findViewById(R.id.orma_result);
        roomResult = findViewById(R.id.room_result);

        title.setText(getTitle());
    }

    private String getTitle() {
        String t = null;
        switch (testType) {
            case INSERT_SINGLE:
                t = getContext().getString(R.string.insert_single);
                break;
            case INSERT_100:
                t = getContext().getString(R.string.insert_100);
                break;
            case SELECT_ALL:
                t = getContext().getString(R.string.select_all);
        }
        return t;
    }

}

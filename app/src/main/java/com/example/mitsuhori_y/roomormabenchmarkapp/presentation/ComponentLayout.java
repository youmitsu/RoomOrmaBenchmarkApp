package com.example.mitsuhori_y.roomormabenchmarkapp.presentation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mitsuhori_y.roomormabenchmarkapp.R;

/**
 * Created by mitsuhori_y on 2017/10/18.
 */

public class ComponentLayout extends RelativeLayout {
    public ComponentLayout(Context context) {
        super(context);
        init(context);
    }

    public ComponentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComponentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.component, this);
    }

}

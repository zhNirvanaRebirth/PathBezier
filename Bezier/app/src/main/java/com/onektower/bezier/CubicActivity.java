package com.onektower.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

/**
 * Created by zhwilson on 2016/11/30.
 * 三阶贝塞尔曲线
 */
public class CubicActivity extends AppCompatActivity {
    private CubicBezier cubic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic);
        init();
    }

    private void init() {
        cubic = (CubicBezier) findViewById(R.id.cubic);
        RadioGroup control = (RadioGroup) findViewById(R.id.control);
        control.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.control_one:
                        cubic.setControlPoint(CubicBezier.CONTROL_POINT_ONE);
                        break;
                    case R.id.control_two:
                        cubic.setControlPoint(CubicBezier.CONTROL_POINT_TWO);
                        break;
                }
            }
        });
    }
}

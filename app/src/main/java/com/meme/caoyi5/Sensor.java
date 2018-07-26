package com.meme.caoyi5;

import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private android.hardware.Sensor mSensor;

    private TextView mTextView_stepc = null;
    private String stepc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        //定义xyz内容显示框TextView
        mTextView_stepc = (TextView) findViewById(R.id.textViews_stepc);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onStop(){   //同样在退出activity时要注销监听
        super.onStop();
        /*if(mSensorManager != null){
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }*/
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];

        //将float类型的数据转为字符型供textView显示
        stepc = String.valueOf((int) x);
        mTextView_stepc.setText(stepc);
        mTextView_stepc.setTextSize(100);
        mTextView_stepc.setTextColor(Color.rgb(249, 204, 229));

    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
    }
}

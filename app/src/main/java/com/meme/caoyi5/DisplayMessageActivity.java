package com.meme.caoyi5;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DisplayMessageActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView mTextView_x = null;
    private TextView mTextView_y = null;
    private TextView mTextView_z = null;
    private String sX,sY,sZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(60);
        textView.setText(message);

        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);


        //定义xyz内容显示框TextView
        mTextView_x = (TextView) findViewById(R.id.textView_x);
        mTextView_y = (TextView) findViewById(R.id.textView_y);
        mTextView_z = (TextView) findViewById(R.id.textView_z);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onStop(){   //同样在退出activity时要注销监听
        super.onStop();
        if(mSensorManager != null){
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        //获得值之后，你就可以进行相应的处理啦
        System.out.println("X="+x);
        System.out.println("Y="+y);
        System.out.println("Z="+z);

        //将float类型的数据转为字符型供textView显示
        sX = String.valueOf(x);
        sY = String.valueOf(y);
        sZ = String.valueOf(z);

        mTextView_x.setText(sX);
        mTextView_y.setText(sY);
        mTextView_z.setText(sZ);


        TextView textView = new TextView(this);
        textView.setTextSize(60);
        textView.setX(x);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


}

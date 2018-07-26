package com.meme.caoyi5;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.meme.caoyi5.MESSAGE";

    private Button btn_open;
    private boolean status=false;
    private CameraManager cm;

    MainActivity instance;

    private Button btn_SOS;

    private Button btn_sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            //Log.i("TEST","Granted");
            //init(barcodeScannerView, getIntent(), null);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);//1 can be another integer
        }
        // check Android 6 permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED ) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
        }



        instance=this;
        btn_open= (Button) findViewById(R.id.btn_open);
        cm= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String [] camerList = cm.getCameraIdList();
            for (String str:camerList
                    ) {
                Log.d("List",str);
            }
        } catch (CameraAccessException e) {
            Log.e("error",e.getMessage());
        }


        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("---------onClick");
                if(!status){
                    btn_open.setText("么么迷魂灯  [关闭]");
                    status=true;
                        new Thread(new TurnOnLight()).start();
                }else{
                    btn_open.setText("么么迷魂灯  [开启]");
                    status=false;

                    try {
                        cm.setTorchMode("0", false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn_SOS=(Button)findViewById(R.id.btn_SOS);
        btn_SOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick btn_SOS");
                    //让可以去打电话的应用去帮我完成这个事情
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    //打电话具体需要的数据
                    intent.setData(Uri.parse("tel://18659259926"));
                    //开始这个企图
                    startActivity(intent);
            }
        });

        btn_sensor=(Button)findViewById(R.id.btn_sensor);
        btn_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick btn_sensor");
                Intent intent = new Intent(MainActivity.this, Sensor.class);
                startActivity(intent);
            }
        });

    }



        public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private class TurnOnLight implements Runnable {
        @Override
        public void run() {
            System.out.println("---------TurnOnLight.run()");
            try {
                cm.setTorchMode("0", true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

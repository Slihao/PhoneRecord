package com.slh.phonerecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Service;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    PhoneReceiver mPhoneReceiver;
    public static final int REQUEST_READ_PHONE_STATE = 0;
    public static final int REQUEST_READ_CALL_LOG = 1;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 2;


    PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //state 当前状态 incomingNumber,貌似没有去电的API
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e(TAG, "挂断");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e(TAG, "接听 OFFHOOK" + incomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e(TAG, "响铃:RINGING" + incomingNumber);
                    //输出来电号码
                    break;
            }
        }

    };
    private String TAG = "测试";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }

        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);

        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_READ_CALL_LOG);
        } else {
            //TODO
        }
        int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck3 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            //TODO
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
        TelephonyManager tm = (TelephonyManager) getSystemService(Service.TELEPHONY_SERVICE);
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

        mPhoneReceiver = new PhoneReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //添加监听电话状态变化的Action
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        //设置咱们这个广播的优先级
        intentFilter.setPriority(Integer.MAX_VALUE);
        //注册，嗖嗖嗖
        registerReceiver(mPhoneReceiver, intentFilter);

       new Thread(new Runnable() {
           @Override
           public void run() {

              FileUtils.findRecordFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Sounds"));
           }
       }).start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            case REQUEST_READ_CALL_LOG:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mPhoneReceiver);
        super.onDestroy();
    }


}
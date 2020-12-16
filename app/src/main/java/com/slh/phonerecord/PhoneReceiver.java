package com.slh.phonerecord;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {
	public static final String  TAG = "广播";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG," action" + intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            //如果是去电（拨出）
           Log.e(TAG,"拨出");
        } else {
            //查了下android文档，貌似没有专门用于接收来电的action,所以，非去电即来电
           Log.e(TAG,"来电");

            //设置一个监听器
        }
    }

    PhoneStateListener listener = new PhoneStateListener() {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            // TODO Auto-generated method stub
            //state 当前状态 incomingNumber,貌似没有去电的API
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                   Log.e(TAG,"挂断");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                   Log.e(TAG,"接听 OFFHOOK" +incomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                   Log.e(TAG,"响铃:RINGING" + incomingNumber);
                    //输出来电号码
                    break;
            }
        }

    };
}
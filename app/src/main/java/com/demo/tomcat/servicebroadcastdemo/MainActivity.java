package com.demo.tomcat.servicebroadcastdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

            TextView    tvMsgBox;
            Button      btnStartService;
    private BroadcastService.BroadcastBinder binder;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.w(TAG, "onCreate()...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();

    }


    //------------- User define function ---------------------------------------//
    private void initView()
    {
        Log.d(TAG, "initView()...");
        tvMsgBox = (TextView) findViewById(R.id.tvMsg);
        btnStartService = (Button) findViewById(R.id.btnStart);
    }

    private void initControl()
    {
        Log.d(TAG, "initControl()...");
    }


    //--------------- inner class ----------------------------------------------//
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.w(TAG, "onServiceConnected()...");
            binder = (BroadcastService.BroadcastBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.w(TAG, "onServiceDisconnected()...");

        }
    };

}


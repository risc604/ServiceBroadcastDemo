package com.demo.tomcat.servicebroadcastdemo;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


//  http://www.cnblogs.com/gqsblog/archive/2012/04/17/2453378.html


public class BroadcastService extends Service
{
    private static final String TAG = BroadcastBinder.class.getSimpleName();

    private int data;
    private Handler handler;
    private boolean isStart;
    private boolean startUpdate;


    public BroadcastService() {
        Log.w(TAG, "BroadcastService(), constructor");
    }

    @Override
    public void onCreate() {
        Log.w(TAG, "onCreate()...");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.w(TAG, "onStartCommand()...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.w(TAG, "onBind()...");
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        isStart = true;
        new Thread(new UpdateThread()).start();
        return new BroadcastBinder();
    }

    @Override
    public void onRebind(Intent intent)
    {
        Log.w(TAG, "onRebind()...");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.w(TAG, "onUnbind()...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        Log.w(TAG, "onDestroy()...");
        super.onDestroy();
    }

    //-------- inner class -------------------------------------------//
    public class BroadcastBinder extends Binder
    {
        @SuppressLint("HandlerLeak")
        public void setDate(final TextView tv, final UpdateData updata)
        {
            Log.w(TAG, "BroadcastBinder, setDate()...");
            startUpdate = true;
            handler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);
                    updata.update(tv, data);
                }
            };
        }
    }

    public class UpdateThread implements Runnable
    {
        @Override
        public void run()
        {
            Log.w(TAG, "UpdateThread, run()...");
            while (isStart)
            {
                if (startUpdate)
                {
                    data++;
                    Message msg = handler.obtainMessage();
                    msg.arg1 = data;
                    handler.sendMessage(msg);
                }

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface UpdateData
    {
        //Log.w(TAG, "UpdateData, run()...");
        public void update(TextView tv, int data);
    }


}

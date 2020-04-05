package com.xman.demo_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataService extends Service {

    private static final String TAG = "Jxr35";
    private List<IData> mArrays = new ArrayList<>();

    public DataService() {
        Log.i(TAG, "create DataService..");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand..");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind..");
        return new IDataStub() {
            @Override
            public void add(IData data) throws RemoteException {
                mArrays.add(data);
            }

            @Override
            public List<IData> getData() throws RemoteException {
                return mArrays;
            }
        };
    }
}

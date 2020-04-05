package com.xman.real_aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xman.aidl.IDataManager;
import com.xman.bean.IData;

import java.util.ArrayList;
import java.util.List;

public class DataService extends Service {

    private static final String TAG = "Jxr35";
    private List<IData> mArrays = new ArrayList<>();

    public DataService() {
        Log.i(TAG, "create DataService..");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate..");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand..");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind..");
        return new IDataManager.Stub() {
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

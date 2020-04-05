package com.xman.real_aidl_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.xman.aidl.IDataManager;

public class MainActivity extends AppCompatActivity {

    IDataManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(new Intent(this, DataService.class),
                new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        mManager = IDataManager.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        mManager = null;
                    }
                }, BIND_AUTO_CREATE);
    }
}

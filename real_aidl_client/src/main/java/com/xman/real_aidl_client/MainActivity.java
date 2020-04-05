package com.xman.real_aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xman.bean.IData;
import com.xman.aidl.IDataManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Jxr35";
    private IDataManager mDataManager;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);
        mTextView.setOnClickListener(this);
        bindService();
    }

    @Override
    public void onClick(View v) {
        try {
            mDataManager.add(new IData(0, "Key"));
            Log.i(TAG, "data: " + mDataManager.getData());
            mTextView.setText(mDataManager.getData().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindService() {
        Log.i(TAG, "bindService..");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.xman.real_aidl_service", "com.xman.real_aidl_service.DataService"));
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "onServiceConnected..");
                mDataManager = IDataManager.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected..");
                mDataManager = null;
            }
        }, BIND_AUTO_CREATE);
    }

}

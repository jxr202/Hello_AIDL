package com.xman.demo_client;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Jxr35";

    private IDataManager mManager;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        mTextView = findViewById(R.id.text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mManager.add(new IData(1, "Key"));
                    mTextView.setText(mManager.getData().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initService() {
        Log.i(TAG, "initService");
        Intent intent = new Intent();
        //intent.
        bindService(new Intent() {{
                        setComponent(new ComponentName("com.xman.demo_service", "com.xman.demo_service.DataService"));
                    }}
                , new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.i(TAG, "onServiceConnected");
                        mManager = IDataStub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.i(TAG, "onServiceDisconnected");
                        mManager = null;
                    }
                }, BIND_AUTO_CREATE);
    }
}

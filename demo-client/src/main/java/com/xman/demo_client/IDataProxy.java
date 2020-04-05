package com.xman.demo_client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

import static com.xman.demo_client.IDataStub.DESCRIPTOR;
import static com.xman.demo_client.IDataStub.TRANSACTION_add;
import static com.xman.demo_client.IDataStub.TRANSACTION_getData;

/**
 * Created by Jxr35 on 2020/4/5
 */
public class IDataProxy implements IDataManager {

    private IBinder mRemote;

    public IDataProxy(IBinder binder) {
        mRemote = binder;
    }

    @Override
    public void add(IData iData) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (iData != null) {
                data.writeInt(1);
                iData.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            mRemote.transact(TRANSACTION_add, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public List<IData> getData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<IData> result;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_getData, data, reply, 0);
            reply.readException();
            result = reply.createTypedArrayList(IData.CREATOR);
        } finally {
            data.recycle();
            reply.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}

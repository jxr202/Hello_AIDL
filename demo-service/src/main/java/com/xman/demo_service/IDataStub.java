package com.xman.demo_service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Jxr35 on 2020/4/5
 * Stub类主要是用来接收数据，通过onTransact方法接收
 */
public abstract class IDataStub extends Binder implements IDataManager {

    public static final String DESCRIPTOR = "com.xman.demo_service.IDataManager";
    public static final int TRANSACTION_add = FIRST_CALL_TRANSACTION;
    public static final int TRANSACTION_getData = FIRST_CALL_TRANSACTION + 1;

    public IDataStub() {
        attachInterface(this, DESCRIPTOR);
    }

    public static IDataManager asInterface(IBinder binder) {
        if (binder == null) {
            return null;
        }
        IInterface iInterface = binder.queryLocalInterface(DESCRIPTOR);
        if (iInterface instanceof IDataManager) {
            return (IDataManager) iInterface;
        }
        return new IDataProxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_add:
                data.enforceInterface(DESCRIPTOR);
                IData iData = null;
                if (data.readInt() != 0) {
                    iData = IData.CREATOR.createFromParcel(data);
                }
                add(iData);
                reply.writeNoException();
                return true;
            case TRANSACTION_getData:
                data.enforceInterface(DESCRIPTOR);
                List<IData> result = getData();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}

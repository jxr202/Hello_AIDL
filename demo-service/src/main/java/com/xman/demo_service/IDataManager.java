package com.xman.demo_service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by Jxr35 on 2020/4/5
 * 接口的定义
 */
public interface IDataManager extends IInterface {

    void add(IData data) throws RemoteException;

    List<IData> getData() throws RemoteException;
}

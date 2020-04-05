// IDataManager.aidl
package com.xman.aidl;

import com.xman.bean.IData;

// Declare any non-default types here with import statements

interface IDataManager {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void add(in IData data);

    List<IData> getData();
}

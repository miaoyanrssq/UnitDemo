package com.example.component_db;

import android.content.Context;

import com.example.component_db.daos.DaoMaster;
import com.example.component_db.daos.DaoSession;


/**
 * Created by gary on 2017/12/6.
 */

public class DBComponent {
    private static Context sContext;

    private static DaoSession daoSession;

    public static void init(Context context){
        sContext = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "zjrb-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}

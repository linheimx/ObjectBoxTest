package com.linheimx.objectboxtest;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.linheimx.objectboxtest.db.objectbox.entity.MyObjectBox;
import com.linheimx.objectboxtest.db.sqlite.DbHelper;

import java.io.File;
import java.io.IOException;

import io.objectbox.BoxStore;

/**
 * Created by x1c on 2017/4/27.
 */

public class App extends Application {

    private BoxStore boxStore;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();


//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            // 读写权限授予了
//            File directory = new File(Environment.getExternalStorageDirectory(), "ObjectBoxTest");
//            if (!directory.exists()) {
//                directory.mkdir();
//            }
//            boxStore = MyObjectBox.builder().androidContext(App.this).directory(directory).build();
//        }
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        sqLiteDatabase = new DbHelper(this).getWritableDatabase();
    }

    public void setBoxStore(BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }
}

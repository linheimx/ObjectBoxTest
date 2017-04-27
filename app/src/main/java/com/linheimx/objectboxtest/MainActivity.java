package com.linheimx.objectboxtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.linheimx.objectboxtest.db.sqlite.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DbHelper dbHelper = new DbHelper(this.getApplicationContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("pragma table_info('tableName')", null);
//
//        StringBuilder sb = new StringBuilder();
//        try {
//            while (cursor.moveToNext()) {
//                for (int i = 0; i < cursor.getColumnCount(); i++) {
//                    sb.append(cursor.getString(i)+" ");
//                }
//            }
//            Log.e("--->", sb.toString() + "");
//        } finally {
//            cursor.close();
//        }


        // 去申请权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }


    public void btnPerformance(View view) {
        startActivity(new Intent(this, PerformanceActivity.class));
    }

    public void btnCRUD(View view) {
        startActivity(new Intent(this, CRUDActivity.class));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "必须要有写的权限！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

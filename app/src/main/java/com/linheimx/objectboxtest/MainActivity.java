package com.linheimx.objectboxtest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.linheimx.objectboxtest.db.sqlite.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this.getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("pragma table_info('tableName')", null);

        StringBuilder sb = new StringBuilder();
        try {
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    sb.append(cursor.getString(i)+" ");
                }
            }
            Log.e("--->", sb.toString() + "");
        } finally {
            cursor.close();
        }
    }
}

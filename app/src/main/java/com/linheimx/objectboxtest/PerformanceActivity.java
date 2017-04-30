package com.linheimx.objectboxtest;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.linheimx.objectboxtest.db.objectbox.entity.Dog;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class PerformanceActivity extends AppCompatActivity {

    public static final int MAX = 10000;

    Box<Dog> _Box;
    BoxStore _BoxStore;

    SQLiteDatabase _Database;

    ChartFragment chartFragment;
    Handler _Handler = new Handler();
    ExecutorService _ExecutorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        getSupportActionBar().setTitle("性能测试");

        _BoxStore = ((App) getApplication()).getBoxStore();
        _Box = _BoxStore.boxFor(Dog.class);

        _Database = ((App) getApplication()).getSqLiteDatabase();

        chartFragment = new ChartFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, chartFragment)
                .commit();
    }

    /**
     * 测试插入
     *
     * @param view
     */
    public void btn_insert(View view) {

        _ExecutorService.execute(new Runnable() {
            @Override
            public void run() {

                ////////////////////// 后台线程运行 ////////////////
                long all_ob = 0;
                for (int i = 0; i < 5; i++) {
                    all_ob += insert_ObjectBox();
                }
                all_ob = all_ob / 5;


                long all_sqlite = 0;
                for (int i = 0; i < 5; i++) {
                    all_sqlite += insert_sqlite();
                }
                all_sqlite = all_sqlite / 5;

                final long t1 = all_ob;
                final long t2 = all_sqlite;
                //////////////////////// 主线程show  /////////////////////////
                _Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        chartFragment.setData(t1, t2);
                    }
                });

            }
        });
    }

    /**
     * 测试查询
     *
     * @param view
     */
    public void btn_select(View view) {

    }


    private long insert_ObjectBox() {

        List<Dog> list = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            Dog dog = new Dog(0, "哈士奇");
            list.add(dog);
        }

        long start = System.currentTimeMillis();
        _Box.put(list);
        long end = System.currentTimeMillis();

        return end - start;
    }

    private long insert_sqlite() {
        long start = System.currentTimeMillis();

        // 在一个事物里面运行
        _Database.beginTransaction();

        try {
            for (int i = 0; i < MAX; i++) {
                String sql = "insert into dog(name) values('哈士奇')";
                _Database.execSQL(sql);
            }
            _Database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _Database.endTransaction();
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        _ExecutorService.shutdownNow();
    }
}

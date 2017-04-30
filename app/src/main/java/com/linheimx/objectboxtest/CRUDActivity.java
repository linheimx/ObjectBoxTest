package com.linheimx.objectboxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linheimx.objectboxtest.db.objectbox.entity.School;
import com.linheimx.objectboxtest.db.objectbox.entity.Teacher;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.LazyList;

public class CRUDActivity extends AppCompatActivity {


    BoxStore _BoxStore;
    Box<School> _Box;

    RecyclerView _rv;
    Button _btnAdd;
    EditText _etName;

    RVAdapter _RvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        getSupportActionBar().setTitle("增删改查");


        _BoxStore = ((App) getApplication()).getBoxStore();
        _Box = _BoxStore.boxFor(School.class);

        _RvAdapter = new RVAdapter();

        _btnAdd = (Button) findViewById(R.id.btn_add);
        _etName = (EditText) findViewById(R.id.et_name);
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _etName.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    School school = new School();
                    school.setName(name);
                    _Box.put(school);

                    _RvAdapter.reloadData();
                    _rv.scrollToPosition((int) _Box.count()-1);
                }
            }
        });

        _rv = (RecyclerView) findViewById(R.id.rv);
        _rv.setLayoutManager(new LinearLayoutManager(this));
        _rv.setAdapter(_RvAdapter);
    }


    class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH> {

        LazyList<School> lazyList;

        public RVAdapter() {
            super();

            /**
             * 懒加载 + 缓存
             */
            lazyList = _Box.query().build().findLazyCached();
        }

        public void reloadData() {
            lazyList = _Box.query().build().findLazyCached();
            notifyDataSetChanged();
        }


        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.rx_item, null);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            School school = lazyList.get(position);
            holder.bind(school);
        }

        @Override
        public int getItemCount() {
            return (int) _Box.count();
        }

        class VH extends RecyclerView.ViewHolder {
            TextView tvNmae;

            public VH(View itemView) {
                super(itemView);
                tvNmae = (TextView) itemView.findViewById(R.id.tv_name);
            }

            public void bind(School school) {
                tvNmae.setText(school.getName());
            }
        }
    }
}

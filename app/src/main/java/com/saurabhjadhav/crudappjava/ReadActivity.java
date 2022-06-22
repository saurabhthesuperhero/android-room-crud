package com.saurabhjadhav.crudappjava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saurabhjadhav.crudappjava.adapter.DataAdapter;
import com.saurabhjadhav.crudappjava.databinding.ActivityReadBinding;
import com.saurabhjadhav.crudappjava.room.DatabaseClient;
import com.saurabhjadhav.crudappjava.room.SimpleModel;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private ActivityReadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);
        binding.floatingButtonAdd.setOnClickListener(view -> openAddScreen());
        getData();
    }

    private void openAddScreen() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void getData() {
        class GetData extends AsyncTask<Void, Void, List<SimpleModel>> {

            @Override
            protected List<SimpleModel> doInBackground(Void... voids) {
                List<SimpleModel> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .Dao()
                        .getAllData();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<SimpleModel> datas) {
                super.onPostExecute(datas);
                Log.e("checkme", "onPostExecute: "+datas );
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                binding.recyclerviewDatas.setLayoutManager(layoutManager);
                DataAdapter adapter = new DataAdapter(ReadActivity.this, datas);
                binding.recyclerviewDatas.setAdapter(adapter);

            }
        }

        GetData gd = new GetData();
        gd.execute();
    }
}
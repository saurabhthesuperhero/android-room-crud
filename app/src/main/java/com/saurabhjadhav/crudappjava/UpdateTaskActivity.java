package com.saurabhjadhav.crudappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.saurabhjadhav.crudappjava.databinding.ActivityUpdateTaskBinding;
import com.saurabhjadhav.crudappjava.room.DatabaseClient;
import com.saurabhjadhav.crudappjava.room.SimpleModel;

public class UpdateTaskActivity extends AppCompatActivity {
    private ActivityUpdateTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_task);
        final SimpleModel data = (SimpleModel) getIntent().getSerializableExtra("data");
        loadData(data);
        binding.updateButton.setOnClickListener(view -> {
            updateData(data);
        });

        binding.deleteButton.setOnClickListener(view -> {
            deleteData(data);
        });
    }

    private void loadData(SimpleModel data) {
        binding.editTextTextPersonName.setText(data.getName());
        binding.editTextTextEmailAddress.setText(data.getEmail());
        binding.editTextImageUrl.setText(data.getImgUrl());

    }


    private void updateData(final SimpleModel data) {
        final String name = binding.editTextTextPersonName.getText().toString().trim();
        final String email = binding.editTextTextEmailAddress.getText().toString().trim();
        final String img = binding.editTextImageUrl.getText().toString().trim();

        if (name.isEmpty()) {
            binding.editTextTextPersonName.setError("name required");
            binding.editTextTextPersonName.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.editTextTextEmailAddress.setError("Empty or Invalid Email Detected");
            binding.editTextTextEmailAddress.requestFocus();
            return;

        }

        if (img.isEmpty())
        {
            binding.editTextImageUrl.setError("Imgurl required");
            binding.editTextImageUrl.requestFocus();
            return;
        }


        class UpdateData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                data.setName(name);
                data.setEmail(email);
                data.setImgUrl(img);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .Dao()
                        .update(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, ReadActivity.class));
            }
        }

        UpdateData ut = new UpdateData();
        ut.execute();
    }

    private void deleteData(final SimpleModel data) {
        class DeleteData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .Dao()
                        .delete(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        DeleteData dt = new DeleteData();
        dt.execute();

    }
}
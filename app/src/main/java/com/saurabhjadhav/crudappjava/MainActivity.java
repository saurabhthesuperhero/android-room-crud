package com.saurabhjadhav.crudappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.saurabhjadhav.crudappjava.databinding.ActivityMainBinding;
import com.saurabhjadhav.crudappjava.retrofit.APIClient;
import com.saurabhjadhav.crudappjava.retrofit.APIInterface;
import com.saurabhjadhav.crudappjava.room.DatabaseClient;
import com.saurabhjadhav.crudappjava.room.SimpleModel;

import java.util.regex.Pattern;

import okhttp3.internal.concurrent.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        binding.button.setOnClickListener(view -> saveData());
        binding.btnShowData.setOnClickListener(view -> showData());








//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<String> call = apiInterface.getFun();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });


    }

    private void showData() {
        startActivity(new Intent(getApplicationContext(), ReadActivity.class));

    }

    private void saveData() {
        String name=binding.editTextTextPersonName.getText().toString();
        String email=binding.editTextTextEmailAddress.getText().toString();
        String imgUrl=binding.editTextImageUrl.getText().toString();
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

        if (imgUrl.isEmpty())
        {
            binding.editTextImageUrl.setError("Imgurl required");
            binding.editTextImageUrl.requestFocus();
            return;
        }

        class SaveData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                SimpleModel data = new SimpleModel();
                data.setName(name);
                data.setEmail(email);
                data.setImgUrl(imgUrl);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .Dao()
                        .insert(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Log.e("checkme", "onPostExecute: Saved" );
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }

        SaveData st = new SaveData();
        st.execute();

    }
}
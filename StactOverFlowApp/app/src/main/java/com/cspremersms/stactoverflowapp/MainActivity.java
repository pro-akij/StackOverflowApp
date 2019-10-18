package com.cspremersms.stactoverflowapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.cspremersms.stactoverflowapp.Rest.ApiClint;
import com.cspremersms.stactoverflowapp.Rest.ImageEndPoints;
import com.cspremersms.stactoverflowapp.Rest.UserEndPoints;
import com.cspremersms.stactoverflowapp.adapter.UserAdapter;
import com.cspremersms.stactoverflowapp.model.ImageModel;
import com.cspremersms.stactoverflowapp.model.User;
import com.cspremersms.stactoverflowapp.model.UserReceive;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<User> list= new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private Bitmap bitmapimage;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.item_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(list, getApplicationContext(), R.layout.item_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        LoadApiData();
        //LoadImage();
    }

    public void LoadApiData(){
        UserEndPoints apiservice= ApiClint.RetrofitClint().create(UserEndPoints.class);
        Call<UserReceive> call = apiservice.getUser("reputation");
        call.enqueue(new Callback<UserReceive>() {
            @Override
            public void onResponse(Call<UserReceive> call, Response<UserReceive> response) {
                list.clear();
                list.addAll(response.body().getUsers());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<UserReceive> call, Throwable t) {
                Log.d("erroe is: ", t.getMessage());
            }
        });
    }

    /*
    public void LoadImage(){
        ImageEndPoints imageEndPoints= ApiClint.RetrofitClint().create(ImageEndPoints.class);
        Call<ImageModel> call= imageEndPoints.getImage();
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                Download_Image download_image= new Download_Image();
                try {
                    bitmapimage= download_image.execute(response.body().getProfile_image()).get();
                    imageView= findViewById(R.id.profileimage);
                    System.out.println("Imaga error is: "+bitmapimage);
                    imageView.setImageBitmap(bitmapimage);
                    adapter.notifyDataSetChanged();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                Log.d("Image Error is: ", t.getMessage());
            }
        });
    }
    */

    /*
    public class Download_Image extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url= new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream= httpURLConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

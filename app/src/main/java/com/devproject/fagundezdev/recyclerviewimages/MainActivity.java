package com.devproject.fagundezdev.recyclerviewimages;

import android.os.Bundle;

import com.devproject.fagundezdev.recyclerviewimages.model.Example;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

/*
* Class name: MainActivity
* Base class
* @author Miguel Fagundez
* @version 1.0
* @since January 2020
* */
public class MainActivity extends AppCompatActivity {

    // Base URL for testing
    // OkHttp option
    //private static final String URL_TAG = "https://randomuser.me/api?results=20";
    // Retrofit option
    private static final String URL_TAG = "https://randomuser.me";
    private static final String TAG = "MainActivity_onResponse";

    // Members
    private RecyclerView rvFriends;
    private FriendAdapter adapter;

    // Client used to connect with the URL_TAG
    private OkHttpClient okHttp;

    // Retrofit
    private Retrofit retrofit;

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
                Toast.makeText(getApplicationContext(),"Feature no implemented yet!",Toast.LENGTH_SHORT).show();
            }
        });

        //***************
        // OkHttp option
        //***************
        /*okHttp = new OkHttpClient();
        connectHttp(URL_TAG);*/

        //*****************
        // Retrofit option
        //*****************
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_TAG)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        connectRetrofit(URL_TAG);

        ArrayList<Friend> friends = new ArrayList<>(0);

        rvFriends = findViewById(R.id.recyclerId);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvFriends.setLayoutManager(manager);
        adapter = new FriendAdapter(friends);
        rvFriends.setAdapter(adapter);
    }

    //*****************
    // Retrofit option
    //*****************
    private void connectRetrofit(String urlTag) {
        RandomUserService service = retrofit.create(RandomUserService.class);
        retrofit2.Call<Example> call = service.getFriends();
        call.enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onResponse(retrofit2.Call<Example> call, retrofit2.Response<Example> response) {
                lookingFriends(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<Example> call, Throwable t) {
                Log.d(TAG,"Error calling...");
                t.printStackTrace();
            }
        });
    }


    //*****************
    // OkHttp option
    //*****************
    private void connectHttp(String url) {
        // Requesting access to URL
        Request request = new Request.Builder().url(url).build();
        // Connecting with URL
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // if error happen
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // if a response is received
                final String strResponse = response.body().string();
                Gson gsonObject = new Gson();
                // Getting the data using a gson object
                final Example list = gsonObject.fromJson(strResponse, Example.class);
                lookingFriends(list);
            }
        });
    }

    //*********************************************
    // Create this method to looking into the data
    // This method is used in both options
    //*********************************************
    private void lookingFriends(Example list){
        final ArrayList<Friend> friends = initFriends(list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.updateFriends(friends);
            }
        });
    }

    //*********************************************
    // Create this method to initialize my data using only OkHttp library
    // This method is used in both options
    //*********************************************
    private ArrayList<Friend> initFriends(Example list){
        int numberFriends = list.getResults().size();
        Friend friend;
        ArrayList<Friend> friends = new ArrayList<>();

        for (int i = 0; i < numberFriends; i++){
            friend = new Friend(
                    list.getResults().get(i).getName().getFirst(),
                    list.getResults().get(i).getName().getLast(),
                    list.getResults().get(i).getPicture().getLarge());
            friends.add(friend);
        }

        return friends;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

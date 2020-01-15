package com.devproject.fagundezdev.recyclerviewimages;

import android.os.Bundle;

import com.devproject.fagundezdev.recyclerviewimages.model.Example;
import com.devproject.fagundezdev.recyclerviewimages.model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* Class name: MainActivity
* Base class
* @author Miguel Fagundez
* @version 1.0
* @since January 2020
* */
public class MainActivity extends AppCompatActivity {

    // Base URL for testing
    private static final String URL_TAG = "https://randomuser.me/api?results=2";
    private static final String TAG = "MainActivity_onResponse";

    // Members
    private RecyclerView rvFriends;
    private RecyclerView.Adapter adapter;

    private ArrayList<Friend> friends = new ArrayList<>();

    // Client used to connect with the URL_TAG
    private OkHttpClient okHttp;

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

        // Connect with the URL_TAG and wait for response
        okHttp = new OkHttpClient();
        connectHttp(URL_TAG);

        ArrayList<Friend> friends = initFriends();

        rvFriends = findViewById(R.id.recyclerId);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvFriends.setLayoutManager(manager);

        adapter = new FriendAdapter(friends);
        rvFriends.setAdapter(adapter);
    }

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
                // Getting the data
                final Example example = gsonObject.fromJson(strResponse,Example.class);
                Log.d(TAG, "Information - Number of friends (example): " + example.getResults().size());
                // NEW CODE HERE
                /*Thread thread = new Thread(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //newInitFriends(example);
                            }
                        });
                    }
                };
                thread.start();*/
                //ParsingData data = (ParsingData) new ParsingData().execute(example);
                //friends = data.getFriends();
                Log.d(TAG, "Information - Number of friends: " + friends.size());
                //newInitFriends(example);
                Log.d(TAG, "Information after Parsed: " + example.getResults().get(0).getName().getFirst()
                        + " " + example.getResults().get(0).getName().getLast());
            }
        });
    }

    private void newInitFriends(Example example){
        int numberFriends = example.getResults().size();
        Friend friend;

        for (int i = 0; i < numberFriends; i++){
            friend = new Friend(
                    example.getResults().get(i).getName().getFirst(),
                    example.getResults().get(i).getName().getLast(),
                    example.getResults().get(i).getPicture().getLarge());
            friends.add(friend);
        }
    }

    private ArrayList<Friend> initFriends(){

        ArrayList<Friend> listFriends = new ArrayList<>();

        listFriends.add(new Friend("Erin","Staley","https://randomuser.me/api/portraits/women/64.jpg"));
        listFriends.add(new Friend("George","Ramos","https://randomuser.me/api/portraits/men/81.jpg"));
        listFriends.add(new Friend("Erik","Gregory","https://randomuser.me/api/portraits/men/31.jpg"));
        listFriends.add(new Friend("Marianne","Sirko","https://randomuser.me/api/portraits/women/23.jpg"));
        listFriends.add(new Friend("Ryder","Lee","https://randomuser.me/api/portraits/men/42.jpg"));
        listFriends.add(new Friend("Una","Bostad","https://randomuser.me/api/portraits/women/73.jpg"));
        listFriends.add(new Friend("Andréa","Bernard","https://randomuser.me/api/portraits/women/27.jpg"));
        listFriends.add(new Friend("Ljiljana","Roy","https://randomuser.me/api/portraits/women/86.jpg"));
        listFriends.add(new Friend("Silvester","Reuter","https://randomuser.me/api/portraits/men/53.jpg"));
        listFriends.add(new Friend("Marilou","Lecomte","https://randomuser.me/api/portraits/women/75.jpg"));
        listFriends.add(new Friend("Raymond","Eifler","https://randomuser.me/api/portraits/men/38.jpg"));
        listFriends.add(new Friend("Miranda","Gonzalez","https://randomuser.me/api/portraits/women/66.jpg"));
        listFriends.add(new Friend("Ellen","Anderson","https://randomuser.me/api/portraits/women/89.jpg"));
        return listFriends;
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

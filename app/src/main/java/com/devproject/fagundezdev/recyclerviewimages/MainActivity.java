package com.devproject.fagundezdev.recyclerviewimages;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFriends;
    private RecyclerView.Adapter adapter;

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

        ArrayList<Friend> friends = initFriends();

        rvFriends = (RecyclerView) findViewById(R.id.recyclerId);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvFriends.setLayoutManager(manager);

        adapter = new FriendAdapter(friends);
        rvFriends.setAdapter(adapter);

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
        listFriends.add(new Friend("Miranda","Gonzalez","https://randomuser.me/api/portraits/women/64.jpg"));
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

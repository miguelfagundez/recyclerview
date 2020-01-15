package com.devproject.fagundezdev.recyclerviewimages;

import android.os.AsyncTask;
import android.util.Log;

import com.devproject.fagundezdev.recyclerviewimages.model.Example;
import com.devproject.fagundezdev.recyclerviewimages.model.Result;

import java.util.ArrayList;
import java.util.List;

public class ParsingData extends AsyncTask<Example, Void, ArrayList<Friend>> {

    private static final String TAG = "Parsing_TAG";
    private ArrayList<Friend> friends;

    public ParsingData() {
        this.friends = new ArrayList<>();
    }

    @Override
    protected ArrayList<Friend> doInBackground(Example... example) {

        int numberFriends = example[0].getResults().size();
        Friend friend;

        for (int i = 0; i < numberFriends; i++){
            friend = new Friend(
                    example[0].getResults().get(i).getName().getFirst(),
                    example[0].getResults().get(i).getName().getLast(),
                    example[0].getResults().get(i).getPicture().getLarge());
            friends.add(friend);
        }
        return friends;
    }

    @Override
    protected void onPostExecute(ArrayList<Friend> result) {
        Log.d(TAG, "Information - Number of friends (Parsing): " + friends.size());
    }

    public ArrayList<Friend> getFriends(){
        return friends;
    }
}

package com.devproject.fagundezdev.recyclerviewimages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private ArrayList<Friend> myFriendList;

    public FriendAdapter(ArrayList<Friend> myFriendList) {
        this.myFriendList = myFriendList;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);

        return new FriendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend = myFriendList.get(position);
        holder.name.setText(friend.getfName() + " " + friend.getlName());
        Picasso.get().load(friend.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return myFriendList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final View view;
        public final TextView name;
        public final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
            this.name = itemView.findViewById(R.id.tvName);
            this.image = itemView.findViewById(R.id.ivImage);
        }
    }
}

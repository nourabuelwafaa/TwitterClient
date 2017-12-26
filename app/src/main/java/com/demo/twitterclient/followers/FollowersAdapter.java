package com.demo.twitterclient.followers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.twitterclient.OnItemCLicked;
import com.demo.twitterclient.R;
import com.demo.twitterclient.Utils;
import com.demo.twitterclient.repo.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {

    private OnItemCLicked activity;
    private List<User> list;
    private Context context;


    public FollowersAdapter(Context context, OnItemCLicked activity, List<User> list) {
        this.activity = activity;
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_follower_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.usernameTv.setText(user.getName());
        if (Utils.checkNull(user.getDescription())) {
            holder.userBioTv.setText(user.getDescription());
            holder.userBioTv.setVisibility(View.VISIBLE);
        } else {
            holder.userBioTv.setVisibility(View.GONE);
        }
        Picasso.with(context).load(user.getProfileImageUrl()).into(holder.userPhotoIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onItemClicked(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv, userBioTv;
        CircleImageView userPhotoIv;

        MyViewHolder(View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.usernameTv);
            userBioTv = itemView.findViewById(R.id.userBioTv);
            userPhotoIv = itemView.findViewById(R.id.userPhotoIv);


        }
    }
}

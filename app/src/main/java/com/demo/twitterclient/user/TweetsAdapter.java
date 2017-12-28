package com.demo.twitterclient.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.twitterclient.utils.MyLog;
import com.demo.twitterclient.OnItemCLicked;
import com.demo.twitterclient.R;
import com.demo.twitterclient.repo.model.tweet.ExtendedEntities;
import com.demo.twitterclient.repo.model.tweet.Media;
import com.demo.twitterclient.repo.model.tweet.RetweetedStatus;
import com.demo.twitterclient.repo.model.tweet.Tweet;
import com.demo.twitterclient.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.MyViewHolder> {

    private List<Tweet> list;
    private Context context;


    public TweetsAdapter(Context context, List<Tweet> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_tweet_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Tweet tweet = list.get(position);
        RetweetedStatus retweetedStatus = tweet.getRetweetedStatus();

        if (retweetedStatus != null) {
            holder.usernameTv.setText(retweetedStatus.getUser().getName());
            Picasso.with(context).load(retweetedStatus.getUser().getProfileImageUrl()).into(holder.userPhotoIv);
        } else {
            holder.usernameTv.setText(tweet.getUser().getName());
            Picasso.with(context).load(Utils.getBiggerPhoto(tweet.getUser().getProfileImageUrl())).into(holder.userPhotoIv);
        }
        holder.tweetTv.setText(tweet.getText());
        holder.retweetCountTv.setText(String.valueOf(tweet.getRetweetCount()));
        holder.favoriteCountTv.setText(String.valueOf(tweet.getFavoriteCount()));

        ExtendedEntities entities = tweet.getExtendedEntities();

        if (entities != null) {
            if (entities.getMedia() != null && entities.getMedia().size() > 0) {
                Media media = entities.getMedia().get(0);

                if (media.getType().equals("photo")) {
                    MyLog.d(media.getType());
                    Picasso.with(context).load(media.getMediaUrl()).into(holder.tweetImage);
                    holder.tweetImage.setVisibility(View.VISIBLE);
                }
            }
        } else {
            holder.tweetImage.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv, tweetTv, retweetCountTv, favoriteCountTv;
        CircleImageView userPhotoIv;
        ImageView tweetImage;

        MyViewHolder(View itemView) {
            super(itemView);
            retweetCountTv = itemView.findViewById(R.id.retweetCountTv);
            favoriteCountTv = itemView.findViewById(R.id.favoriteCountTv);
            usernameTv = itemView.findViewById(R.id.usernameTv);
            tweetTv = itemView.findViewById(R.id.tweetTv);
            userPhotoIv = itemView.findViewById(R.id.userPhotoIv);
            tweetImage = itemView.findViewById(R.id.tweetImage);

        }
    }
}

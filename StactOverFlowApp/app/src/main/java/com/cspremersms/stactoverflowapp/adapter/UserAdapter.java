package com.cspremersms.stactoverflowapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cspremersms.stactoverflowapp.R;
import com.cspremersms.stactoverflowapp.Rest.ApiClint;
import com.cspremersms.stactoverflowapp.Rest.ImageEndPoints;
import com.cspremersms.stactoverflowapp.Rest.UserEndPoints;
import com.cspremersms.stactoverflowapp.model.ImageModel;
import com.cspremersms.stactoverflowapp.model.User;
import com.cspremersms.stactoverflowapp.model.UserReceive;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private Context context;
    private int rowlayout;
    private Bitmap bitmapimage;


    public UserAdapter(List<User> users, Context context, int rowlayout) {
        this.setUsers(users);
        this.setContext(context);
        this.setRowlayout(rowlayout);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getRowlayout() {
        return rowlayout;
    }

    public void setRowlayout(int rowlayout) {
        this.rowlayout = rowlayout;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).
                inflate(rowlayout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        holder.userName.setText("Name: "+users.get(position).getUserName());
        holder.userReputation.setText("Reputation: "+users.get(position).getReputation());
        holder.userLocation.setText("Location: "+users.get(position).getLocation());
        holder.userweb.setText("Website: "+users.get(position).getWebsite());
        holder.userid.setText("User_id: "+users.get(position).getUserid());

        ImageEndPoints apiservice2= ApiClint.RetrofitClint().create(ImageEndPoints.class);
        Call<ImageModel> userCall= apiservice2.getImage();
        userCall.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {

               Download_Image download_image= new Download_Image();
                try {
                    bitmapimage= download_image.execute(response.body().getProfile_image()).get();
                    holder.imageView.setImageBitmap(bitmapimage);
                    System.out.println("profile image "+holder.imageView);
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

        Iterator<Map.Entry<String, Integer>> iterator=
                users.get(position).getBadges().entrySet().iterator();

        Map.Entry<String, Integer> pair= iterator.next();
        holder.goldebB.setText(pair.getKey()+" : ");
        holder.goldebV.setText(pair.getValue().toString());

        pair= iterator.next();
        holder.silverB.setText(pair.getKey()+" : ");
        holder.silverV.setText(pair.getValue().toString());

        pair= iterator.next();
        holder.BronzeB.setText(pair.getKey()+" : ");
        holder.BronzeV.setText(pair.getValue().toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        LinearLayout UserLaout;
        ImageView imageView;
        TextView userName, userReputation, userLocation, userid, userweb, goldebB,
                goldebV, silverB, silverV, BronzeB, BronzeV;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            UserLaout= itemView.findViewById(R.id.userLayout);
            userName= itemView.findViewById(R.id.userName);
            userReputation= itemView.findViewById(R.id.userRupatation);
            userLocation= itemView.findViewById(R.id.userLocation);
            userweb= itemView.findViewById(R.id.userwebsite);
            userid= itemView.findViewById(R.id.userid);
            imageView= itemView.findViewById(R.id.profileimage);

            goldebB= itemView.findViewById(R.id.goldenBadge);
            goldebV= itemView.findViewById(R.id.goldenValue);
            silverB= itemView.findViewById(R.id.silverBadge);
            silverV= itemView.findViewById(R.id.silverValue);
            BronzeB= itemView.findViewById(R.id.brongeBadge);
            BronzeV= itemView.findViewById(R.id.brongeValue);
        }
    }


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
}

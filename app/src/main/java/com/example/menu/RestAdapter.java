package com.example.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.ViewHolder>{

    private ArrayList<RestItem> restItems;
    private Context context;
    private DBSqlite dbSqlite;

    public RestAdapter(ArrayList<RestItem> restItems,Context context){
        this.context = context;
        this.restItems = restItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbSqlite = new DBSqlite(context);

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestAdapter.ViewHolder holder, int position) {
        final RestItem restItem = restItems.get(position);

        readCursorData(restItem, holder);
        holder.imageView.setImageResource(restItem.getImageResourse());
        holder.titleTextView.setText(restItem.getTitle());
    }



    @Override
    public int getItemCount() {
        return restItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView, likeCountTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.nomRest);
            favBtn = itemView.findViewById(R.id.favBtn);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    RestItem coffeeItem = restItems.get(position);

                    //likeClick(coffeeItem, favBtn, likeCountTextView);
                }
            });
        }
    }


    private void createTableOnFirstStart() {
        dbSqlite.addData();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(RestItem restItem, ViewHolder viewHolder) {
        Cursor cursor = dbSqlite.readData();
        SQLiteDatabase db = dbSqlite.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String item_fav_status = cursor.getString(cursor.getColumnIndex(DBSqlite.FAVORITE_STATUS));
                restItem.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }

    // like click
//    private void likeClick (RestItem restItem, Button favBtn, final TextView textLike) {
//        DatabaseReference refLike = FirebaseDatabase.getInstance().getReference().child("likes");
//        final DatabaseReference upvotesRefLike = refLike.child(coffeeItem.getKey_id());
//
//        if (restItem.getFavStatus().equals("0")) {
//
//            restItem.setFavStatus("1");
//            dbSqlite.addData(restItem.getTitle(), restItem.getImageResourse(),
//                    restItem.getKey_id(), restItem.getFavStatus());
//            favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//            favBtn.setSelected(true);
//
//            upvotesRefLike.runTransaction(new Transaction.Handler() {
//                @NonNull
//                @Override
//                public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
//                    try {
//                        Integer currentValue = mutableData.getValue(Integer.class);
//                        if (currentValue == null) {
//                            mutableData.setValue(1);
//                        } else {
//                            mutableData.setValue(currentValue + 1);
//                            new Handler(Looper.getMainLooper()).post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textLike.setText(mutableData.getValue().toString());
//                                }
//                            });
//                        }
//                    } catch (Exception e) {
//                        throw e;
//                    }
//                    return Transaction.success(mutableData);
//                }
//
//                @Override
//                public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
//                    System.out.println("Transaction completed");
//                }
//            });
//
//
//
//        } else if (coffeeItem.getFavStatus().equals("1")) {
//            coffeeItem.setFavStatus("0");
//            favDB.remove_fav(coffeeItem.getKey_id());
//            favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
//            favBtn.setSelected(false);
//
//            upvotesRefLike.runTransaction(new Transaction.Handler() {
//                @NonNull
//                @Override
//                public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
//                    try {
//                        Integer currentValue = mutableData.getValue(Integer.class);
//                        if (currentValue == null) {
//                            mutableData.setValue(1);
//                        } else {
//                            mutableData.setValue(currentValue - 1);
//                            new Handler(Looper.getMainLooper()).post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textLike.setText(mutableData.getValue().toString());
//                                }
//                            });
//                        }
//                    } catch (Exception e) {
//                        throw e;
//                    }
//                    return Transaction.success(mutableData);
//                }
//
//                @Override
//                public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
//                    System.out.println("Transaction completed");
//                }
//            });
//        }
//
//
//
//
//
//
//
//
//
//    }


}

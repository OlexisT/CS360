package com.example.proj_2;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proj_2.entities.Item;

import java.util.Random;

import io.reactivex.schedulers.Schedulers;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    final private Item[] localDataSet;
    final private AppDatabase db;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomAdapter(Item[] dataSet, AppDatabase db) {
        localDataSet = dataSet;
        this.db = db;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_item_row, viewGroup, false);


        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet[position].getItemName() + ":   " +
                localDataSet[position].getItemQuantity());

        viewHolder.itemView.findViewById(R.id.btnPlus).setOnClickListener(v ->
                db.itemDao().incrementById(localDataSet[position].id).subscribeOn(Schedulers.io()).subscribe());

        viewHolder.itemView.findViewById(R.id.btnMinus).setOnClickListener(v ->{
                    //Notification Builder
                    NotificationManager manager = viewHolder.itemView.getContext().getSystemService(NotificationManager.class);

                    NotificationChannel channel = new NotificationChannel("A", "inventoryApp", NotificationManager.IMPORTANCE_DEFAULT );
                    manager.createNotificationChannel(channel);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(viewHolder.itemView.getContext(), channel.getId())
                            .setContentTitle("Inventory Low")
                            .setContentText("An item in your inventory is running low.")
                            .setSmallIcon(R.drawable.icons8_warehouse_96)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    //Checking Permission
                    if (ActivityCompat.checkSelfPermission(viewHolder.itemView.getContext(), "android.permission.POST_NOTIFICATIONS") == PackageManager.PERMISSION_GRANTED && localDataSet[position].getItemQuantity() == 5) {
                        Random NOTIFY_ID = new Random();
                        NotificationManagerCompat.from(viewHolder.itemView.getContext()).notify(NOTIFY_ID.nextInt(), builder.build());
                    }
            db.itemDao().decrementById(localDataSet[position].id).subscribeOn(Schedulers.io()).subscribe();
        });


        viewHolder.itemView.findViewById(R.id.btnDelete).setOnClickListener(v ->
                db.itemDao().delete(localDataSet[position]).subscribeOn(Schedulers.io()).subscribe());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

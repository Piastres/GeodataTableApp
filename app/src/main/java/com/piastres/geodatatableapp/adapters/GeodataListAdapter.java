package com.piastres.geodatatableapp.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.activities.GeodataListActivity;

public class GeodataListAdapter extends
        RecyclerView.Adapter<GeodataListAdapter.ViewHolder> {

    private GeodataListActivity activity;

    public GeodataListAdapter (Activity activity){
        this.activity = (GeodataListActivity) activity;
    }

    @NonNull
    @Override
    public GeodataListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geodata_item, parent, false);

        return new GeodataListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeodataListAdapter.ViewHolder holder,
                                 int position) {
        Typeface font = Typeface.createFromAsset(holder.itemView.getContext()
                .getAssets(), "fa_solid.ttf" );

        String IMG_URL = "https://github.com/Piastres/geodata-table-app/blob/master/app/src/main/res/drawable/user_photo.jpg?raw=true";
        new RequestOptions();
        Glide.with(activity)
                .load(IMG_URL)
                .apply(RequestOptions
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);
        holder.textIcon.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textTitle;
        TextView textCordinates;
        TextView textIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.geodataItemImg);
            textTitle = itemView.findViewById(R.id.geodataItemTitle);
            textCordinates = itemView.findViewById(R.id.geodataItemCoordinates);
            textIcon = itemView.findViewById(R.id.geodataListItemIcon);
        }
    }
}

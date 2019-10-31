package com.piastres.geodatatableapp.adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.piastres.geodatatableapp.R;

public class GeodataListAdapter extends
        RecyclerView.Adapter<GeodataListAdapter.ViewHolder> {
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

        holder.textIcon.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return 15;
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

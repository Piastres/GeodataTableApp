package com.piastres.geodatatableapp.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.activities.GeodataListActivity;
import com.piastres.geodatatableapp.models.Datum;
import com.piastres.geodatatableapp.utils.RoundedDoubleUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GeodataListAdapter extends
        RecyclerView.Adapter<GeodataListAdapter.ViewHolder> {

    private GeodataListActivity activity;
    private List<Datum> datumList;
    private ListAdapterListener mListener;

    public GeodataListAdapter (Activity activity, List<Datum> datumList,
                               ListAdapterListener mListener){
        this.activity = (GeodataListActivity) activity;
        this.datumList = datumList;
        this.mListener = mListener;
    }

    public interface ListAdapterListener {
        void onClickSwitch(int position);
    }

    @NonNull
    @Override
    public GeodataListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
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

        String IMG_URL = "https://image.freepik.com/free-vector/illustration-gps-navigation_53876-6971.jpg";

        Glide.with(activity)
                .load(IMG_URL)
                .apply(RequestOptions
                        .circleCropTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .priority(Priority.HIGH))
                .into(holder.image);

        Datum datum = datumList.get(position);
        String coordinates = RoundedDoubleUtils.getRoundedDouble(datum.getLat()) + ", "
                                                + RoundedDoubleUtils.getRoundedDouble(datum.getLon());

        holder.textTitle.setText(datum.getName());
        holder.textCoordinates.setText(coordinates);

        holder.layout.setOnClickListener(v -> {
            mListener.onClickSwitch(position);
        });
    }

    @Override
    public int getItemCount() {
        if (datumList == null) {
            return 0;
        }

        return datumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView image;
        TextView textTitle;
        TextView textCoordinates;
        TextView textIcon;
        View viewLine;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.geodataItemLayout);
            image = itemView.findViewById(R.id.geodataItemImg);
            textTitle = itemView.findViewById(R.id.geodataItemName);
            textCoordinates = itemView.findViewById(R.id.geodataItemCoordinates);
            textIcon = itemView.findViewById(R.id.geodataListItemIcon);
            viewLine = itemView.findViewById(R.id.geodataItemLine);
        }
    }

    public void dataChanged(List<Datum> changedDatumList){
        datumList.clear();
        datumList.addAll(changedDatumList);
        notifyDataSetChanged();
    }
}

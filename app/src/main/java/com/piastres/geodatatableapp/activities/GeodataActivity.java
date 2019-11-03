package com.piastres.geodatatableapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.fragments.RequestErrorFragment;
import com.piastres.geodatatableapp.models.Datum;
import com.piastres.geodatatableapp.utils.ErrorDescriptorUtil;
import com.piastres.geodatatableapp.utils.RoundedDoubleUtils;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.Objects;

public class GeodataActivity extends AppCompatActivity {

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String MAPKIT_API_KEY = "key";
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_geodata);

        Typeface font = Typeface.createFromAsset(getAssets(), "fa_solid.ttf" );
        TextView textIconBack = findViewById(R.id.geodataIconBack);
        textIconBack.setTypeface(font);

        textIconBack.setOnClickListener(v -> finish());

        try {
            Datum datum = (Datum) Objects.requireNonNull(getIntent().getExtras()).getSerializable("GEODATA_ITEM");

            TextView textName = findViewById(R.id.geodataName);
            TextView textCountry = findViewById(R.id.geodataCountry);
            TextView textId = findViewById(R.id.geodataId);
            TextView textCoordinates = findViewById(R.id.geodataCoordinates);
            TextView textIconId = findViewById(R.id.geodataIconId);
            TextView textIconCountry = findViewById(R.id.geodataIconCountry);
            TextView textIconCountryCoordinates = findViewById(R.id.geodataIconCoordinates);
            ImageView image = findViewById(R.id.geodataImg);
            mapView = findViewById(R.id.geodataMapView);

            textIconId.setTypeface(font);
            textIconCountry.setTypeface(font);
            textIconCountryCoordinates.setTypeface(font);

            String coordinates = RoundedDoubleUtils.getRoundedDouble(datum.getLat()) + ", "
                    + RoundedDoubleUtils.getRoundedDouble(datum.getLon());
            textCoordinates.setText(coordinates);
            textName.setText(datum.getName());
            textCountry.setText(datum.getCountry());
            textId.setText(datum.getId());

            String IMG_URL = "https://image.freepik.com/free-vector/illustration-gps-navigation_53876-6971.jpg";
            Glide.with(this)
                    .load(IMG_URL)
                    .apply(RequestOptions
                            .circleCropTransform()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .priority(Priority.HIGH))
                    .into(image);

            MapObjectCollection mapObjects = mapView.getMap().getMapObjects();
            Point point = new Point(datum.getLat(), datum.getLon());
            mapView.getMap().setRotateGesturesEnabled(false);
            PlacemarkMapObject mark = mapObjects.addPlacemark(point);
            mapView.getMap().move(new CameraPosition(point, 16.0f, 0.0f, 0.0f),
                    new Animation(Animation.Type.SMOOTH, 0),
                    null);

            CompositeIcon pinIcon = mark.useCompositeIcon();
            pinIcon.setIcon(
                    "pin",
                    ImageProvider.fromResource(this, R.drawable.ic_pin),
                    new IconStyle().setAnchor(new PointF(0.0f, 1.0f))
                            .setRotationType(RotationType.ROTATE)
                            .setZIndex(0f)
                            .setScale(1f));
        } catch (Exception e) {
            showErrorDialogFragment(ErrorDescriptorUtil.checkError(e));
        }
    }

    private void showErrorDialogFragment(String error) {
        Bundle bundle = new Bundle();
        bundle.putString("ERROR_TITLE", error);
        RequestErrorFragment requestErrorFragment = new RequestErrorFragment();
        requestErrorFragment.setArguments(bundle);
        requestErrorFragment.show(getSupportFragmentManager(), "fragment_request_error");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }
}

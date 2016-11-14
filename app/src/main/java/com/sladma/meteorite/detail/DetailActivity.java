package com.sladma.meteorite.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sladma.common.mvvm.BaseActivity;
import com.sladma.meteorite.BR;
import com.sladma.meteorite.R;
import com.sladma.meteorite.databinding.ActivityDetailBinding;
import com.sladma.meteorite.data.model.Meteorite;

import org.parceler.Parcels;

/**
 * Detail activity show info about meteorite.
 * <p>
 * Created by sladma
 */
public class DetailActivity extends BaseActivity<ActivityDetailBinding, IDetailViewModel> implements IDetailView, OnMapReadyCallback {

    private static final String EXTRA_METEORITE = "EXTRA_METEORITE";

    private GoogleMap mMap;

    public static Intent getIntent(Context context, Meteorite meteorite) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_METEORITE, Parcels.wrap(meteorite));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAndBind(new DetailViewModel(), R.layout.activity_detail, BR.viewModel, savedInstanceState);
        Meteorite meteorite = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_METEORITE));
        viewModel.setMeteorite(meteorite);
        initToolbar(meteorite.name);
        initMap();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(final String title) {
        setSupportActionBar(dataBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        viewModel.onMapReady();
    }

    @Override
    public void addMeteoriteMarker(final float latitude, final float longitude, @NonNull final String title, boolean moveCamera) {
        if (mMap != null) {
            LatLng point = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title(title)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin))
            );
            if (moveCamera) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 3));
            }
        }
    }

}

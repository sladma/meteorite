package com.sladma.meteorite.main.list.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sladma.meteorite.R;
import com.sladma.meteorite.data.model.Meteorite;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for list of meteorites
 * <p>
 * Created by sladma
 */
public class MeteoriteAdapter extends RecyclerView.Adapter<MeteoriteItemViewHolder> {

    private List<Meteorite> mMeteorites = Collections.emptyList();

    public void setMeteorites(List<Meteorite> meteorites) {
        mMeteorites = meteorites;
    }

    @Override
    public MeteoriteItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_meteorite, parent, false);
        return new MeteoriteItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeteoriteItemViewHolder holder, int position) {
        holder.getViewModel().setMeteorite(mMeteorites.get(position));
        holder.updateBindings();
    }

    @Override
    public int getItemCount() {
        return mMeteorites.size();
    }
}

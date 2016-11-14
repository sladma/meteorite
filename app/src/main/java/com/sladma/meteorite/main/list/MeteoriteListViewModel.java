package com.sladma.meteorite.main.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.sladma.common.mvvm.BaseViewModel;
import com.sladma.meteorite.data.local.IMeteoriteRepository;
import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.meteorite.main.list.recyclerview.MeteoriteAdapter;

import java.util.List;

/**
 * ViewModel for {@link MeteoriteListFragment}
 * <p>
 * Created by sladma
 */
class MeteoriteListViewModel extends BaseViewModel<IMeteoriteListView> implements IMeteoriteListViewModel {

    private final MeteoriteAdapter mAdapter;
    private final IMeteoriteRepository mMeteoriteRepository;

    MeteoriteListViewModel(@NonNull MeteoriteAdapter adapter, @NonNull IMeteoriteRepository meteoriteRepository) {
        mAdapter = adapter;
        mMeteoriteRepository = meteoriteRepository;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void refresh() {
        mMeteoriteRepository.getMeteoritesSince2011SortedByWeight(new IMeteoriteRepository.Callback() {
            @Override
            public void onDone(List<Meteorite> meteorites) {
                mAdapter.setMeteorites(meteorites);
                mAdapter.notifyDataSetChanged();
                getView().onRefreshFinished(true);
            }

            @Override
            public void onError() {
                getView().onRefreshFinished(false);
            }
        });
    }

}

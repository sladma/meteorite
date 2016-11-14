package com.sladma.meteorite.main.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.sladma.meteorite.MeteoriteApplication;
import com.sladma.meteorite.R;
import com.sladma.meteorite.databinding.FragmentListBinding;
import com.sladma.meteorite.main.list.recyclerview.MeteoriteAdapter;
import com.sladma.common.mvvm.BaseFragment;

/**
 * Fragment shows list with meteorites
 * <p>
 * Created by sladma
 */
public class MeteoriteListFragment extends BaseFragment<FragmentListBinding, IMeteoriteListViewModel> implements IMeteoriteListView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initAndBind(new MeteoriteListViewModel(new MeteoriteAdapter(), MeteoriteApplication.get(this.getContext()).getMeteoriteRepository()), inflater, container, R.layout.fragment_list, BR.viewModel, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.recyclerView.setAdapter(viewModel.getAdapter());
        dataBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
            }
        });
        dataBinding.swipeRefreshLayout.setRefreshing(true);
        viewModel.refresh();
    }

    @Override
    public void onRefreshFinished(boolean success) {
        dataBinding.swipeRefreshLayout.setRefreshing(false);

        if (!success) {
            // Show error message with retry option
            Snackbar.make(dataBinding.recyclerView, R.string.error_refresh_data, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dataBinding.swipeRefreshLayout.setRefreshing(true);
                            viewModel.refresh();
                        }
                    }).show();
        }
    }

}

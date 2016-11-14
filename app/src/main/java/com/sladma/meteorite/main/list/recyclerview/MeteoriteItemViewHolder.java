package com.sladma.meteorite.main.list.recyclerview;

import android.content.Context;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.sladma.common.mvvm.BaseViewHolder;
import com.sladma.meteorite.databinding.ItemMeteoriteBinding;
import com.sladma.meteorite.detail.DetailActivity;
import com.sladma.meteorite.data.model.Meteorite;

/**
 * ViewHolder for list item of meteorite
 * <p>
 * Created by sladma
 */
class MeteoriteItemViewHolder extends BaseViewHolder<ItemMeteoriteBinding, IMeteoriteItemViewModel> implements IMeteoriteItemView {

    MeteoriteItemViewHolder(View itemView) {
        super(itemView);
        initAndBind(new MeteoriteItemViewModel(), itemView, BR.viewModel);
    }

    @Override
    public void showDetail(Meteorite meteorite) {
        final Context context = itemView.getContext();
        context.startActivity(DetailActivity.getIntent(context, meteorite));
    }

}

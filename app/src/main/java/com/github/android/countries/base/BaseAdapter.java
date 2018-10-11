package com.github.android.countries.base;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.github.android.countries.GlideApp;
import com.github.android.countries.R;
import com.github.android.countries.util.SvgSoftwareLayerSetter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected void loadImageFromURL(@NonNull Context context, @NonNull ImageView view,
                                    @NonNull String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        RequestBuilder requestBuilder = GlideApp.with(context)
                .as(PictureDrawable.class)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        requestBuilder
                .load(url)
                .into(view);
    }
}

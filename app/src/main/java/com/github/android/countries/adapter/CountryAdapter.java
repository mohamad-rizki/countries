package com.github.android.countries.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.android.countries.R;
import com.github.android.countries.base.BaseAdapter;
import com.github.android.countries.data.entity.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends BaseAdapter<CountryAdapter.CountryHolder> {

    private Context context;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    class CountryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_flag)
        ImageView ivFlag;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_region)
        TextView tvRegion;

        CountryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_country, viewGroup, false);
        return new CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int i) {

        Country country = countryList.get(i);
        holder.tvName.setText(country.getName());
        holder.tvRegion.setText(country.getRegion());

        loadImageFromURL(context, holder.ivFlag, country.getFlag());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}

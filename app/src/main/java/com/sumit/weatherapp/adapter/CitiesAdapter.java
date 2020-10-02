package com.sumit.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.weatherapp.BR;
import com.sumit.weatherapp.model.Cities;
import com.sumit.weatherapp.model.Daily;
import com.sumit.weatherapp.model.ForecastData;
import com.sumit.weatherapp.model.ForecastList;
import com.sumit.weatherapp.viewmodel.CitiesViewModel;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.GenericViewHolder>{

    private int layoutId;
    private List<Daily> cities;
    private CitiesViewModel viewModel;


    public CitiesAdapter(@LayoutRes int layoutId, CitiesViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }
    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }
    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
         return cities == null ? 0 : cities.size();
    }


    public void setCities(List<Daily> cities) {
        this.cities = cities;
    }



    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(CitiesViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }
}

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
import com.sumit.weatherapp.model.CityList;
import com.sumit.weatherapp.viewmodel.AddCityViewModel;
import com.sumit.weatherapp.viewmodel.CitiesViewModel;

import java.util.List;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.GenericCitiesViewHolder> {
    private int layoutId;
    private List<CityList> cities;
    private AddCityViewModel viewModel;


    public AddCityAdapter(@LayoutRes int layoutId, AddCityViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public GenericCitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new AddCityAdapter.GenericCitiesViewHolder(binding);
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
    @Override
    public void onBindViewHolder(@NonNull GenericCitiesViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }
    public void setCities(List<CityList> cities) {
        this.cities = cities;
    }

    class GenericCitiesViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericCitiesViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(AddCityViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }



}

package com.sumit.weatherapp.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sumit.weatherapp.R;
import com.sumit.weatherapp.adapter.AddCityAdapter;
import com.sumit.weatherapp.api.APIService;
import com.sumit.weatherapp.api.ApiUtils;
import com.sumit.weatherapp.model.CityList;
import com.sumit.weatherapp.model.MainData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddCityViewModel extends ViewModel {
    private CityList cities;
    public MutableLiveData<CityList> selected;
    private AddCityAdapter adapter;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public APIService apiService;
    public void init() {
        cities = new CityList();
        selected = new MutableLiveData<>();
        adapter = new AddCityAdapter(R.layout.add_city_list_item, this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        apiService = ApiUtils.createService(APIService.class);
    }
    public CityList getCityAt(Integer index) {
        if (getCityList().getValue() != null &&
                index != null &&
                getCityList().getValue().size() > index) {
            return getCityList().getValue().get(index);
        }
        return null;
    }

    public AddCityAdapter getAdapter() {
        return adapter;
    }


    public void onItemClick(Integer index) {
        CityList city = getCityAt(index);
        selected.setValue(city);
    }
    public MutableLiveData<List<CityList>> getCities() {
        return getCityList();
    }

    public void setCitiesInAdapter(List<CityList> cityLists) {
        this.adapter.setCities(cityLists);
        this.adapter.notifyDataSetChanged();
    }
    public void fetchList(List<CityList> list) {
        fetchCityList(list,apiService);
    }

    public MutableLiveData<CityList> getSelected() {
        return selected;
    }
    private MutableLiveData<List<CityList>> citiess = new MutableLiveData<>();
    public MutableLiveData<List<CityList>> getCityList() {
        return citiess;
    }
    public void fetchCityList(final List<CityList> list, APIService apiService){
        String ids = "";
        for(int i = 0;i<list.size();i++){
            if(ids.equalsIgnoreCase("")){
                ids = ids+list.get(i).getId();
            }else {
                ids = ids+","+list.get(i).getId();
            }
        }
        Log.e("idssss",""+ids);
        Observable<MainData> obs =  apiService.getListData(""+ids, ApiUtils.ApiKey,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        io.reactivex.Observer<MainData> observer = new io.reactivex.Observer<MainData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MainData data) {
                Log.e("data",""+data.getList().get(0).getMain().getTemp());
                List<CityList> citylist = new ArrayList<CityList>();
                for(int i=0;i<list.size();i++){
                    CityList city = list.get(i);
                    for(int j= 0;j<data.getList().size();j++){
                        if(city.getName().equalsIgnoreCase(data.getList().get(j).getName())){
                            list.get(i).setWeather(data.getList().get(j).getMain().getTemp().toString());
                        }
                    }
                }
                citiess.postValue(list);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error","error"+e.getMessage());
            }

            @Override
            public void onComplete() {

                Log.e("model","Hello");
            }
        };
        obs.subscribe(observer);



    }
}

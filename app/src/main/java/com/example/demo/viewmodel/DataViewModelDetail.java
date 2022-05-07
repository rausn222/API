package com.example.demo.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demo.model.DataModel;
import com.example.demo.model.ImageModel;
import com.example.demo.utils.utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataViewModelDetail extends ViewModel {

    private MutableLiveData<List<DataModel>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<ImageModel>> mutableLiveData1 = new MutableLiveData<>();

    public void init(Context context){

        String jsonFileString = utils.getJsonFromAssets(context, "data.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<DataModel>>() { }.getType();
        Type listUserType1 = new TypeToken<List<ImageModel>>() { }.getType();
        List<DataModel> users = gson.fromJson(jsonFileString, listUserType);
        List<ImageModel> users1 = gson.fromJson(jsonFileString, listUserType1);
        mutableLiveData.postValue(users);
        mutableLiveData1.postValue(users1);
    }

    public LiveData<List<DataModel>> getNewsRepository() {
        return mutableLiveData;
    }

    public LiveData<List<ImageModel>> getNewsRepository1() {
        return mutableLiveData1;
    }

}
package com.example.citypomsjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.citypomsjava.MySQLiteData;

import java.util.List;

public class AllRoutesViewModel extends ViewModel {

    private MutableLiveData<List<MySQLiteData.Transport>> transportList = new MutableLiveData<>();

    public void setTransportList(List<MySQLiteData.Transport> list) {
        transportList.setValue(list);
    }

    public LiveData<List<MySQLiteData.Transport>> getTransportList() {
        return transportList;
    }
}

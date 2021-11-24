package com.example.hairdresserappointment.other;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.hairdresserappointment.R;
import com.example.hairdresserappointment.StartFragment;
import com.example.hairdresserappointment.adapters.AdapterDayInfo;
import com.example.hairdresserappointment.db.ClientAddDataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataInfoViewModel extends AndroidViewModel {

    ClientAddDataBase clientAddDataBase;

    MutableLiveData<List<DataInfo>> dataInfo = new MutableLiveData<>();

    public DataInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<DataInfo>> getLiveData() {
        return dataInfo;
    }

    public void addListInLiveData(StartFragment startFragment, RecyclerView recyclerView) {
        List<DataInfo> list = new ArrayList<>();

        for(int a=0; a<7; a++) {
            list.add(new DataInfo(getDayData(a), getDayName(a), getRecordNow(a), openDate(a)));
        }


        dataInfo.setValue(list);

        getLiveData().observe(startFragment, new Observer<List<DataInfo>>() {
            @Override
            public void onChanged(List<DataInfo> dataInfos) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplication().getApplicationContext()));
                AdapterDayInfo adapterDayInfo = new AdapterDayInfo(dataInfos);
                recyclerView.setAdapter(adapterDayInfo);
            }
        });
    }


    public String getDayName(int data_day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, data_day);
        SimpleDateFormat day_name = createSimpleDateFormat("EEEE");
        String day = day_name.format(calendar.getTime());
        return day;
    }

    public String getDayData(int data_day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, data_day);
        SimpleDateFormat day = createSimpleDateFormat("dd.MM.yyyy");
        String data = day.format(calendar.getTime());
        return data;
    }

    public String getRecordNow(int data_day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, data_day);
        SimpleDateFormat simpleDateFormatID = createSimpleDateFormat("ddMMyyyy");
        int date = Integer.parseInt(simpleDateFormatID.format(calendar.getTime()));
        clientAddDataBase = ClientAddDataBase.getInstance(getApplication().getApplicationContext());
        int day_size = clientAddDataBase.getClientDAO().getListDate(date).size();
        return String.valueOf(day_size);
    }

    public int openDate(int data_day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, data_day);
        SimpleDateFormat simpleDateFormatID = createSimpleDateFormat("ddMMyyyy");
        int openDat = Integer.valueOf(simpleDateFormatID.format(calendar.getTime()));
        return  openDat;
    }



    public SimpleDateFormat createSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault());
    }
}

package com.example.hairdresserappointment;

import android.app.Application;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hairdresserappointment.adapters.ClientAdapter;
import com.example.hairdresserappointment.db.Client;
import com.example.hairdresserappointment.db.ClientAddDataBase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseViewModel extends AndroidViewModel {

    ClientAddDataBase clientAddDataBase = ClientAddDataBase.getInstance(getApplication().getApplicationContext());


    public DataBaseViewModel(@NonNull Application application) {
        super(application);
    }


    public void databaseUpdateClient(Client client) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientAddDataBase.getClientDAO().updateClient(client);
            }
        });
        thread.start();
        thread.join();
    }


    public void databaseAddClient(Client client) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientAddDataBase.getClientDAO().addClient(client);
            }
        });
        thread.start();
    }

    public void databaseDeleteClient(TextView id_in_bd) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientAddDataBase.getClientDAO().deleteClient(new Client(Long.parseLong(id_in_bd.getText().toString())));
            }
        });
        thread.start();

    }


}







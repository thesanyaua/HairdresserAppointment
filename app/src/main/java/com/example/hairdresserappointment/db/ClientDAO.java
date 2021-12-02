package com.example.hairdresserappointment.db;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDAO {

    @Insert
    public long addClient(Client client);

    @Update
    public void updateClient(Client client);

    @Delete
    public void deleteClient(Client client);

    @Query("select*from table_client order by timeHour ASC")
    public List<Client> getListClient();

    @Query("select*from table_client where id==:id")
    public Client getID(int id);

    @Query("select*from table_client where idDate==:idDate order by timeHour ASC")
    public List<Client> getListDate(int idDate);

    @Query("select*from table_client where idDate==:idDate order by timeHour ASC")
    public LiveData<List<Client>> getListDateUI(int idDate);
}

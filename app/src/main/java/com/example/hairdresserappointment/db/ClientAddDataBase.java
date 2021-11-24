package com.example.hairdresserappointment.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Client.class}, version = 2)
public abstract class ClientAddDataBase extends RoomDatabase {

   static ClientAddDataBase clientAddDataBase;

    public abstract ClientDAO getClientDAO();

    public static ClientAddDataBase getInstance(Context context) {
        if(clientAddDataBase==null) {
            clientAddDataBase = Room.databaseBuilder(context, ClientAddDataBase.class, "ClientDatabase").allowMainThreadQueries().build();
        }
        return clientAddDataBase;
    }

}

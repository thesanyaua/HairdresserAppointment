package com.example.hairdresserappointment.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Client.class}, version = 2)
public abstract class ClientAddDataBase extends RoomDatabase {

    public abstract ClientDAO getClientDAO();

}

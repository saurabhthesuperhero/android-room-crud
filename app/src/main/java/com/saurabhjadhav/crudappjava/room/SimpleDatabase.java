package com.saurabhjadhav.crudappjava.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SimpleModel.class}, version = 1)
public abstract class SimpleDatabase extends RoomDatabase {

    public abstract Dao Dao();

}








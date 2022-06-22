package com.saurabhjadhav.crudappjava.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(SimpleModel model);

    @Update
    void update(SimpleModel model);

    @Delete
    void delete(SimpleModel model);

    @Query("DELETE FROM sample_table")
    void deleteAllData();

    @Query("SELECT * FROM sample_table")
    List<SimpleModel> getAllData();
}

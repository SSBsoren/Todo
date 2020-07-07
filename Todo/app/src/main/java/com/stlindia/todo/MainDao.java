package com.stlindia.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.stlindia.todo.model.Todo;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface MainDao {

    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(Todo todo);


    //delete query
    @Delete
    void delete(Todo todo);

    @Update
    void update(Todo todo);



    //Get all data query
    @Query("SELECT * FROM table_name")
    LiveData<List<Todo>> getAll();

}

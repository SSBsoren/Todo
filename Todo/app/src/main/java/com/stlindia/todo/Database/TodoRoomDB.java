package com.stlindia.todo.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.stlindia.todo.MainDao;
import com.stlindia.todo.model.Todo;

//Add database entities
@Database(entities = {Todo.class},version = 1,exportSchema = false)
public abstract class TodoRoomDB extends RoomDatabase {

    //create database instance
    private static TodoRoomDB instance;

    //define database name
    private static String DATABASE_NAME = "database";

    public synchronized static TodoRoomDB getInstance(Context context){
        //when database is null
        //initialize database
        if (instance == null) {
            synchronized (TodoRoomDB.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), TodoRoomDB.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();

                }
            }

        }
        return instance;
    }

    //Create Dao
    public abstract MainDao mainDao();
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(instance).execute();
        }
    };

    private static class populateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private MainDao mDao;

        public populateDbAsyncTask(TodoRoomDB db) {
            this.mDao = db.mainDao();
        }

        @Override
        protected Void doInBackground(Void... todos) {
            mDao.insert(new Todo());
            return null;
        }
    }
}

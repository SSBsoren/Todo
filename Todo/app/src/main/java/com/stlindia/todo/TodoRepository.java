package com.stlindia.todo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.stlindia.todo.Database.TodoRoomDB;
import com.stlindia.todo.model.Todo;

import java.util.List;

import static android.os.Build.ID;

public class TodoRepository {
    private MainDao mTodoDao;
    private LiveData<List<Todo>> mAllTodo;

    public TodoRepository(Application application) {
        TodoRoomDB db = TodoRoomDB.getInstance(application);
        mTodoDao = db.mainDao();
        mAllTodo = mTodoDao.getAll();
    }

    public void insert(Todo todo) {
        new insertAsyncTask(mTodoDao).execute(todo);
    }

    public void delete(Todo todo) {
        new deleteAsyncTask(mTodoDao).execute(todo);

    }

    public void update(Todo todo) {
        new updateAsyncTask(mTodoDao).execute(todo);

    }

    public LiveData<List<Todo>> getAllTodos() {
        return mAllTodo;

    }

    private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private MainDao mAsyncTaskDao;

        insertAsyncTask(MainDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mAsyncTaskDao.insert(todos[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private MainDao mAsyncTaskDao;

        deleteAsyncTask(MainDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mAsyncTaskDao.delete(todos[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Todo, Void, Void> {
        private MainDao mAsyncTaskDao;

        updateAsyncTask(MainDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mAsyncTaskDao.update(todos[0]);
            return null;
        }
    }



}

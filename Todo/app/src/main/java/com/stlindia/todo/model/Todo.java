package com.stlindia.todo.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_name")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int todo_id;

    @ColumnInfo(name = "text")
    private String  text;

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todo_id +
                ", text='" + text + '\'' +
                '}';
    }
}

package com.stlindia.todo;

import com.stlindia.todo.model.Todo;

public interface OnTodoClickListener {
    void deleteTodoItemClicked(int position);

    void  updateTodoItemClicked(Todo tdo);
}

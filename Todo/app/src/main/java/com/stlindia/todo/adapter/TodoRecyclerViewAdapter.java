package com.stlindia.todo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stlindia.todo.OnTodoClickListener;
import com.stlindia.todo.databinding.TodoListItemBinding;
import com.stlindia.todo.model.Todo;

import java.util.List;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder> {

    private OnTodoClickListener customOnClickListener;
    private List<Todo> todoList;
    private Context context;
    private LayoutInflater inflater;
    private static final String TAG = "TodoRecyclerViewAdapter";

    public TodoRecyclerViewAdapter(List<Todo> todoList, Context context, OnTodoClickListener customOnClickListener) {
        this.customOnClickListener = customOnClickListener;
        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        TodoListItemBinding todoDataBinding = TodoListItemBinding.inflate(inflater, parent, false);
        return new TodoViewHolder(todoDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoViewHolder holder, int position) {

        final Todo model = todoList.get(position);
        holder.mBinding.setViewModel(model);
        holder.mBinding.executePendingBindings();

        holder.mBinding.updateTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customOnClickListener != null) {
                    customOnClickListener.updateTodoItemClicked(model);
                }

            }
        });
        holder.mBinding.deleteTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customOnClickListener != null) {
                    customOnClickListener.deleteTodoItemClicked(holder.getAdapterPosition());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Todo getTodoAt(int position) {
        return todoList.get(position);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        private TodoListItemBinding mBinding;

        public TodoViewHolder(@NonNull TodoListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

        }


    }

}




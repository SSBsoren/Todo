package com.stlindia.todo.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.stlindia.todo.OnTodoClickListener;
import com.stlindia.todo.databinding.DialogUpdateBinding;
import com.stlindia.todo.databinding.FragmentTodoListBinding;
import com.stlindia.todo.model.Todo;
import com.stlindia.todo.adapter.TodoRecyclerViewAdapter;
import com.stlindia.todo.Database.TodoRoomDB;
import com.stlindia.todo.model.TodoViewModel;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment implements OnTodoClickListener {

    private FragmentTodoListBinding binding;
    private List<Todo> todoList = new ArrayList<>();
    private TodoRecyclerViewAdapter adapter;

    private TodoRoomDB database;
    private TodoViewModel todoViewModel;

    public TodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        adapter = new TodoRecyclerViewAdapter(todoList, getContext(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.todoItemRv.setLayoutManager(layoutManager);
        binding.todoItemRv.setAdapter(adapter);
        getAllTodoList();


    }

    private void getAllTodoList() {
        todoViewModel.getAllTodos().observe(getActivity(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.setTodoList(todos);
            }
        });
    }

    @Override
    public void deleteTodoItemClicked(int position) {

        todoViewModel.delete(adapter.getTodoAt(position));

        Toast.makeText(getContext(), "item deleted!! " + position, Toast.LENGTH_SHORT).show();

        adapter.notifyDataSetChanged();

    }


    @Override
    public void updateTodoItemClicked(final Todo todos) {


        String sText = todos.getText();

        final Dialog dialog = new Dialog(getContext());
        final DialogUpdateBinding updateBinding = DialogUpdateBinding.inflate(LayoutInflater.from(getContext()));
        dialog.setContentView(updateBinding.getRoot());

        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        updateBinding.updateTodoEdtxt.setText(sText);

        updateBinding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String uText = updateBinding.updateTodoEdtxt.getText().toString().trim();
                todoViewModel.update(todos);
                todoList.clear();
                todos.setText(uText);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Updated successfully!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
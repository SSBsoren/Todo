package com.stlindia.todo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stlindia.todo.R;
import com.stlindia.todo.databinding.FragmentNewTodoBinding;
import com.stlindia.todo.model.Todo;
import com.stlindia.todo.Database.TodoRoomDB;
import com.stlindia.todo.model.TodoViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NewTodoFragment extends Fragment {


    private FragmentNewTodoBinding binding;
    NavController navController = null;
    private List<Todo> todoList = new ArrayList<>();
    private TodoViewModel todoViewModel;

    public NewTodoFragment() {
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
        binding = FragmentNewTodoBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);


        navController = Navigation.findNavController(view);
        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        binding.newTodoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTxt = binding.newTodoEditTxt.getText().toString().trim();
                if (!sTxt.equals("")){
                    Todo todoo = new Todo();
                    todoViewModel.insert(todoo);
                    todoo.setText(sTxt);
                    binding.newTodoEditTxt.setText("");
                    todoList.clear();
                    Toast.makeText(getContext(), "Successfully added in Todo List!!", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}
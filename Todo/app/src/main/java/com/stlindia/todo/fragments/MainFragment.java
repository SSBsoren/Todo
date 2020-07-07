package com.stlindia.todo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stlindia.todo.R;
import com.stlindia.todo.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private NavController navController = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.createNewTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_newTodoFragment);

            }
        });

        binding.viewTodoListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainFragment_to_todoListFragment);

            }
        });
    }
}
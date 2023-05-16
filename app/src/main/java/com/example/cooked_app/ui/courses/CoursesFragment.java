package com.example.cooked_app.ui.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooked_app.Activities.AboutActivity;
import com.example.cooked_app.Activities.YouTubeActivity;
import com.example.cooked_app.Adapter.CourseAdapter;
import com.example.cooked_app.Model.Course_Model;
import com.example.cooked_app.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {
    private CoursesViewModel mViewModel;
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<Course_Model> courseList;

    public static CoursesFragment newInstance() {
        return new CoursesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_courses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        courseList = new ArrayList<>();
        initializeCourses();

        adapter = new CourseAdapter(getActivity(), courseList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initializeCourses() {
        // Add sample courses here
        // TODO: Create courses table
        courseList.add(new Course_Model("Burger Cooking Basics",
                "Learn the essentials of creating the ultimate burger experience.",
                R.drawable.burgerpic, "fgIRny6WRAo"));
        courseList.add(new Course_Model("Pesto Making at Home",
                "Master the art of making Pesto in your own kitchen.",
                R.drawable.pestopic, "HU_CNivkxaw"));
        // ... more courses
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CoursesViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.nav_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
    }
}
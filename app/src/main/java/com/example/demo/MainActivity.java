package com.example.demo;

import android.os.Bundle;

import com.example.demo.adapters.ItemAdapter;
import com.example.demo.model.DataModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.model.ImageModel;
import com.example.demo.viewmodel.DataViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageModel> articleArrayList = new ArrayList<>();
    ItemAdapter newsAdapter;
    RecyclerView rvHeadline;
    DataViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeadline = findViewById(R.id.rvNews);
        newsViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        newsViewModel.init(getApplicationContext());
        newsViewModel.getNewsRepository1().observe(this, newsResponse -> {
            List<ImageModel> newsArticles = newsResponse;
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new ItemAdapter(MainActivity.this, articleArrayList);
            rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            rvHeadline.setAdapter(newsAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }
}
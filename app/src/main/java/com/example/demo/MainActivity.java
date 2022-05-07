package com.example.demo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.demo.adapters.ItemAdapter;
import com.example.demo.model.DataModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.model.ImageModel;
import com.example.demo.viewmodel.DataViewModel;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageModel> articleArrayList = new ArrayList<>();
    ItemAdapter newsAdapter;
    RecyclerView rvHeadline;
    DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isInternetAvailable()){
            rvHeadline = findViewById(R.id.rvNews);
            dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
            dataViewModel.init(getApplicationContext());
            dataViewModel.getNewsRepository1().observe(this, newsResponse -> {
                List<ImageModel> newsArticles = newsResponse;
                articleArrayList.addAll(newsArticles);
                newsAdapter.notifyDataSetChanged();
            });
            setupRecyclerView();
        }
        else{
            Toast.makeText(MainActivity.this,"Please connect your Internet Connection", Toast.LENGTH_SHORT).show();
        }

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

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
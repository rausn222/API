package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.demo.databinding.ActivityMainBinding;
import com.example.demo.model.DataModel;
import com.example.demo.model.ImageModel;
import com.example.demo.viewmodel.DataViewModel;

import java.util.List;

public class Display extends AppCompatActivity {
    ImageView iv;
    TextView tv_title,tv_desc,tv_copyright,tv_date;
    DataViewModel newsViewModel;
    DataModel dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        iv = findViewById(R.id.hdImage);
        tv_copyright = findViewById(R.id.copyright);
        tv_date = findViewById(R.id.date);
        tv_desc = findViewById(R.id.description);
        tv_title = findViewById(R.id.textView_title);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("index");
            newsViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
            newsViewModel.init(getApplicationContext());
            newsViewModel.getNewsRepository().observe(this, newsResponse -> {
                List<DataModel> newsArticles = newsResponse;
                dm = newsArticles.get(value);
                tv_title.setText(dm.getTitle());
                tv_desc.setText(dm.getExplanation());
                tv_copyright.setText(dm.getCopyright());
                tv_date.setText(dm.getDate());
                Glide.with(getApplicationContext()).load(dm.getHdurl()).into(iv);
            });
        }
    }
}
package com.example.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.demo.databinding.ActivityMainBinding;
import com.example.demo.model.DataModel;
import com.example.demo.model.ImageModel;
import com.example.demo.viewmodel.DataViewModel;

import java.util.List;

public class Display extends AppCompatActivity {
    ImageView iv;
    TextView tv_title,tv_desc,tv_copyright,tv_date;
    DataViewModel dataViewModel;
    DataModel dm;
    ProgressBar pg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        iv = findViewById(R.id.hdImage);
        tv_copyright = findViewById(R.id.copyright);
        tv_date = findViewById(R.id.date);
        tv_desc = findViewById(R.id.description);
        tv_title = findViewById(R.id.textView_title);
        pg1 = findViewById(R.id.progress1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("index");
            dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
            dataViewModel.init(getApplicationContext());
            dataViewModel.getNewsRepository().observe(this, newsResponse -> {
                List<DataModel> newsArticles = newsResponse;
                dm = newsArticles.get(value);
                tv_title.setText(dm.getTitle());
                tv_desc.setText(dm.getExplanation());
                tv_copyright.setText(dm.getCopyright());
                tv_date.setText(dm.getDate());
                if(dm.getHdurl()!=null){
                    Glide.with(getApplicationContext()).load(dm.getHdurl()).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            pg1.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            pg1.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(iv);
                }
                else{
                    iv.setVisibility(View.GONE);
                }
            });
        }
    }
}
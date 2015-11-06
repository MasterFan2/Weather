package com.masterfan.weather.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.masterfan.weather.R;
import com.masterfan.weather.ui.base.ActivityFeature;
import com.masterfan.weather.ui.base.BaseActivity;

@ActivityFeature(layout = R.layout.activity_comments)
public class CommentsActivity extends BaseActivity {


    @Override
    public void initialize() {

    }

    @Override
    public boolean onKeydown() {
        return false;
    }
}

package com.masterfan.weather.ui;

import android.os.Handler;
import android.os.Message;

import com.masterfan.weather.R;
import com.masterfan.weather.ui.base.ActivityFeature;
import com.masterfan.weather.ui.base.BaseActivity;

/**
 * Created by 13510 on 2015/11/4.
 */
@ActivityFeature(layout = R.layout.activity_launcher, full_screen = true)
public class LauncherActivity extends BaseActivity {
    @Override
    public void initialize() {

        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                animStart(MainActivity.class);
                finish();
            }
        }.sendEmptyMessageDelayed(0, 3500);
    }

    @Override
    public boolean onKeydown() {
        return false;
    }
}

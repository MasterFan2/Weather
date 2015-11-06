package com.masterfan.weather.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.masterfan.weather.R;
import com.masterfan.weather.ui.base.ActivityFeature;
import com.masterfan.weather.ui.base.BaseActivity;
import com.masterfan.weather.util.T;
import com.masterfan.weather.widget.dialog.MTDialog;
import com.masterfan.weather.widget.dialog.OnClickListener;
import com.masterfan.weather.widget.mdialog.MasterDialog;

import butterknife.Bind;

@ActivityFeature(layout = R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnClickListener {

    //widget
    @Bind(R.id.home_title_district)
    TextView titlTxt;


    private LocationClient mLocationClient;
    private MyLocationListener listener ;

    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor="bd09";

//    private MTDialog dialog;
    private MasterDialog dialog;

//    private AlertDialog dialog;

    @Override
    public void initialize() {

        dialog = new MasterDialog.Builder(context).create();
        dialog.setCancelable(false);
//        dialog = new AlertDialog.Builder(context).create();
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_position, null);
//        dialog.setView(view);

//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_position, null);
//        ViewHolder holder = new ViewHolder(view);
//        dialog = new MTDialog.Builder(context)
//                .setContentHolder(holder)
//                .setCancelable(false)
//                .setGravity(MTDialog.Gravity.TOP)
//                .setOnClickListener(this)
//                .create();

        //
        mLocationClient = new LocationClient(context);
        listener = new MyLocationListener();
        mLocationClient.registerLocationListener(listener);

        //
        initLocation();

        //
        dialog.show();
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mLocationClient.start();
            }
        }.sendEmptyMessageDelayed(0, 2500);
    }

    @Override
    public boolean onKeydown() {
        animFinish();
        return false;
    }

    @Override
    public void onClick(MTDialog dialog, View view) {
        switch (view.getId()) {
            case 1:
                dialog.dismiss();
                break;
        }
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if(location != null) {
                titlTxt.setText(location.getDistrict());
                dialog.dismiss();
                mLocationClient.stop();
            }
        }
    }

    /**
     * 配置定位信息
     */
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }
}

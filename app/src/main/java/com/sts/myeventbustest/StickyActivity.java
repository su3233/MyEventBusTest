package com.sts.myeventbustest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.sts.myeventbustest.event.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StickyActivity extends AppCompatActivity {

    private TextView tv_show_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        initView();
    }

    private void initView() {
        tv_show_2 = findViewById(R.id.tv_view2_show);
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public void getMessage(MyEvent myEvent){
        if (!TextUtils.isEmpty(myEvent.message)) {
            tv_show_2.setText(myEvent.message);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

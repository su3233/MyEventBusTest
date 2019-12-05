package com.sts.myeventbustest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sts.myeventbustest.event.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author SuTs
 * @create 2019/12/4 17:17
 * @Describe 本app实现eventBus的基本使用和postDlay \n
 * view1_common实现向本页面发送event \n
 * view_sticky实现向本页面和另一个activity发送event
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_show = findViewById(R.id.tv_view_show);
        findViewById(R.id.bt_view_1).setOnClickListener(this);
        findViewById(R.id.bt_view_sticky).setOnClickListener(this);
        findViewById(R.id.bt_goto).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_view_1:
                EventBus.getDefault().post(MyEvent.getInstance("我是一个事件", "1"));
                break;

            case R.id.bt_view_sticky:
                EventBus.getDefault().postSticky(MyEvent.getInstance("我是一个事件2", "2"));
                break;

            case R.id.bt_goto:
                startActivity(new Intent(MainActivity.this, StickyActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(MyEvent myEvent) {
        if (!TextUtils.isEmpty(myEvent.message)) {
            tv_show.setText(myEvent.message);
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

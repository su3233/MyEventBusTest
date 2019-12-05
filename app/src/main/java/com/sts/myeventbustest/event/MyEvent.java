package com.sts.myeventbustest.event;

/**
 * @author SuTs
 * @create 2019/12/4 16:50
 * @Describe
 */
public class MyEvent {

    public final String message;
    public final String tag;

    public static MyEvent getInstance(String message,String tag) {
        return new MyEvent(message,tag);
    }

    private MyEvent(String message,String tag) {
        this.message = message;
        this.tag = tag;
    }
}

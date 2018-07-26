package com.meme.caoyi5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TiaoZi extends AppCompatActivity {

    private TextView textView;

    private String  s = "从前，有一片大草原....\n" +
            "草原上有一只聪明的小白兔和贪吃的小绵羊，\n" +
            "上次小白兔做了雪地摩托，这次小白兔继续学习。\n" +
            "小绵羊开雪地摩托玩的很开心，他叫小白兔带带他。\n" +
            "然后他们两个就一起学习。学着学着小绵羊就变瘦了..." +
            "然后，小绵羊呼噜呼噜睡着了" +
            "旁边的小白兔继续学习，等小绵羊醒来。。\n" +
            "小白兔做好了一台桂林米粉机\n" +
            "这台机器可以源源不断的自动做出香喷喷的桂林米粉\n" +
            "小绵羊就赖在小白兔的米粉机下不走了\n" +
            "他吃了一碗又一碗，吃饱了就睡着了，睡着了……\n" +
            "故事讲完了，躲躲晚安！";;
    private TiaoZiUtil tiaoziUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao_zi);
        textView = ((TextView) findViewById(R.id.tv_text));
        textView.setTextSize(24);
        tiaoziUtil = new TiaoZiUtil(textView, s, 250);//调用构造方法，直接开启

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}


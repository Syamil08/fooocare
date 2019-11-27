package com.example.fooocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_head,tv_section;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_head     = (TextView) findViewById(R.id.tv_head);
        tv_section  = (TextView) findViewById(R.id.tv_section);
int a = 0;
//        Memanggil library animation
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv_head.startAnimation(myanim);
        tv_section.startAnimation(myanim);
        final Intent onBoarding = new Intent(this,onBoarding.class);
        Thread timer = new Thread(){
            public void run () {
                try {
                    sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    startActivity(onBoarding);
                    finish();
                }
            }
        };

            timer.start();
    }
}

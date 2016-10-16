package com.example.knp.gchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by knp on 10/15/16.
 */

public class SplashAct extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);



        Thread splash_time = new Thread()
        {
            public void run()
            {
                try
                {
                    //Целое значение время отображения картинки:
                    int SplashTimer = 0;
                    //Запускаем цикл длиной в 3 секунды:
                    while(SplashTimer < 1000) {
                        sleep(100);
                        SplashTimer = SplashTimer +100;
                    }
                    startActivity(new Intent(SplashAct.this,ChatActivityMain.class));
                }
                catch (InterruptedException e) {
                    e.printStackTrace(); }
                finally {
                    //Закрываем activity:
                    finish();
                }
            }
        };
        splash_time.start();
    }

}

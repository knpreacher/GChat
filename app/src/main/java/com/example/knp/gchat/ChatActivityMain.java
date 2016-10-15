package com.example.knp.gchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by knp on 10/15/16.
 */

public class ChatActivityMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void signIn(View view) {
        startActivity(new Intent(ChatActivityMain.this,MainActivity.class));
    }
}

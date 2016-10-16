package com.example.knp.gchat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by knp on 10/15/16.
 */

public class ChatActivityMain extends AppCompatActivity {
    EditText etL;
    EditText etP;
    Context context;
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etL = (EditText)findViewById(R.id.etLogin);
        etP = (EditText)findViewById(R.id.etPass);
        tv = (TextView)findViewById(R.id.textView3);

        context = getBaseContext();

    }

    SetPref sp;

    public void signIn(View view) {
                RequestQueue queue = Volley.newRequestQueue(context);
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.72:8080/login/", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //super
                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        //sp.savePref(etL.getText().toString(),etP.getText().toString());
                        startActivity(new Intent(ChatActivityMain.this,MainActivity.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();


                        //TODO НЕ ЗАБУДЬ УБРАТЬ!!!
                        startActivity(new Intent(ChatActivityMain.this,MainActivity.class));
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("login",etL.getText().toString());
                        params.put("password", etP.getText().toString());
                        return params;
                    }
                };
                //tv.setText(etP.getText().toString());
                queue.add(stringRequest);

    }

    public void gotoSignUp(View view) {
        startActivity(new Intent(ChatActivityMain.this,SignUpActivity.class));
    }
    int i = 0;
    @Override
    public void onBackPressed() {
        i++;
        if(i==1){
            Toast.makeText(context, "Press again for exit", Toast.LENGTH_SHORT).show();
        } else if (i==2){
            i=0;
            System.exit(0);
        }

        //super.onBackPressed();
    }
}

package com.example.knp.gchat;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String chatRoomId;
    private RecyclerView recyclerView;
    private ChatRoomThreadAdapter mAdapter;
    private ArrayList<Message> messageArrayList;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private EditText inputMessage;
    private Button btnSend;

    private void initChatActivity(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        messageArrayList = new ArrayList<>();

        mAdapter = new ChatRoomThreadAdapter(this, messageArrayList, "1");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        inputMessage = (EditText)findViewById(R.id.message) ;


    }
    int i = 0;
    public void sendMessage() {
        i++;

        final String message = this.inputMessage.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(getApplicationContext(), "Enter a message", Toast.LENGTH_SHORT).show();
            return;
        }
        Message m1;
        if (i % 2 == 0) {
            User user = new User("1", "lol", null);

            m1 = new Message();
            m1.setId("1");
            m1.setMessage(inputMessage.getText().toString());
            m1.setCreatedAt("tr");
            m1.setUser(user);
        } else {
            User user = new User("2", "lol", null);

            m1 = new Message();
            m1.setId("2");
            m1.setMessage(inputMessage.getText().toString());
            m1.setCreatedAt("tr");
            m1.setUser(user);
        }
        messageArrayList.add(m1);

        mAdapter.notifyDataSetChanged();
        if (mAdapter.getItemCount() > 1) {
            // scrolling to bottom of the recycler view
            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, mAdapter.getItemCount() - 1);
        }
        this.inputMessage.setText("");
    }

    private void invisAll(){
        View[] screens= new View[]{
                findViewById(R.id.inc1),
                findViewById(R.id.inc2),
                findViewById(R.id.inc3),
                findViewById(R.id.inc4)

        };
        for (int i = 0;i<4;i++){
            screens[i].setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

  /*      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
/*        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        initChatActivity();
        //ica.onCreate(null);
        btnSend = (Button)findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pressed", Toast.LENGTH_SHORT).show();
                sendMessage();
                //ica.sendMessage();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        invisAll();
        findViewById(R.id.inc3).setVisibility(View.VISIBLE);
        //ViewFlipper vf = (ViewFlipper)findViewById()


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            invisAll();
            // Handle the camera action
            Toast.makeText(this, "cam", Toast.LENGTH_SHORT).show();
            findViewById(R.id.inc1).setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_chat_list) {
            invisAll();
            Toast.makeText(this, "gal", Toast.LENGTH_SHORT).show();
            findViewById(R.id.inc2).setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_add_chat) {
            invisAll();
            Toast.makeText(this, "sli", Toast.LENGTH_SHORT).show();
            findViewById(R.id.inc3).setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_settings) {
            invisAll();
            Toast.makeText(this, "man", Toast.LENGTH_SHORT).show();
            findViewById(R.id.inc4).setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_log_out){
            //findViewById(R.id.inc2).setVisibility(View.VISIBLE);

            final AlertDialog madb = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this,R.style.myDialog)).create();

            madb.setMessage("Are you sure?");
            madb.setTitle("Log out from account");
            madb.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            madb.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(MainActivity.this,ChatActivityMain.class));
                }
            });
            madb.show();
            //setMessage("Are you sure?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    //

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

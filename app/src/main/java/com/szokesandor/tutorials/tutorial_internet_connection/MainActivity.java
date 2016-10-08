package com.szokesandor.tutorials.tutorial_internet_connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonOnClick(View v)
    {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }
    }
}

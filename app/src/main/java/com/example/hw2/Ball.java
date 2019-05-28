package com.example.hw2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Ball {

    public String[] values;

    public Ball(Context context){
        this.values = context.getResources().getStringArray(R.array.values);
    }

}

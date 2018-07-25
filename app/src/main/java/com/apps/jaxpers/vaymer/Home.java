package com.apps.jaxpers.vaymer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.jaxpers.vaymer.View.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);





    }
    @OnClick(R.id.button_main)
    public void submit(View view) {
        Intent intent =  new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}

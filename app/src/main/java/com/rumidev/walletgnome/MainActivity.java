package com.rumidev.walletgnome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etPurchase;

    private Button bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPurchase = (EditText) findViewById(R.id.etPurchase);
        bAdd = (Button) findViewById(R.id.bAdd);


    }
}

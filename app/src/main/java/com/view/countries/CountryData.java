package com.view.countries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CountryData extends AppCompatActivity {
ArrayList<Pojo> list;
String n,p,a;
TextView t1,t2,t3;
public CountryData(){}


    public CountryData(CountriesList countriesList, ArrayList<Pojo> list) {
    this.list=list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_data);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);t3=findViewById(R.id.t3);
        String name=getIntent().getStringExtra("cap");
        String pop=getIntent().getStringExtra("pop");
        String area=getIntent().getStringExtra("area");
        t1.setText(name);
        t2.setText(area);
        t3.setText(pop);

        }

    public void area(View view) {
pop(view);
    }

    public void pop(View view) {
        Intent i=new Intent(this,CountriesList.class);
        i.putExtra("s","ss");
        startActivity(i);
    }
}

package com.view.countries;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CountriesList extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
StateFullRecyclerview recyclerView;
ArrayList<Pojo> list;
String s,a,p,n;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        recyclerView=findViewById(R.id.recyclerview);
         s=getIntent().getStringExtra("s");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)

                getSupportLoaderManager().initLoader(1, null, this);

        else {
            error();
        }
    }
    private void error() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Network Error");
        builder.setMessage("Internet Connection is Not Available");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
            @Nullable
            @Override
            public String loadInBackground() {
                try {
                    URL url=new URL("https://restcountries.eu/rest/v2/all");
                    HttpURLConnection huc= (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("GET");
                    InputStream is=huc.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while((line=br.readLine())!=null)
                    {
                        sb.append(line);
                    }
                    return sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        list=new ArrayList<Pojo>();
        //Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
        try {


            JSONArray array=new JSONArray(data);
            for(int i = 0; i< array.length(); i++)
            {
                JSONObject res=array.getJSONObject(i);
                String name=res.getString("name");
                String cap=res.getString("capital");
                String pop=res.getString("population");
                String  area=res.getString("area");
                Pojo p=new Pojo(pop,area,name,cap);
                list.add(p);
            }
            if(s==null) {
                MyAdapter adapter = new MyAdapter(this, list);
                CountryData countryData = new CountryData(this, list);
                recyclerView.setAdapter(adapter);
            }
            else{
                for(int i=0;i<list.size();i++){
                    for(int j=i+1;j<list.size();j++){

                        if(Float.parseFloat(list.get(i).getPop().toString())<Float.parseFloat(list.get(j).getPop().toString())){
                            a=list.get(i).getArea();
                            p=list.get(i).getPop();
                            n=list.get(i).getNames();
                            list.get(i).setNames(list.get(j).getNames());
                            list.get(i).setArea(list.get(j).getArea());
                            list.get(i).setPop(list.get(j).getPop());
                            list.get(j).setNames(n);
                            list.get(j).setArea(a);
                            list.get(j).setPop(p);
                        }
                    }
                }
                MyAdapter adapter = new MyAdapter(this, list);
                CountryData countryData = new CountryData(this, list);
                recyclerView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}

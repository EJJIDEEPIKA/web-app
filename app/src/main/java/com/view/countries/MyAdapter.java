package com.view.countries;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.HolderData>   {
    Context countriesList,countryData;
    ArrayList<Pojo> list;
    public MyAdapter(CountriesList countriesList, ArrayList<Pojo> list) {
        this.list=list;
        this.countriesList=countriesList;
    }

    public MyAdapter(CountryData countryData, ArrayList<Pojo> list) {
        this.countryData=countryData;
        this.list=list;
    }

    @NonNull
    @Override
    public MyAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(countriesList).inflate(R.layout.countrynames,parent,false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.HolderData holder, final int position) {
holder.t.setText(list.get(position).getNames().toString());
holder.t.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(countriesList,CountryData.class);
        i.putExtra("cap",list.get(position).getCap());
        i.putExtra("pop",list.get(position).getPop());
        i.putExtra("area",list.get(position).getArea());
        countriesList.startActivity(i);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView t;
        public HolderData(View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.name);
        }
    }
}

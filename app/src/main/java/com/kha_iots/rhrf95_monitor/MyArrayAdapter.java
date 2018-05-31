package com.kha_iots.rhrf95_monitor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class MyArrayAdapter extends ArrayAdapter<JSONObject> {
    private final int mResouce;
    private final Context context;
    MyArrayAdapter(Context context, int resourceID, ArrayList<JSONObject> inputData){
        super(context, resourceID, inputData);
        this.mResouce = resourceID;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView = inflater.inflate(mResouce, parent, false);
        TextView tempView = (TextView) myView.findViewById(R.id.temp);
        TextView humidView = (TextView) myView.findViewById(R.id.humid);
        TextView dateView = (TextView) myView.findViewById(R.id.date);
        JSONObject temporaryObj = getItem(position);
        try{
            tempView.setText(String.valueOf(temporaryObj.getDouble("temperature"))+ "°C");
            humidView.setText(String.valueOf(temporaryObj.getDouble("humid"))+"%");
            dateView.setText(new Date(temporaryObj.getLong("time")).toString());
        }
        catch (JSONException jE){}
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return myView;
    }
}

package com.kha_iots.rhrf95_monitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class DetailsViewActivity extends AppCompatActivity{
    private Button button;
    private TextView t, h, c, d, r;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.detail_view);
        button = (Button) findViewById(R.id.back_button);
        t = (TextView) findViewById(R.id.tD);
        h = (TextView) findViewById(R.id.hD);
        c = (TextView) findViewById(R.id.nD);
        d = (TextView) findViewById(R.id.dD);
        r = (TextView) findViewById(R.id.rD);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        JSONObject jsonObject =  JSON.adapter.getItem(getIntent().getExtras().getInt("position"));
        try{
            t.setText("Temperature: " + jsonObject.getDouble("temperature") + "ºC");
            h.setText("Humidity: " + jsonObject.getDouble("humid")+"%");
            c.setText("Sent from client #" + jsonObject.getString("mssv"));
            d.setText(new Date(jsonObject.getLong("time")).toString());
            r.setText(jsonObject.getString("_id"));
        }
        catch(JSONException jE){

        }
    }
}

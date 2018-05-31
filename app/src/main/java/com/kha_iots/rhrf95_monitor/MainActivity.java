package com.kha_iots.rhrf95_monitor;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);
        final Context context = this;
        new JSON(context, listView).execute("http://test.apitiny.com/api/bkit/data");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyArrayAdapter adapter = (MyArrayAdapter) listView.getAdapter();
                if (adapter != null) adapter.clear();
                new JSON(context, listView).execute("http://test.apitiny.com/api/bkit/data");
            }
        });
    }
}

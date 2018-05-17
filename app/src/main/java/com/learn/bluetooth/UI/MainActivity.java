package com.learn.bluetooth.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.learn.bluetooth.Adapter.BTListAdapter;
import com.learn.bluetooth.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add("On and Off");
        items.add("Scan");
        items.add("Third");
        items.add("");
        items.add("");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BTListAdapter(items, new BTListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    case 0:
                        startActivity(OnOffActivity.class);
                        break;
                    case 1:
                        startActivity(BlueToothActivity.class);
                        break;

                }

            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    private void startActivity(Class classes) {
        Intent intent = new Intent(MainActivity.this, classes);
        startActivity(intent);
    }
}

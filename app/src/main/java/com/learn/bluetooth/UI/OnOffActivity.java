package com.learn.bluetooth.UI;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.learn.bluetooth.R;

public class OnOffActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_off);

        findViewById(R.id.on).setOnClickListener(this);
        findViewById(R.id.off).setOnClickListener(this);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Your device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.on:
                if (bluetoothAdapter != null) {
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent BTEnableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(BTEnableIntent, REQUEST_ENABLE_BT);
                    } else {
                        Toast.makeText(this, "Bluetooth already enabled", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.off:
                if (bluetoothAdapter.isEnabled()) {
                }
                break;
        }
    }


}
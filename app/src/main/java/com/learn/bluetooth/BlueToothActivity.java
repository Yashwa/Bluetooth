package com.learn.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.learn.bluetooth.Adapter.BlueAdapter;

import java.util.ArrayList;

public class BlueToothActivity extends AppCompatActivity {

    Context context = BlueToothActivity.this;
    ListView listView;
    ArrayList<BluetoothDevice> blueModelArrayList;
    BlueAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        listView = (ListView)findViewById(R.id.list_item);
        blueModelArrayList = new ArrayList<>();
        listAdapter = new BlueAdapter(BlueToothActivity.this);
        listAdapter.setObjects(new ArrayList<BluetoothDevice>());
        listView.setAdapter(listAdapter);

        bluetoothScanning();
    }

    private void updateAdapter() {
        listAdapter.setObjects(blueModelArrayList);

    }

    void bluetoothScanning(){

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiver, filter);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

    }


    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                Log.i("Device Name: " , "device " + deviceName);
                Log.i("deviceHardwareAddress " , "hard"  + deviceHardwareAddress);
                if(!blueModelArrayList.contains(device)) {
                    blueModelArrayList.add(device);
                }

                updateAdapter();
            }
        }
    };
}

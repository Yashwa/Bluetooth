package com.learn.bluetooth.Adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.learn.bluetooth.R;

import java.util.ArrayList;

public class BlueAdapter extends BaseAdapter {

    private ArrayList<BluetoothDevice> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public BlueAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setObjects(ArrayList<BluetoothDevice> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public BluetoothDevice getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_adapter, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(BluetoothDevice object, ViewHolder holder) {
        holder.bind(object);
    }

    private class ViewHolder {
        private TextView name;
        private TextView address;

        private ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
        }

        private void bind(BluetoothDevice object) {
            name.setText(object.getName());
            address.setText(object.getAddress());
        }
    }
}


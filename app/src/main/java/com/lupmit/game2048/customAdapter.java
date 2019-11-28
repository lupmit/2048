package com.lupmit.game2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Integer> arr;

    public customAdapter(@NonNull Context context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_grd, null);
        }

        if(arr.size() > 0) {
            o_vuong o = (o_vuong)convertView.findViewById(R.id.item_o);
            o.settext(arr.get(position));
        }
        return convertView;

    }

    @Override
    public void notifyDataSetChanged() {
        arr =maingame.getDatagame().getArrSo();
        super.notifyDataSetChanged();
    }
}

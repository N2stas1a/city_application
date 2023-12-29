package com.example.citypomsjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class ListAdapter extends ArrayAdapter<ListData> {
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item_main, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_main, parent, false);
        }
        View cardView = view.findViewById(R.id.cardViewMain);
        ImageView listImage = cardView.findViewById(R.id.listImage);
        TextView listName = cardView.findViewById(R.id.listName);
        TextView listTime = cardView.findViewById(R.id.listTime);

        if (listData != null) {
            listImage.setImageResource(listData.getImage());
            listName.setText(listData.getName());
            listTime.setText(listData.getDesc());
        }

        return view;
    }

}

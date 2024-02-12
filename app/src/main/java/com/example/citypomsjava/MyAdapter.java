package com.example.citypomsjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Model>modelArrayList;
    private Context context;

    public MyAdapter(ArrayList<Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleviewapi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
    Model model = modelArrayList.get(position);
    holder.sid.setText("Sid:-"+model.getSid()+"\n");
    holder.src_url.setText("Src_url:-"+model.getSrc_url()+"\n");
    holder.title.setText("title:-"+ model.getTitle()+"\n");
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView src_url, sid, title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sid = itemView.findViewById(R.id.sid);
            src_url = itemView.findViewById(R.id.src_url);
            title = itemView.findViewById(R.id.title);
        }
    }
}

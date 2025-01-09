package com.example.recyclevapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {


    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> filteredDataSet;

    public CustomeAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
        this.filteredDataSet = new ArrayList<>(dataSet);
    }

    public void getFilter(String query) {
        ArrayList<DataModel> filteredList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredList.addAll(dataSet);
        } else {
            for (DataModel item : dataSet) {
                if (item.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        updateData(filteredList);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewVersion = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {

        holder.textViewName.setText(filteredDataSet.get(position).getName());
        holder.textViewVersion.setText(filteredDataSet.get(position).getVersion());
        holder.imageView.setImageResource(filteredDataSet.get(position).getImage());

        holder.itemView.setOnClickListener(view -> {
            String itemName = filteredDataSet.get(position).getName();
            Toast.makeText(view.getContext(), itemName + " Clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataSet.size();
    }

    public void updateData(ArrayList<DataModel> newDataset) {
        this.filteredDataSet = newDataset;
        notifyDataSetChanged();
    }


}



package com.example.covidtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable{


    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<ExampleItem> exampleListFull;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView countryname;
        public TextView confcases;
        public TextView activecases;
        public TextView recoveredcases;
        public TextView deadcases;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView1);
            countryname=itemView.findViewById(R.id.countryname);
            activecases=itemView.findViewById(R.id.activecases);
            confcases=itemView.findViewById(R.id.confcases);
            recoveredcases=itemView.findViewById(R.id.recoveredcases);
            deadcases=itemView.findViewById(R.id.deadcases);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList=exampleList;
        exampleListFull=new ArrayList<>(mExampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        ExampleItem currentItem=mExampleList.get(position);

        if(currentItem.getImageResource()!=null) {
            holder.mImageView.setImageBitmap(currentItem.getImageResource());
        }
        else
        {
            holder.mImageView.setImageResource(R.drawable.ic_launcher_background);
        }
        holder.countryname.setText(currentItem.getCountry());
        holder.confcases.setText(currentItem.getTotConfirmed());
        holder.activecases.setText(currentItem.getTotActive());
        holder.recoveredcases.setText(currentItem.getTotRecovered());
        holder.deadcases.setText(currentItem.getTotDeaths());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    @Override
    public Filter getFilter() {
        return exapleFilter;
    }
    private Filter exapleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0)
            {
                filteredList.addAll(exampleListFull);
            }
            else
            {
                String filteredpattern=constraint.toString().toLowerCase().trim();
                for(ExampleItem item: exampleListFull)
                {
                    if(item.getCountry().toLowerCase().contains(filteredpattern))
                    {
                        filteredList.add(item);
                    }
                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void filteredList(ArrayList<ExampleItem> filteredList)
    {

        mExampleList=filteredList;
        exampleListFull=filteredList;
        notifyDataSetChanged();
    }


}

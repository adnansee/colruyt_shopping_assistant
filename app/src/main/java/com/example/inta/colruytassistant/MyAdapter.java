package com.example.inta.colruytassistant;




import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;


/*
* This class would handle the RecyclersViews of the Application
* */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private String[] mDataset;


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(TextView v){
        super(v);



    }



}

}

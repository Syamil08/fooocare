package com.example.fooocare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter  extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
//    buat variable untuk arrayList
    ArrayList<ExampleItem> mExampleList;

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

//    untuk memberikan data terkait position dll maka diatur di onBindViewHolder

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
//        dapatkan posisi sekarang

        ExampleItem currentItem = mExampleList.get(position);


//        kemudian set TextView Berdasarkan Posisi yang didapatkan

        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
        holder.mTextView3.setText(currentItem.getmMakan());
        holder.mTextView4.setText(Integer.toString(currentItem.getmKalori()));

        boolean isExpanded = mExampleList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

//    cara mengambil data array list dari class ExampleItem ke Adapter
//    passing data array list kedalam parameter

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        this.mExampleList = exampleList;
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder{
//        Buat Variable untuk menampung 2 TextView yang telah dibuat
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        RelativeLayout expandableLayout;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
//            cari id TextView tersebut
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.tvMakanPagi);
            mTextView4 = itemView.findViewById(R.id.tvKalori);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            mTextView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExampleItem exampleItem = mExampleList.get(getAdapterPosition());
                    exampleItem.setExpanded(!exampleItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }


}

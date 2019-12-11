package com.example.fooocare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananKarbohidratModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterMakananMalam extends RecyclerView.Adapter<RecyclerViewAdapterMakananMalam.ViewHolder> {

//    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

//    mendefinisikan variable ArrayList
    private ArrayList<String> mNameMalam = new ArrayList<>();
    private ArrayList<String> mKaloriMalam = new ArrayList<>();
    private ArrayList<String> mImagesMalam = new ArrayList<>();
    private ArrayList<String> mKandunganMalam = new ArrayList<>();
    private Context mContextMalam;

    public RecyclerViewAdapterMakananMalam(Context mContext, ArrayList<String> mName, ArrayList<String> mKalori , ArrayList<String> mImages, ArrayList<String> mKandunganMalam) {
        this.mNameMalam = mName;
        this.mKaloriMalam = mKalori;
        this.mImagesMalam = mImages;
        this.mContextMalam = mContext;
        this.mKandunganMalam = mKandunganMalam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardmakanmalam,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

//        untuk holder image menggunakan glide

        Glide.with(mContextMalam)
                .asBitmap()
                .load(mImagesMalam.get(position))
                .into(holder.imageMalam);

        holder.makananMalam.setText(mNameMalam.get(position));
        holder.kaloriMalam.setText(mKaloriMalam.get(position));

        holder.imageMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContextMalam, mNameMalam.get(position),Toast.LENGTH_SHORT).show();
                AgendaListAdapter.agendaMakananMalam.add(new MakananKarbohidratModel(mNameMalam.get(position),Float.valueOf(mKaloriMalam.get(position)),Float.valueOf(mKandunganMalam.get(position)),mImagesMalam.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNameMalam.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageMalam;
        TextView makananMalam;
        TextView kaloriMalam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMalam   = itemView.findViewById(R.id.image_makananMalam);
            makananMalam = itemView.findViewById(R.id.tvMakananCardMalam);
            kaloriMalam  = itemView.findViewById(R.id.tvBanyakKaloriMalam);
        }
    }
}

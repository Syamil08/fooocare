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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterOlahraga extends RecyclerView.Adapter<RecyclerViewAdapterOlahraga.ViewHolder> {

//    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

//    mendefinisikan variable ArrayList
    private ArrayList<String> mNameOlahraga = new ArrayList<>();
    private ArrayList<String> mKaloriOlahraga = new ArrayList<>();
    private ArrayList<String> mImagesOlahraga = new ArrayList<>();
    private Context mContextOlahraga;

    public RecyclerViewAdapterOlahraga(Context mContext, ArrayList<String> mName, ArrayList<String> mKalori , ArrayList<String> mImages) {
        this.mNameOlahraga = mName;
        this.mKaloriOlahraga = mKalori;
        this.mImagesOlahraga = mImages;
        this.mContextOlahraga = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardolahraga,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

//        untuk holder image menggunakan glide

        Glide.with(mContextOlahraga)
                .asBitmap()
                .load(mImagesOlahraga.get(position))
                .into(holder.imageOlahraga);

        holder.namaOlahraga.setText(mNameOlahraga.get(position));
        holder.kaloriOlahraga.setText(mKaloriOlahraga.get(position));

        holder.imageOlahraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContextOlahraga, mNameOlahraga.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNameOlahraga.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageOlahraga;
        TextView namaOlahraga;
        TextView kaloriOlahraga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageOlahraga   = itemView.findViewById(R.id.image_olahraga);
            namaOlahraga = itemView.findViewById(R.id.tvNamaOlga);
            kaloriOlahraga  = itemView.findViewById(R.id.tvBanyakKaloriOlga);
        }
    }
}

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
import com.example.fooocare.Model.MakananModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterMakanan extends RecyclerView.Adapter<RecyclerViewAdapterMakanan.ViewHolder> {

//    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

//    mendefinisikan variable ArrayList
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mKalori = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mKandungan = new ArrayList<>();
    private ArrayList<MakananModel> dataMakanan = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapterMakanan(Context mContext,ArrayList<String> mName,ArrayList<String> mKalori ,ArrayList<String> mImages, ArrayList<String> mKandungan) {
        this.mName = mName;
        this.mKalori = mKalori;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mKandungan = mKandungan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcard,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

//        untuk holder image menggunakan glide

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.makanan.setText(mName.get(position));
        holder.kalori.setText(mKalori.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mName.get(position),Toast.LENGTH_SHORT).show();
                AgendaListAdapter.agendaMakananPagi.add(new MakananKarbohidratModel(mName.get(position),Float.valueOf(mKalori.get(position)),Float.valueOf(mKandungan.get(position)),mImages.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView makanan;
        TextView kalori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image   = itemView.findViewById(R.id.image_makanan);
            makanan = itemView.findViewById(R.id.tvMakananCard);
            kalori  = itemView.findViewById(R.id.tvBanyakKalori);
        }
    }
}

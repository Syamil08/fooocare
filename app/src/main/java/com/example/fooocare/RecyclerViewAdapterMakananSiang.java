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

public class RecyclerViewAdapterMakananSiang extends RecyclerView.Adapter<RecyclerViewAdapterMakananSiang.ViewHolder> {

//    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

//    mendefinisikan variable ArrayList
    private ArrayList<String> mNameSiang = new ArrayList<>();
    private ArrayList<String> mKaloriSiang = new ArrayList<>();
    private ArrayList<String> mImagesSiang = new ArrayList<>();
    private ArrayList<String> mKandunganSiang = new ArrayList<>();
    private Context mContextSiang;

    public RecyclerViewAdapterMakananSiang(Context mContext, ArrayList<String> mName, ArrayList<String> mKalori , ArrayList<String> mImages, ArrayList<String> mKandunganSiang) {
        this.mNameSiang = mName;
        this.mKaloriSiang = mKalori;
        this.mImagesSiang = mImages;
        this.mContextSiang = mContext;
        this.mKandunganSiang = mKandunganSiang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardmakansiang,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

//        untuk holder image menggunakan glide

        Glide.with(mContextSiang)
                .asBitmap()
                .load(mImagesSiang.get(position))
                .into(holder.imageSiang);

        holder.makananSiang.setText(mNameSiang.get(position));
        holder.kaloriSiang.setText(mKaloriSiang.get(position));

        holder.imageSiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContextSiang, mNameSiang.get(position),Toast.LENGTH_SHORT).show();
                AgendaListAdapter.agendaMakananSiang.add(new MakananKarbohidratModel(mNameSiang.get(position),Float.valueOf(mKaloriSiang.get(position)),Float.valueOf(mKandunganSiang.get(position)),mImagesSiang.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNameSiang.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageSiang;
        TextView makananSiang;
        TextView kaloriSiang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageSiang   = itemView.findViewById(R.id.image_makananSiang);
            makananSiang = itemView.findViewById(R.id.tvMakananCardSiang);
            kaloriSiang  = itemView.findViewById(R.id.tvBanyakKaloriSiang);
        }
    }
}

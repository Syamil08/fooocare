package com.example.fooocare;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterMakanan extends RecyclerView.Adapter<RecyclerViewAdapterMakanan.ViewHolder> {

//    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

//    mendefinisikan variable ArrayList

    private ArrayList<MakananModel> dataMakanan;

    private Context mContext;

    public RecyclerViewAdapterMakanan(Context mContext, ArrayList<MakananModel> list) {
        this.mContext = mContext;
        this.dataMakanan = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_makan_pagi,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MakananModel currentItem = dataMakanan.get(position);
//        untuk holder image menggunakan glide
        final boolean[] clicked = new boolean[1];
        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.image);

        holder.makanan.setText(currentItem.getNama());
        if(currentItem instanceof MakananKarbohidratModel){
            holder.kalori.setText(currentItem.getKalori() +" cal / "+ ((MakananKarbohidratModel) currentItem).getKarbohidrat()+"gram");
        }
        else if (currentItem instanceof MakananProteinModel){
            holder.kalori.setText(currentItem.getKalori() +" cal / "+ ((MakananProteinModel) currentItem).getProtein()+"gram");
        }


        holder.mBtn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mBtn_confirm.setBackground(mContext.getDrawable(R.drawable.btn_cardhome_checked));
                holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
                currentItem.setChecked(true);
                HomeFragment.KurangiKalori(currentItem.getKalori());
                Log.d("Kalori mau makan", String.valueOf(currentItem.getKalori()));
            }
        });
        if(currentItem.isChecked() == true){
            holder.mBtn_confirm.setBackground(mContext.getDrawable(R.drawable.btn_cardhome_checked));
            holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
        }
    }

    @Override
    public int getItemCount() {
        return dataMakanan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView makanan;
        TextView kalori;
        ImageButton mBtn_confirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image   = itemView.findViewById(R.id.imageMenuPagi);
            makanan = itemView.findViewById(R.id.textNamaMenuPagi);
            kalori  = itemView.findViewById(R.id.textKaloriMenuPagi);
            mBtn_confirm = itemView.findViewById(R.id.btn_confirm);
        }
    }
}

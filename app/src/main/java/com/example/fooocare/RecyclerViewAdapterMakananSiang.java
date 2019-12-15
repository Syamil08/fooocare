package com.example.fooocare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterMakananSiang extends RecyclerView.Adapter<RecyclerViewAdapterMakananSiang.ViewHolder> {

    //    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";

    //    mendefinisikan variable ArrayList
    private ArrayList<MakananModel> dataMakanan;
    private Context mContextSiang;

    public RecyclerViewAdapterMakananSiang(Context mContext, ArrayList<MakananModel> list) {
        dataMakanan = list;
        this.mContextSiang = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardmakansiang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

//        untuk holder image menggunakan glide
        final MakananModel currentItem = dataMakanan.get(position);

        Glide.with(mContextSiang)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.imageSiang);

        holder.makananSiang.setText(currentItem.getNama());
        if(currentItem instanceof MakananKarbohidratModel){
            holder.kaloriSiang.setText(currentItem.getKalori() +" cal / "+ ((MakananKarbohidratModel) currentItem).getKarbohidrat()+"gram");
        }
        else if (currentItem instanceof MakananProteinModel) {
            holder.kaloriSiang.setText(currentItem.getKalori() + " cal / " + ((MakananProteinModel) currentItem).getProtein() + "gram");
        }

        holder.mBtn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mBtn_confirm.setBackground(mContextSiang.getDrawable(R.drawable.btn_cardhome_checked));
                holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
                currentItem.setChecked(true);
                HomeFragment.KurangiKalori(currentItem.getKalori());
                if(currentItem instanceof MakananProteinModel){
                    HomeFragment.KurangiProtein((int) ((MakananProteinModel) currentItem).getProtein());
                }
                else if(currentItem instanceof  MakananKarbohidratModel)
                    HomeFragment.KurangiKarbo((int) ((MakananKarbohidratModel) currentItem).getKarbohidrat());
                Log.d("Kalori mau makan", String.valueOf(currentItem.getKalori()));
            }
        });
        if(currentItem.isChecked() == true){
            holder.mBtn_confirm.setBackground(mContextSiang.getDrawable(R.drawable.btn_cardhome_checked));
            holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
        }
    }

    @Override
    public int getItemCount() {
        return dataMakanan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageSiang;
        TextView makananSiang;
        TextView kaloriSiang;
        ImageButton mBtn_confirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageSiang = itemView.findViewById(R.id.imageMenuSiang);
            makananSiang = itemView.findViewById(R.id.textNamaMenuSiang);
            kaloriSiang = itemView.findViewById(R.id.textKaloriMenuSiang);
            mBtn_confirm = itemView.findViewById(R.id.btn_confirm);
        }
    }
}

package com.example.fooocare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananModel;

import java.util.ArrayList;

public class AgendaMakanSiangAdapter extends RecyclerView.Adapter<AgendaMakanSiangAdapter.AgendaListViewHolder> {

    ArrayList<MakananModel> listAgenda;
    Context mContext;

    public AgendaMakanSiangAdapter(Context mContext, ArrayList<MakananModel> listAgenda) {
        this.listAgenda = listAgenda;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AgendaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agendamakansiang, parent, false);
        AgendaListViewHolder holder = new AgendaListViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaListViewHolder holder, int position) {
        MakananModel currentItem = listAgenda.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.imageAgendaSiang);
        holder._mJudul.setText(currentItem.getNama());
        holder._mkalori.setText(String.valueOf(currentItem.getKalori()));

    }

    @Override
    public int getItemCount() {
        try {
            return listAgenda.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class AgendaListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAgendaSiang;
        public TextView _mJudul, _mkalori;
        public RelativeLayout expandableLayout;

        public AgendaListViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageAgendaSiang = itemView.findViewById(R.id.imageAgendasiang);
//            _mJudul = (TextView) itemView.findViewById(R.id.textJudulsiang);
//            _mkalori = (TextView) itemView.findViewById(R.id.calorisiang);


        }
    }
}

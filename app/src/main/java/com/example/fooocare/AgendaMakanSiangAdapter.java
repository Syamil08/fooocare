package com.example.fooocare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AgendaMakanSiangAdapter extends RecyclerView.Adapter<AgendaMakanSiangAdapter.AgendaListViewHolder> {
    int posisiKlik;
    ArrayList<MakananModel> listAgenda;
    Context mContext;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference menu;

    public AgendaMakanSiangAdapter(Context mContext, ArrayList<MakananModel> listAgenda) {
        this.listAgenda = listAgenda;
        this.mContext = mContext;
    }

    public AgendaMakanSiangAdapter(Context mContext,ArrayList<MakananModel> listAgenda,int posisi) {
        this.listAgenda = listAgenda;
        this.mContext = mContext;
        this.posisiKlik = posisi;
    }

    @NonNull
    @Override
    public AgendaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agendamakansiang, parent, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        menu = FirebaseDatabase.getInstance().getReference().child("Agenda").child(user.getUid()).child(String.valueOf(posisiKlik));
        AgendaListViewHolder holder = new AgendaListViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaListViewHolder holder, int position) {
        final MakananModel currentItem = listAgenda.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.imageAgendaSiang);
        holder._mJudul.setText(currentItem.getNama());
        if(currentItem instanceof MakananKarbohidratModel){
            holder._mkalori.setText(currentItem.getKalori() +" cal / "+ ((MakananKarbohidratModel) currentItem).getKarbohidrat()+"gram");
        }
        else if (currentItem instanceof MakananProteinModel){
            holder._mkalori.setText(currentItem.getKalori() +" cal / "+ ((MakananProteinModel) currentItem).getProtein()+"gram");
        }
        holder.mBtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"Test", Toast.LENGTH_SHORT).show();
                listAgenda.add(currentItem);
                Log.d("Menu Siang", String.valueOf(HomeFragment.listMakananSiang.size()));
                menu.child("Menu Siang").child(String.valueOf(HomeFragment.listMakananSiang.size())).setValue(currentItem);
                HomeFragment.listMakananSiang.add(currentItem);
            }
        });

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
        public ImageButton mBtnTambah;

        public AgendaListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAgendaSiang = itemView.findViewById(R.id.imageAgendasiang);
            _mJudul = (TextView) itemView.findViewById(R.id.textJudulsiang);
            _mkalori = (TextView) itemView.findViewById(R.id.caloriSiang);
            mBtnTambah = (ImageButton)itemView.findViewById(R.id.btn_tambah);

        }
    }
}

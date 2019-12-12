package com.example.fooocare;

import android.content.Context;
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

import java.util.ArrayList;

public class AgendaMakanMalamAdapter extends RecyclerView.Adapter<AgendaMakanMalamAdapter.AgendaListViewHolder> {

    ArrayList<MakananModel> listAgenda;
    Context mContext;

    public AgendaMakanMalamAdapter(Context mContext, ArrayList<MakananModel> listAgenda) {
        this.listAgenda = listAgenda;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AgendaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardmakanmalam, parent, false);
        AgendaListViewHolder holder = new AgendaListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaListViewHolder holder, int position) {
        final MakananModel currentItem = listAgenda.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.imageAgendaMalam);
        holder._mJudul.setText(currentItem.getNama());
        holder._mkalori.setText(String.valueOf(currentItem.getKalori()));
        if (currentItem instanceof MakananKarbohidratModel) {
            holder._mkalori.setText(currentItem.getKalori() + " cal / " + ((MakananKarbohidratModel) currentItem).getKarbohidrat() + "gram");
        } else if (currentItem instanceof MakananProteinModel) {
            holder._mkalori.setText(currentItem.getKalori() + " cal / " + ((MakananProteinModel) currentItem).getProtein() + "gram");
        }
        holder.mBtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
                listAgenda.add(currentItem);
                HomeFragment.listMakananMalam.add(currentItem);
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
        ImageView imageAgendaMalam;
        public TextView _mJudul, _mkalori;
        public RelativeLayout expandableLayout;
        public ImageButton mBtnTambah;

        public AgendaListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAgendaMalam = itemView.findViewById(R.id.imageMenuMalam);
            _mJudul = (TextView) itemView.findViewById(R.id.textNamaMenuMalam);
            _mkalori = (TextView) itemView.findViewById(R.id.textKaloriMenuMalam);
            mBtnTambah = (ImageButton) itemView.findViewById(R.id.btn_tambah);
        }
    }
}

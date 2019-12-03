package com.example.fooocare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AgendaListAdapter extends RecyclerView.Adapter<AgendaListAdapter.AgendaListViewHolder> {

    ArrayList<AgendaItem> listAgenda;

    public AgendaListAdapter(ArrayList<AgendaItem> listAgenda) {
        this.listAgenda = listAgenda;
    }

    @NonNull
    @Override
    public AgendaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_list, parent, false);
        AgendaListViewHolder holder = new AgendaListViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaListViewHolder holder, int position) {
        AgendaItem currentItem = listAgenda.get(position);
        holder.mTxtPertandingan.setText(currentItem.getPertandingan());
        holder.mTxtTanggal.setText(currentItem.getTanggal());
    }

    @Override
    public int getItemCount() {
        return listAgenda.size();
    }

    public class AgendaListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtPertandingan, mTxtTanggal;

        public AgendaListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtPertandingan = (TextView) itemView.findViewById(R.id.txt_pertandingan);
            mTxtTanggal = (TextView) itemView.findViewById(R.id.txt_tanggal);
        }
    }
}

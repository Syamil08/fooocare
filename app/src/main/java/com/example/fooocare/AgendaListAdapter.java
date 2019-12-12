package com.example.fooocare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

public class AgendaListAdapter extends RecyclerView.Adapter<AgendaListAdapter.AgendaListViewHolder> {

    ArrayList<AgendaItem> listAgenda;
    private RecyclerView agendaPagi,agendaSiang,agendaMalam;
    private RecyclerView.LayoutManager mLayoutAgendaPagi,mLayoutAgendaSiang,mLayoutAgendaMalam;
    private RecyclerView.Adapter mAdapterAgendaPagi,mAdapterAgendaSiang,mAdapterAgendaMalam;
    public static ArrayList<MakananModel> agendaMakananPagi = new ArrayList<>(),agendaMakananSiang = new ArrayList<>(),agendaMakananMalam = new ArrayList<>();

    View v;
    public AgendaListAdapter(ArrayList<AgendaItem> listAgenda) {
        this.listAgenda = listAgenda;
    }

    @NonNull
    @Override
    public AgendaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_list, parent, false);
        buildRecyclerViewAgendaPagi(parent);
        buildRecyclerViewAgendaSiang(parent);
        buildRecyclerViewAgendaMalam(parent);
        AgendaListViewHolder holder = new AgendaListViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaListViewHolder holder, int position) {
        AgendaItem currentItem = listAgenda.get(position);
        holder.mTxtPertandingan.setText(currentItem.getPertandingan());
        holder.mTxtTanggal.setText(currentItem.getTanggal());

        boolean isExpanded = listAgenda.get(position).expanded;
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listAgenda.size();
    }

    public class AgendaListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtPertandingan, mTxtTanggal;
        public RelativeLayout expandableLayout;

        public AgendaListViewHolder(@NonNull View itemView) {
            super(itemView);
//            mTxtPertandingan = (TextView) itemView.findViewById(R.id.txt_pertandingan);
//            mTxtTanggal = (TextView) itemView.findViewById(R.id.txt_tanggal);
//            expandableLayout = itemView.findViewById(R.id.agenda_expandable);

//            mTxtPertandingan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AgendaItem agenda = listAgenda.get(getAdapterPosition());
//                    agenda.setExpanded(!agenda.isExpanded());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });
        }
    }


    private void buildRecyclerViewAgendaPagi(ViewGroup parent) {
//        agendaPagi = v.findViewById(R.id.recyclerViewAgendaPagi);
        agendaPagi.setHasFixedSize(true);
        mLayoutAgendaPagi = new LinearLayoutManager(parent.getContext());
        mAdapterAgendaPagi = new AgendaMakanPagiAdapter(parent.getContext(),agendaMakananPagi);

        agendaPagi.setLayoutManager(mLayoutAgendaPagi);
        agendaPagi.setAdapter(mAdapterAgendaPagi);
    }

    private void buildRecyclerViewAgendaSiang(ViewGroup parent) {
//        agendaSiang = v.findViewById(R.id.recyclerViewAgendaSiang);
        agendaSiang.setHasFixedSize(true);
        mLayoutAgendaSiang = new LinearLayoutManager(parent.getContext());
        mAdapterAgendaSiang = new AgendaMakanSiangAdapter(parent.getContext(),agendaMakananSiang);

        agendaSiang.setLayoutManager(mLayoutAgendaSiang);
        agendaSiang.setAdapter(mAdapterAgendaSiang);
    }

    private void buildRecyclerViewAgendaMalam(ViewGroup parent) {
//        agendaMalam = v.findViewById(R.id.recyclerViewAgendaMalam);
        agendaMalam.setHasFixedSize(true);
        mLayoutAgendaMalam = new LinearLayoutManager(parent.getContext());
        mAdapterAgendaMalam = new AgendaMakanMalamAdapter(parent.getContext(),agendaMakananMalam);

        agendaMalam.setLayoutManager(mLayoutAgendaMalam);
        agendaMalam.setAdapter(mAdapterAgendaMalam);
    }
}

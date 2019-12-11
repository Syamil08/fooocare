package com.example.fooocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

public class AgendaFragment extends Fragment {
    private View rootview;
    private RecyclerView agenda, agendaSejarah,agendaPagi;
    private RecyclerView.LayoutManager mLayoutAgenda, mLayoutAgendaSejarah,mLayoutAgendaPagi;
    private RecyclerView.Adapter mAdapterAgenda, mAdapterAgendaSejarah,mAdapterAgendaPagi;
    private ArrayList<AgendaItem> AgendaList;
    private ArrayList<AgendaItem> AgendaListSejarah;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_agenda, container, false);
        createListAgenda();
        createListAgendaSejara();
        buildRecyclerViewAgenda();
        buildRecyclerViewAgendaSejarah();

        return rootview;
    }


    public void buildRecyclerViewAgenda(){
        agenda = rootview.findViewById(R.id.recyclerViewAgendaTerdekat);
        agenda.setHasFixedSize(true);
        mLayoutAgenda = new LinearLayoutManager(getContext());
        mAdapterAgenda = new AgendaListAdapter(AgendaList);

        agenda.setLayoutManager(mLayoutAgenda);
        agenda.setAdapter(mAdapterAgenda);
    }

    public void buildRecyclerViewAgendaSejarah(){
        agendaSejarah = rootview.findViewById(R.id.recyclerViewSejarahAgenda);
        agendaSejarah.setHasFixedSize(true);
        mLayoutAgendaSejarah = new LinearLayoutManager(getContext());
        mAdapterAgendaSejarah = new AgendaListAdapter(AgendaListSejarah);

        agendaSejarah.setLayoutManager(mLayoutAgendaSejarah);
        agendaSejarah.setAdapter(mAdapterAgendaSejarah);
    }

    public void createListAgenda(){
        AgendaList = new ArrayList<AgendaItem>();
        AgendaList.add(new AgendaItem("Pertandingan Persahabatan Shopee", "     24 Oktober 2019"));
        AgendaList.add(new AgendaItem("Pertandingan Liga 1 Traveloka", "     25 Oktober 2019"));
    }

    public void createListAgendaSejara(){
        AgendaListSejarah = new ArrayList<AgendaItem>();
        AgendaListSejarah.add(new AgendaItem("Pertandingan Champions League", "     24 November 2019"));
        AgendaListSejarah.add(new AgendaItem("Pertandingan Europa League", "     25 November 2019"));
    }
}
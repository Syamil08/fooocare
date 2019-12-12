package com.example.fooocare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    public static ArrayList<ExampleItem> mExampleList = new ArrayList<>();
    private static View rootview;
    private static RecyclerView agenda, agendaSejarah,agendaPagi;
    private static  RecyclerView.LayoutManager mLayoutAgenda, mLayoutAgendaSejarah,mLayoutAgendaPagi;
    private static  RecyclerView.Adapter mAdapterAgenda, mAdapterAgendaSejarah,mAdapterAgendaPagi;
    private ArrayList<AgendaItem> AgendaList;
    private ArrayList<AgendaItem> AgendaListSejarah;
    private Button tambahAgenda;

    public AgendaFragment(ArrayList<String> list){

    }

    public  AgendaFragment (){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_agenda, container, false);
//        createListAgenda();
//        createListAgendaSejara();
        buildRecyclerViewAgenda();
//        buildRecyclerViewAgendaSejarah();
        Log.d("User 1", String.valueOf(mExampleList));
        tambahAgenda = rootview.findViewById(R.id.btn_tambahAgendaa);
        tambahAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mExampleList.size();
                Log.d("User 2", String.valueOf(position));
                openDialog();
                insertItem(position);
            }
        });

        buildRecyclerViewAgenda();
        return rootview;
    }

    private void openDialog() {
        DialogPage dialogPage = new DialogPage();
        dialogPage.show(getFragmentManager(),"Tambah Agenda");
    }

    public void insertItem(int position){
        mAdapterAgenda.notifyItemInserted(position);
    }

    public static void buildAgenda(){
        agenda = rootview.findViewById(R.id.recyclerViewJadwalPertandingan);
        agenda.setHasFixedSize(true);
        mLayoutAgenda = new LinearLayoutManager(rootview.getContext());
        mAdapterAgenda = new ExampleAdapter(rootview.getContext(),mExampleList);

        agenda.setLayoutManager(mLayoutAgenda);
        agenda.setAdapter(mAdapterAgenda);
    }

    public void buildRecyclerViewAgenda(){
        agenda = rootview.findViewById(R.id.recyclerViewJadwalPertandingan);
        agenda.setHasFixedSize(true);
        mLayoutAgenda = new LinearLayoutManager(getContext());
        mAdapterAgenda = new ExampleAdapter(getContext(),mExampleList);

        agenda.setLayoutManager(mLayoutAgenda);
        agenda.setAdapter(mAdapterAgenda);
    }

    @Override
    public void onResume() {
        super.onResume();
        buildRecyclerViewAgenda();
    }
}
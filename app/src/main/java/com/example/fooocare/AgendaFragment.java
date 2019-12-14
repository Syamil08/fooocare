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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AgendaFragment extends Fragment {
    public static ArrayList<ExampleItem> mExampleList;
    private static View rootview;
    private static RecyclerView agenda, agendaSejarah,agendaPagi;
    private static  RecyclerView.LayoutManager mLayoutAgenda, mLayoutAgendaSejarah,mLayoutAgendaPagi;
    private static  RecyclerView.Adapter mAdapterAgenda, mAdapterAgendaSejarah,mAdapterAgendaPagi;
    private ArrayList<AgendaItem> AgendaListSejarah;
    private Button tambahAgenda;

    FirebaseAuth auth;
    FirebaseUser user;

    public static void setmExampleList(ArrayList<ExampleItem> mExampleList) {
        AgendaFragment.mExampleList = mExampleList;
    }

    DatabaseReference root;


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

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        root = FirebaseDatabase.getInstance().getReference().child("Agenda").child(user.getUid());



        root.addValueEventListener(new ValueEventListener() {
            ArrayList<ExampleItem> listSanpshot = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSanpshot.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    String judul = ds.child("judul").getValue().toString();
                    String tanggal = ds.child("tanggal").getValue().toString();

                    listSanpshot.add(new ExampleItem(judul,tanggal));
                    setmExampleList(listSanpshot);

                }
                buildRecyclerViewAgenda();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



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
//        buildRecyclerViewAgenda();
    }
}
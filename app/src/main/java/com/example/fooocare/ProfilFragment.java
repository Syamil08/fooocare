package com.example.fooocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseUser user;
    View root;

    TextView namaPengguna,nama,email,usia,jenisKelamin,tinggiBadan,beratBadan;

    DatabaseReference reference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profil, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        namaPengguna =root.findViewById(R.id.namaPengguna);
        nama =root.findViewById(R.id.nama);
        email =root.findViewById(R.id.email);
        usia =root.findViewById(R.id.usia);
        jenisKelamin = root.findViewById(R.id.kelamin);
        tinggiBadan =root.findViewById(R.id.tinggiBadan);
        beratBadan = root.findViewById(R.id.berat);


        reference = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _namaPengguna = dataSnapshot.child("namaLengkap").getValue().toString();
                String _nama = dataSnapshot.child("namaLengkap").getValue().toString();
                String _email = dataSnapshot.child("email").getValue().toString();
                String _usia = dataSnapshot.child("usia").getValue().toString();
                String _jenisKelamin = dataSnapshot.child("jenis_kelamin").getValue().toString();
                String _tinggiBadan = dataSnapshot.child("tinggiBadan").getValue().toString();
                String _beratBadan = dataSnapshot.child("beratBadan").getValue().toString();

                namaPengguna.setText("Hallo "+_namaPengguna);
                nama.setText(_nama);
                email.setText(_email);
                usia.setText(_email);
                jenisKelamin.setText(_jenisKelamin);
                tinggiBadan.setText(_tinggiBadan);
                beratBadan.setText(_beratBadan);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



        return root;
    }
}
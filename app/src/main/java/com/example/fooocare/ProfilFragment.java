package com.example.fooocare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooocare.Model.CaloryCounter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class ProfilFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseUser user;
    View root;
    Button btn_logout,btn_editProfile;

    TextView namaPengguna,nama,email,usia,jenisKelamin,tinggiBadan,beratBadan, _kalori;

    DatabaseReference reference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profil, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btn_editProfile = root.findViewById(R.id.btn_edit_profil);
        namaPengguna =root.findViewById(R.id.namaPengguna);
        email =root.findViewById(R.id.emailPengguna);
        usia =root.findViewById(R.id.usiaPengguna);
        jenisKelamin = root.findViewById(R.id.jenisKelamin);
        tinggiBadan =root.findViewById(R.id.tinggiBadan);
        beratBadan = root.findViewById(R.id.beratBadan);
        _kalori = root.findViewById(R.id.kaloriBadan);


        reference = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _namaPengguna = dataSnapshot.child("namaLengkap").getValue().toString();
                String _email = dataSnapshot.child("email").getValue().toString();
                String _usia = dataSnapshot.child("usia").getValue().toString();
                String _jenisKelamin = dataSnapshot.child("jenis_kelamin").getValue().toString();
                String _tinggiBadan = dataSnapshot.child("tinggiBadan").getValue().toString();
                String _beratBadan = dataSnapshot.child("beratBadan").getValue().toString();
                String kalori = String.valueOf((int)CaloryCounter.coutnCalory(Float.valueOf(_beratBadan), Float.valueOf(_usia), _jenisKelamin
                , Float.valueOf(_tinggiBadan), (float)2.3));
                namaPengguna.setText(_namaPengguna);
                email.setText(_email);
                usia.setText(_usia);
                jenisKelamin.setText(_jenisKelamin);
                tinggiBadan.setText(_tinggiBadan);
                beratBadan.setText(_beratBadan);
                _kalori.setText(kalori);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),UpdateProfile.class));
            }
        });


        btn_logout = root.findViewById(R.id.btn_signout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent i = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("isIntroOpened", false);
                editor.commit();
                startActivity(i);

            }
        });

        return root;
    }
}
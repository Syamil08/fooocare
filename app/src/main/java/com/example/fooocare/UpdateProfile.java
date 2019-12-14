package com.example.fooocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference root;

//    RadioGrup
    RadioGroup jenisKelamin;
    RadioButton lakiKelamin,perempuanKelamin;

//    Edit Text
    EditText edtNamaLengkap,edtBerat,edtTinggi,edtUsia;

//    Button
    Button updateProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

//        radio grup
        jenisKelamin = findViewById(R.id.radioGrupKelamin);

//        Edit Text
        edtNamaLengkap = findViewById(R.id.namaLengkap);
        edtBerat = findViewById(R.id.beratBadan);
        edtTinggi = findViewById(R.id.tinggiBadan);
        edtUsia = findViewById(R.id.usia);
        lakiKelamin = findViewById(R.id.lakiKelamin);
        perempuanKelamin = findViewById(R.id.perempuanKelamin);

//        Button
        updateProfil = findViewById(R.id.updateProfil);

        root = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String namaPengguna = dataSnapshot.child("namaLengkap").getValue(String.class);
                String berat = dataSnapshot.child("beratBadan").getValue().toString();
                String tinggi = dataSnapshot.child("tinggiBadan").getValue().toString();
                String usia = dataSnapshot.child("usia").getValue().toString();
                String gender = dataSnapshot.child("jenis_kelamin").getValue().toString();

                edtNamaLengkap.setText(namaPengguna);
                edtBerat.setText(berat);
                edtTinggi.setText(tinggi);
                edtUsia.setText(usia);
                Log.d("gender",gender);
                if (gender.equals("Laki-Laki")){
                    lakiKelamin.setChecked(true);
                }
                if (gender.equals("Perempuan")){
                    perempuanKelamin.setChecked(true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaPengguna, berat,tinggi,usia;
                namaPengguna = edtNamaLengkap.getText().toString().trim();
                berat = edtBerat.getText().toString().trim();
                tinggi = edtTinggi.getText().toString().trim();
                usia = edtUsia.getText().toString().trim();
                int selectedId = jenisKelamin.getCheckedRadioButtonId();
                RadioButton kelamin = (RadioButton) findViewById(selectedId);

                if(kelamin.isChecked()){
                    root.child("jenis_kelamin").setValue(kelamin.getText().toString().trim());
                }

                if(!TextUtils.isEmpty(namaPengguna)){
                    root.child("namaLengkap").setValue(namaPengguna);
                }
                if (!TextUtils.isEmpty(berat)){
                    root.child("beratBadan").setValue(berat);
                }
                if (!TextUtils.isEmpty(tinggi)){
                    root.child("tinggiBadan").setValue(tinggi);
                }
                if (!TextUtils.isEmpty(usia)){
                    root.child("usia").setValue(usia);
                }



                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });
    }
}

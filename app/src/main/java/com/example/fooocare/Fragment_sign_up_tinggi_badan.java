package com.example.fooocare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooocare.Fragment_sign_up_data.OnHeadlineSelectedListener;
import com.example.fooocare.Model.Pengguna;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_sign_up_tinggi_badan extends Fragment implements OnHeadlineSelectedListener {
    public static Pengguna pengguna;
    EditText mTinggiBadan, mBeratBadan;
    Button btnKembali, btnSelesai;
    FirebaseAuth fAuth;
    FirebaseUser firebaseuser;
    DatabaseReference reff;

    public interface FragmentSignUpTinggiBadanListener {
        public void fragmentSignUpTinggiBadanEvent(List<Integer> s);
    }

    FragmentSignUpTinggiBadanListener listener;
    Fragment_sign_up_data.MovePositionListener movePositionListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentSignUpTinggiBadanListener) {
            listener = (FragmentSignUpTinggiBadanListener) context;
            movePositionListener = (Fragment_sign_up_data.MovePositionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data tinggi badan");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up_tinggi_badan, container, false);
        mTinggiBadan = (EditText) v.findViewById(R.id.tinggi_badan);
        mBeratBadan = (EditText) v.findViewById(R.id.berat_badan);
        btnSelesai = (Button) v.findViewById(R.id.btn_selesai);
        btnKembali = (Button) v.findViewById(R.id.btn_prev);
        mBeratBadan.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengguna.setTinggiBadan(174);
                pengguna.setBeratBadan(Integer.valueOf(mBeratBadan.getText().toString()));
                fAuth = FirebaseAuth.getInstance();
                reff = FirebaseDatabase.getInstance().getReference().child("Pengguna");

                Log.d("Nama Pengguna", pengguna.getNamaLengkap());
                Log.d("Tinggi Badan Pengguna", String.valueOf(pengguna.getTinggiBadan()));
                fAuth.createUserWithEmailAndPassword(pengguna.getEmail(), pengguna.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseuser = fAuth.getCurrentUser();
                            reff.child(firebaseuser.getUid()).setValue(pengguna).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity().getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
                                    savePrefsData();
                                    startActivity(new Intent(getActivity().getApplicationContext() , DashboardActivity.class));
                                }
                            });

                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePositionListener.move(0);
            }
        });
        return v;
    }

    @Override
    public void fragmentSignUpEvent(List<String> s) {
        pengguna.setNamaLengkap("Test");
        pengguna.setEmail(s.get(1));
        pengguna.setPassword(s.get(2));
        pengguna.setUsia(Integer.parseInt(s.get(3)));
        pengguna.setJenis_kelamin(s.get(4));
    }

    private void savePrefsData() {

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }
}
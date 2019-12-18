package com.example.fooocare;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sign_up_data extends Fragment {
    RadioGroup jenis_kelamin;
    EditText mNama, mEmail, mPassword, mUsia;
    Button btnNext;
    MovePositionListener listenerPosition;

    private View v = null;

    public Fragment_sign_up_data() {
    }

    public interface OnHeadlineSelectedListener {
        public void fragmentSignUpEvent(List<String> s);
    }

    public interface MovePositionListener{
        public void move(int position);
    }

    OnHeadlineSelectedListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnHeadlineSelectedListener) {
            listener = (OnHeadlineSelectedListener) context;
            listenerPosition = (MovePositionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data");
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
        v = inflater.inflate(R.layout.fragment_sign_u, container, false);
        jenis_kelamin = v.findViewById(R.id.jenis_kelamin);
        mNama = v.findViewById(R.id.nama);
        mEmail = v.findViewById(R.id.email);
        mPassword = v.findViewById(R.id.password);
        mUsia = v.findViewById(R.id.usia);
        btnNext = v.findViewById(R.id.btn_next);

//        jenis_kelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                int selectedId = jenis_kelamin.getCheckedRadioButtonId();
//                RadioButton kelamin = (RadioButton) v.findViewById(selectedId);
//
//            }
//        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(mNama.getText().toString())){
                    mNama.setError("Maaf field tidak boleh dikosongi");
                }
                if (TextUtils.isEmpty(mEmail.getText().toString())){
                    mEmail.setError("Maaf field tidak boleh dikosongi");
                }
                if (TextUtils.isEmpty(mPassword.getText().toString())){
                    mPassword.setError("Maaf field tidak boleh dikosongi");
                }
                if (TextUtils.isEmpty(mUsia.getText().toString())){
                    mUsia.setError("Maaf field tidak boleh dikosongi");
                }
                else {
                    List<String> data = new ArrayList<>();
                    data.add(mNama.getText().toString());
                    data.add(mEmail.getText().toString());
                    data.add(mPassword.getText().toString().trim());
                    data.add(mUsia.getText().toString());
                    int selectedId = jenis_kelamin.getCheckedRadioButtonId();
                    RadioButton kelamin = (RadioButton) v.findViewById(selectedId);
                    data.add(kelamin.getText().toString());
                    listener.fragmentSignUpEvent(data);
                    listenerPosition.move(1);
                }


            }
        });

        return v;
    }

    public List<String> getText(){

        List<String> data = new ArrayList<>();
        data.add(mNama.getText().toString());
        data.add(mEmail.getText().toString());
        data.add(mPassword.getText().toString().trim());
        data.add(mUsia.getText().toString());
        int selectedId = jenis_kelamin.getCheckedRadioButtonId();
        RadioButton kelamin = (RadioButton) v.findViewById(selectedId);
        data.add(kelamin.getText().toString());
        listener.fragmentSignUpEvent(data);

        return data;

    }
}


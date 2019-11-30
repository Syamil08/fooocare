package com.example.fooocare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Fragment_sign_up_data extends Fragment {
    RadioGroup jenis_kelamin;
    EditText mNama, mEmail, mPassword, mUsia;
    public interface OnHeadlineSelectedListener {
        public void fragmentSignUpEvent(List<String> s);
    }

    OnHeadlineSelectedListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  OnHeadlineSelectedListener){
            listener = (OnHeadlineSelectedListener) context;
        }else {
            throw new RuntimeException(context.toString() +"Must implement listener sign up data");
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
        View v =inflater.inflate(R.layout.fragment_sign_u, container, false);
        jenis_kelamin = v.findViewById(R.id.jenis_kelamin);
        mNama = v.findViewById(R.id.nama);
        mEmail = v.findViewById(R.id.email);
        mPassword = v.findViewById(R.id.password);
        mUsia = v.findViewById(R.id.usia);
        int selectedId = jenis_kelamin.getCheckedRadioButtonId();
        RadioButton kelamin = (RadioButton) v.findViewById(selectedId);

        jenis_kelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Inisasi list untuk disend ke activity sign up
                List<String> data = new ArrayList<>();
                data.add(mNama.getText().toString());
                data.add(mEmail.getText().toString());
                data.add(mPassword.getText().toString());
                data.add(mUsia.getText().toString());
//                data.add(kelamin.getText().toString());
                listener.fragmentSignUpEvent(data);
            }
        });

        return v;
    }
}


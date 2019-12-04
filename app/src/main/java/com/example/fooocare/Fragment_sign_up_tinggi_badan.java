package com.example.fooocare;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sign_up_tinggi_badan extends Fragment {

    EditText mTinggiBadan, mBeratBadan;
    Button btnKembali, btnNext;

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
        btnNext = (Button) v.findViewById(R.id.btn_next);
        btnKembali = (Button) v.findViewById(R.id.btn_prev);
        mBeratBadan.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> data = new ArrayList<>();
                data.add(Integer.parseInt(mTinggiBadan.getText().toString()));
                data.add(Integer.parseInt(mBeratBadan.getText().toString()));
                listener.fragmentSignUpTinggiBadanEvent(data);
                movePositionListener.move(2);
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
}
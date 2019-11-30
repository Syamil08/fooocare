package com.example.fooocare;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_sign_up_tinggi_badan extends Fragment {

    EditText mTinggiBadan, mBeratBadan;
    public interface FragmentSignUpTinggiBadanListener {
        public void fragmentSignUpTinggiBadanEvent(List<Integer> s);
    }

    FragmentSignUpTinggiBadanListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentSignUpTinggiBadanListener ){
            listener = (FragmentSignUpTinggiBadanListener) context;
        }else {
            throw new RuntimeException(context.toString() +"Must implement listener sign up data tinggi badan");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTinggiBadan.getText().toString() != null && mBeratBadan.getText().toString() != null){
            List<Integer> data = new ArrayList<>();
            data.add(Integer.parseInt(mTinggiBadan.getText().toString()));
            data.add(Integer.parseInt(mBeratBadan.getText().toString()));
            listener.fragmentSignUpTinggiBadanEvent(data);
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
        return v;
    }
}

package com.example.fooocare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_sign_up_data extends Fragment {
    public interface OnHeadlineSelectedListener {
        public void someEvent(String s);
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

        listener.someEvent("data dari fragment 1");
        return inflater.inflate(R.layout.fragment_sign_u, container, false);
    }
}

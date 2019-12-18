package com.example.fooocare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class DialogPage extends AppCompatDialogFragment {
    ArrayList<ExampleItem> exampleItem = new ArrayList<>();

    EditText _judul,_tanggal;
    DialogPageListener listener;
    DatePickerDialog picker;
    ArrayList<String> list = new ArrayList<>();
    int position;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference root;

    public DialogPage(int position) {
        this.position = position;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        root = FirebaseDatabase.getInstance().getReference().child("Agenda").child(user.getUid());

        builder.setView(view)
                .setTitle("Agendaku")
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String judul = _judul.getText().toString().trim();
                        String tanggal = _tanggal.getText().toString();
                        if (TextUtils.isEmpty(judul)){
                            _judul.setError("Maaf field tidak boleh dikosongi");
                        }
                        if (TextUtils.isEmpty(tanggal)){
                            _tanggal.setError("Maaf field tidak boleh dikosongi");
                        }
                        else
                        {
                            list.add(judul);
                            list.add(tanggal);
                            AgendaFragment.buildAgenda();
                            listener.applyTexts(list);
//                        root untuk setValue
                            root.child(String.valueOf(position)).setValue(new ExampleItem(judul, tanggal, position));
                            Log.d("User",judul + " " +tanggal);
                        }
                    }
                });

        _judul = view.findViewById(R.id.jadwal_agenda);
        _tanggal = view.findViewById(R.id.tanggalPertandingan);
        _tanggal.setInputType(InputType.TYPE_NULL);
        _tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                _tanggal.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogPageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogPageListener{
        void applyTexts(ArrayList<String> data);
    }
}

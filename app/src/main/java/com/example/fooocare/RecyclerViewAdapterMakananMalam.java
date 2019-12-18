package com.example.fooocare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterMakananMalam extends RecyclerView.Adapter<RecyclerViewAdapterMakananMalam.ViewHolder> {

    //    untuk Log (debugging) ini tag nya
    private static final String TAG = "RecyclerViewAdapter";
    private final ArrayList<MakananModel> dataMakanan;

//    mendefinisikan variable ArrayList

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    public static int posisiAgenda;

    private Context mContextMalam;

    public RecyclerViewAdapterMakananMalam(Context mContext, ArrayList<MakananModel> list) {
        this.mContextMalam = mContext;
        this.dataMakanan = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitemcardmakanmalam, parent, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Agenda").child(user.getUid()).child(String.valueOf(posisiAgenda));
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MakananModel currentItem = dataMakanan.get(position);
//        untuk holder image menggunakan glide
        final boolean[] clicked = new boolean[1];
        Glide.with(mContextMalam)
                .asBitmap()
                .load(currentItem.getImages())
                .into(holder.imageMalam);

        holder.makananMalam.setText(currentItem.getNama());
        if (currentItem instanceof MakananKarbohidratModel) {
            holder.kaloriMalam.setText(currentItem.getKalori() + " cal / " + ((MakananKarbohidratModel) currentItem).getKalori() + "gram");
        } else if (currentItem instanceof MakananProteinModel) {
            holder.kaloriMalam.setText(currentItem.getKalori() + " cal / " + ((MakananProteinModel) currentItem).getKalori() + "gram");
        }


        holder.mBtn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mBtn_confirm.setBackground(mContextMalam.getDrawable(R.drawable.btn_cardhome_checked));
                holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
                currentItem.setChecked(true);
                HomeFragment.KurangiKalori(currentItem.getKalori());
                if (currentItem instanceof MakananProteinModel) {
                    HomeFragment.KurangiProtein((int) ((MakananProteinModel) currentItem).getProtein());

                } else if (currentItem instanceof MakananKarbohidratModel)
                    HomeFragment.KurangiKarbo((int) ((MakananKarbohidratModel) currentItem).getKarbohidrat());
                Log.d("Kalori mau makan", String.valueOf(currentItem.getKalori()));
                reference.child("Menu Malam").child(String.valueOf(position)).child("checked").setValue(true);
                holder.mBtn_confirm.setEnabled(false);
            }
        });
        reference.child("Menu Malam").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i =0;
                boolean _isChecked = false;
                try {
                    _isChecked = (boolean)dataSnapshot.child(String.valueOf(position)).child("checked").getValue();
                    if(_isChecked == true){
                        holder.mBtn_confirm.setBackground(mContextMalam.getDrawable(R.drawable.btn_cardhome_checked));
                        holder.mBtn_confirm.setImageResource(R.drawable.ic_check_white);
                        holder.mBtn_confirm.setEnabled(false);
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMakanan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageMalam;
        TextView makananMalam;
        TextView kaloriMalam;
        ImageButton mBtn_confirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMalam = itemView.findViewById(R.id.imageMenuMalam);
            makananMalam = itemView.findViewById(R.id.textNamaMenuMalam);
            kaloriMalam = itemView.findViewById(R.id.textKaloriMenuMalam);
            mBtn_confirm = itemView.findViewById(R.id.btn_confirm);
        }
    }
}

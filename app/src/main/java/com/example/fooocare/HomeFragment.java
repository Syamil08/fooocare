package com.example.fooocare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooocare.Model.CaloryCounter;
import com.example.fooocare.Model.MakananKarboGenerator;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinGenerator;
import com.example.fooocare.Model.MakananProteinModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener, AgendaMakanPagiAdapter.MakananPagiListener {
    public static ArrayList<ExampleItem> mExampleList = new ArrayList<>();
    public static ArrayList<MakananModel> listMakananSiang = new ArrayList<>();
    public static ArrayList<MakananModel> listMakananMalam = new ArrayList<>();
    private RecyclerView mRecyclerView, mRecyclerViewMakanan, mRecyclerViewMakananSiang, mRecyclerViewMakananMalam,
            mRecyclerViewMakananOlahraga, mRecyclerViewMenuPagi, mRecyclerViewMenuSiang, mRecyclerViewMenuMalam;
    private RecyclerView.Adapter mAdapter, mAdapterMakanan, mAdapterMakananSiang, mAdapterMakananMalam, mAdapterMakananOlahraga,
            mAdapterMenuPagi, mAdapterMenuSiang, mAdapterMenuMalam;
    private RecyclerView.LayoutManager mLayoutManager, mLayoutManagerMakanan, mLayoutManagerMakananSiang, mLayoutManagerMakananMalam, mLayoutManagerMakananOlahraga,
            mLayoutMenuMakanPagi, mLayoutMenuMakanSiang, mLayoutMenuMakanMalam;
    private static View rootView;
    static TextView tv_banyakKalori;
    TextView banyakKaloriPagi;
    TextView banyakKaloriSiang;
    TextView banyakKaloriMalam;
    TextView kaloriDimakanPagi;
    TextView kaloriDimakanSian;
    TextView kaloriDimakanMalam;
    private Button buttonInsertAgenda;
    private int line1 = 5, line2 = 6;
    private static float kalori_saat_ini = 0;

    static ProgressBar progressKalori, progressProtein, progressKarbo;

    public void setBanyakKalori(float banyakKalori) {
        this.banyakKalori = banyakKalori;
    }

    private static float banyakKalori;
    private float kaloriAgenda;
    ImageButton tambahPagi, tambahSiang, tambahMalam;
    Spinner spinnerAgenda;


    //    array list makanan rekomendasi makan pagi
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mKalori = new ArrayList<>();
    private ArrayList<String> mKandungan = new ArrayList<>();

    //    array list makanan rekomendasi makan siang
    private ArrayList<String> mNameSiang = new ArrayList<>();
    private ArrayList<String> mKaloriSiang = new ArrayList<>();
    private ArrayList<String> mImagesSiang = new ArrayList<>();
    private ArrayList<String> mKandunganSiang = new ArrayList<>();

    //    array list makanan rekomendasi makan malam
    private ArrayList<String> mNameMalam = new ArrayList<>();
    private ArrayList<String> mKaloriMalam = new ArrayList<>();
    private ArrayList<String> mImagesMalam = new ArrayList<>();
    private ArrayList<String> mKandunganMalam = new ArrayList<>();

    //    array list olahraga yang dilakukan
    private ArrayList<String> mNameOlahraga = new ArrayList<>();
    private ArrayList<String> mKaloriOlahraga = new ArrayList<>();
    private ArrayList<String> mImagesOlahraga = new ArrayList<>();
    private ArrayList<MakananKarbohidratModel> listKarbo;
    private ArrayList<MakananProteinModel> listProtein;
    public static ArrayList<MakananModel> listMakananPagi = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference, agenda, profil;
    int positionSpinner;
    static int banyakProtein;
    static int banyakKarbo;
    static TextView mTextProtein;
    static TextView mTextKarbo;

    public void setBerat(int berat) {
        this.berat = berat;
    }

    int berat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        tambahPagi = rootView.findViewById(R.id.tambahMakanPagi);
        tambahSiang = rootView.findViewById(R.id.tambahMakanSiang);
        tambahMalam = rootView.findViewById(R.id.tambahMakanMalam);
        tv_banyakKalori = rootView.findViewById(R.id.tv_banyakKalori);
        banyakKaloriPagi = rootView.findViewById(R.id.banyakKaloriPagi);
        banyakKaloriSiang = rootView.findViewById(R.id.banyakKaloriSiang);
        banyakKaloriMalam = rootView.findViewById(R.id.banyakKaloriMalam);
        kaloriDimakanPagi = rootView.findViewById(R.id.kaloriDimakan);
        kaloriDimakanSian = rootView.findViewById(R.id.kaloriDimakanSiang);
        kaloriDimakanMalam = rootView.findViewById(R.id.kaloriDimakanMalam);
        mTextProtein = rootView.findViewById(R.id.txt_protein);
        mTextKarbo = rootView.findViewById(R.id.textKarbo);
        progressProtein = rootView.findViewById(R.id.progress_bar_protein);
        progressKarbo = rootView.findViewById(R.id.progress_bar_karbo);
        listMakananPagi.clear();
        listMakananSiang.clear();
        listMakananMalam.clear();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        MakananKarboGenerator generatorKarbo = new MakananKarboGenerator();
        listKarbo = generatorKarbo.getListMakanan();
        MakananProteinGenerator generatorProtein = new MakananProteinGenerator();
        listProtein = generatorProtein.getListMakanan();
        progressKalori = rootView.findViewById(R.id.progressBarKalori);

//        getImagesOlahraga();
//        getImagesMalam();
//        getImagesSiang();
//        getImages();
//        buildRecyclerView();

        reference = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());
        agenda = FirebaseDatabase.getInstance().getReference().child("Agenda").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _namaPengguna = dataSnapshot.child("namaLengkap").getValue().toString();
                String _email = dataSnapshot.child("email").getValue().toString();
                String _usia = dataSnapshot.child("usia").getValue().toString();
                String _jenisKelamin = dataSnapshot.child("jenis_kelamin").getValue().toString();
                String _tinggiBadan = dataSnapshot.child("tinggiBadan").getValue().toString();
                String _beratBadan = dataSnapshot.child("beratBadan").getValue().toString();
                CaloryCounter.GeneratePengali();
                CaloryCounter.GenerateBMR();
                setBerat(Integer.parseInt(_beratBadan));
                banyakKalori = CaloryCounter.coutnCalory(Float.valueOf(_beratBadan), Float.valueOf(_usia), _jenisKelamin, Float.valueOf(_tinggiBadan), (float) 2.3);
                tv_banyakKalori.setText(String.valueOf(banyakKalori));
                progressKalori.setMax((int) banyakKalori);
                progressKalori.setProgress((int) banyakKalori);
                setBanyakKalori(banyakKalori);
                int karbo = (int) banyakKalori/2;
                mTextKarbo.setText(karbo +"g tersisa");
                setBanyakKarbo(karbo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        profil = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());
        profil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _namaPengguna = dataSnapshot.child("namaLengkap").getValue().toString();
                String _email = dataSnapshot.child("email").getValue().toString();
                String _usia = dataSnapshot.child("usia").getValue().toString();
                String _jenisKelamin = dataSnapshot.child("jenis_kelamin").getValue().toString();
                int _tinggiBadan = Integer.valueOf(dataSnapshot.child("tinggiBadan").getValue().toString());
                int _beratBadan = Integer.valueOf(dataSnapshot.child("beratBadan").getValue().toString());

                int protein = (_jenisKelamin.equals("Perempuan"))?
                        MakananProteinGenerator.getProteinPerempuan(_beratBadan) : MakananProteinGenerator.getProteinLaki(_beratBadan);
                mTextProtein.setText(String.valueOf(protein)+"g tersisa");
                progressProtein.setMax(protein);
                progressProtein.setProgress(protein);
                setBanyakProtein(protein);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        agenda.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> item = new ArrayList<>();
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String judul = areaSnapshot.child("judul").getValue(String.class);
                    item.add(judul);
                }

                final Spinner areaSpinner = (Spinner) rootView.findViewById(R.id.spinnerAgenda);
                if (getActivity() != null) {
                    ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, item);
                    areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areasAdapter);
                    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String language = preferences.getString("Pertandingan", "");
                    if (!language.equalsIgnoreCase("")) {
                        int spinnerPosition = areasAdapter.getPosition(language);
                        areaSpinner.setSelection(spinnerPosition);

                    }
                    areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, final long l) {
                            CaloryCounter.GenerateBMR();
                            CaloryCounter.GeneratePengali();
                            float kaloriAgenda = CaloryCounter.agendaCounter(berat, 1);
                            kalori_saat_ini = banyakKalori + (kaloriAgenda / 7);
                            int karboAgenda = (int) kalori_saat_ini /2 ;
                            setBanyakKarbo(karboAgenda);
                            progressKarbo.setMax(karboAgenda);
                            progressKarbo.setProgress(karboAgenda);
                            mTextKarbo.setText(karboAgenda +"g tersisa");
                            tv_banyakKalori.setText(String.valueOf((int)kalori_saat_ini));
                            String text = adapterView.getItemAtPosition(i).toString();
                            final int _position = Math.toIntExact(adapterView.getItemIdAtPosition(i));
                            RecyclerViewAdapterMakanan.posisiAgenda = i;
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("Pertandingan", areaSpinner.getSelectedItem().toString());
                            editor.apply();

                            getMenuMakanSiang();
                            getMenuMakanMalam();
                            Toast.makeText(adapterView.getContext(), text + _position, Toast.LENGTH_LONG).show();
                            setPositionSpinner(_position);
                            agenda.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        long position = (long) ds.child("position").getValue();
                                        if (positionSpinner == position) {
                                            String judul = ds.child("judul").getValue(String.class);
                                            String tanggal = ds.child("tanggal").getValue(String.class);

                                            DatabaseReference refMenuPagi = agenda.child(String.valueOf(positionSpinner)).child("Menu Pagi");

                                            agenda.child(String.valueOf(positionSpinner)).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    listMakananPagi.clear();
                                                    listMakananSiang.clear();
                                                    listMakananMalam.clear();
                                                    String isPagi = null;
                                                    try {
//                                                        isPagi = dataSnapshot.child("Menu Pagi").getValue().toString();
                                                        agenda.child(String.valueOf(positionSpinner)).child("Menu Pagi").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                                    String nama = ds.child("nama").getValue(String.class);
                                                                    long kalori = (long) ds.child("kalori").getValue();
                                                                    String images = ds.child("images").getValue(String.class);
                                                                    double kandungan = 0;
                                                                    try {
                                                                        kandungan = (double) ds.child("karbohidrat").getValue();
                                                                        listMakananPagi.add(new MakananKarbohidratModel(nama, (float) kandungan, (float) kalori, images));
                                                                    } catch (Exception e) {
                                                                        kandungan = (double) ds.child("protein").getValue();
                                                                        listMakananPagi.add(new MakananProteinModel(nama, (float) kalori, (float) kandungan, images));
                                                                    }
                                                                    Log.d("nama", nama + " Kandungan : " + kandungan);

                                                                }
                                                                getMenuMakanPagi();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        String err = (e.getMessage() == null) ? "SD Card failed" : e.getMessage();
                                                        listMakananPagi.clear();
                                                        getMenuMakanPagi();
                                                        Log.e("sdcard-err2:", err);
                                                    }
                                                    try {
//                                                        isPagi = dataSnapshot.child("Menu Pagi").getValue().toString();
                                                        agenda.child(String.valueOf(positionSpinner)).child("Menu Siang").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                                    String nama = ds.child("nama").getValue(String.class);
                                                                    long kalori = (long) ds.child("kalori").getValue();
                                                                    String images = ds.child("images").getValue(String.class);
                                                                    double kandungan = 0;
                                                                    try {
                                                                        kandungan = (double) ds.child("karbohidrat").getValue();
                                                                        listMakananSiang.add(new MakananKarbohidratModel(nama, (float) kandungan, (float) kalori, images));
                                                                    } catch (Exception e) {
                                                                        kandungan = (double) ds.child("protein").getValue();
                                                                        listMakananPagi.add(new MakananProteinModel(nama, (float) kalori, (float) kandungan, images));
                                                                    }
                                                                    Log.d("nama", nama + " Kandungan : " + kandungan);

                                                                }
                                                                getMenuMakanSiang();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        String err = (e.getMessage() == null) ? "SD Card failed" : e.getMessage();
                                                        listMakananSiang.clear();
                                                        getMenuMakanSiang();
                                                        Log.e("sdcard-err2:", err);
                                                    }
                                                    try {
//                                                        isPagi = dataSnapshot.child("Menu Pagi").getValue().toString();
                                                        agenda.child(String.valueOf(positionSpinner)).child("Menu Malam").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                                    String nama = ds.child("nama").getValue(String.class);
                                                                    long kalori = (long) ds.child("kalori").getValue();
                                                                    String images = ds.child("images").getValue(String.class);
                                                                    double kandungan = 0;
                                                                    try {
                                                                        kandungan = (double) ds.child("karbohidrat").getValue();
                                                                        listMakananMalam.add(new MakananKarbohidratModel(nama, (float) kandungan, (float) kalori, images));
                                                                    } catch (Exception e) {
                                                                        kandungan = (double) ds.child("protein").getValue();
                                                                        listMakananMalam.add(new MakananProteinModel(nama, (float) kalori, (float) kandungan, images));
                                                                    }
                                                                    Log.d("nama", nama + " Kandungan : " + kandungan);

                                                                }
                                                                getMenuMakanMalam();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        String err = (e.getMessage() == null) ? "SD Card failed" : e.getMessage();
                                                        listMakananMalam.clear();
                                                        getMenuMakanMalam();
                                                        Log.e("sdcard-err2:", err);
                                                    }
//                                                    Log.d("Ada Pagi", isPagi);
//                                                        if (dataSnapshot.child("Menu Pagi").exists()){
//                                                            agenda.child(String.valueOf(positionSpinner)).child("Menu Pagi").addValueEventListener(new ValueEventListener() {
//                                                                @Override
//                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                                    for (DataSnapshot ds:dataSnapshot.getChildren()){
//                                                                        String nama = ds.child("nama").getValue(String.class);
//                                                                        long kalori = (long)ds.child("kalori").getValue();
//                                                                        String images = ds.child("images").getValue(String.class);
//                                                                        double kandungan = 0;
//                                                                        try {
//                                                                            kandungan = (double) ds.child("karbohidrat").getValue();
//                                                                            listMakananPagi.add(new MakananKarbohidratModel(nama, (float)kandungan, (float)kalori, images));
//                                                                        } catch (Exception e) {
//                                                                            kandungan = (double) ds.child("protein").getValue();
//                                                                            listMakananPagi.add(new MakananProteinModel(nama,(float)kalori, (float)kandungan, images));
//                                                                        }
//                                                                        Log.d("nama",nama+" Kandungan : "+kandungan);
//                                                                        getMenuMakanPagi();
//                                                                    }
//                                                                }
//
//                                                                @Override
//                                                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                                                }
//                                                            });
//                                                        }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            Log.d("db agenda", position + " " + judul + " " + tanggal);

                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tambahPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MakananModel makanan2 : listMakananPagi) {
                    Log.d("Nama Makanan", makanan2.getNama());
                }
                int posisiMakanan = positionSpinner;
                Intent iin = new Intent(getContext(), JenisMakananActivity.class);
                iin.putExtra("tag", posisiMakanan);
                iin.putExtra("Penanda", "Menu Pagi");
                startActivity(iin);
            }
        });

        tambahSiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = "Menu Siang";
                int posisiMakanan = positionSpinner;
                Intent iin = new Intent(getContext(), JenisMakananActivity.class);
                iin.putExtra("tag", posisiMakanan);
                iin.putExtra("Penanda", "Menu Siang");
                startActivity(iin);
            }
        });

        tambahMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = "Menu Malam";
                int posisiMakanan = positionSpinner;
                Intent iin = new Intent(getContext(), JenisMakananActivity.class);
                iin.putExtra("tag", posisiMakanan);
                iin.putExtra("Penanda", "Menu Malam");
                startActivity(iin);
            }
        });
        return rootView;
    }

    public void setPositionSpinner(int positionSpinner) {
        this.positionSpinner = positionSpinner;
    }

    //    prosedur untuk menambah agenda
    public void insertItem(int position) {
        mAdapter.notifyItemInserted(position);
    }


    public void getImages() {
        for (MakananKarbohidratModel karbo : listKarbo) {
            mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mName.add(karbo.getNama());
            mKalori.add(String.valueOf(karbo.getKalori()));
            mKandungan.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein) {
            mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mName.add(protein.getNama());
            mKalori.add(String.valueOf(protein.getKalori()));
            mKandungan.add(String.valueOf(protein.getProtein()));
        }


//        initRecyclerView();

    }

    private void initRecyclerView() {

        mLayoutManagerMakanan = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerViewMakanan    = rootView.findViewById(R.id.recyclerViewMakanan);
        mRecyclerViewMakanan.setLayoutManager(mLayoutManagerMakanan);
//        mAdapterMakanan = new RecyclerViewAdapterMakanan(getContext(), mName, mKalori, mImages, mKandungan);
        mRecyclerViewMakanan.setAdapter(mAdapterMakanan);
    }

    private void getImagesSiang() {

        for (MakananKarbohidratModel karbo : listKarbo) {
            mImagesSiang.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameSiang.add(karbo.getNama());
            mKaloriSiang.add(String.valueOf(karbo.getKalori()));
            mKandunganSiang.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein) {
            mImagesSiang.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameSiang.add(protein.getNama());
            mKaloriSiang.add(String.valueOf(protein.getKalori()));
            mKandunganSiang.add(String.valueOf(protein.getProtein()));
        }


//        initRecyclerViewSiang();
    }

    private void initRecyclerViewSiang() {
        mLayoutManagerMakananSiang = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerViewMakananSiang    = rootView.findViewById(R.id.recyclerViewMakananSiang);
        mRecyclerViewMakananSiang.setLayoutManager(mLayoutManagerMakananSiang);
//        mAdapterMakananSiang = new RecyclerViewAdapterMakananSiang(getContext(), mNameSiang, mKaloriSiang, mImagesSiang, mKandunganSiang);
        mRecyclerViewMakananSiang.setAdapter(mAdapterMakananSiang);
    }

    private void getImagesMalam() {


        for (MakananKarbohidratModel karbo : listKarbo) {
            mImagesMalam.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameMalam.add(karbo.getNama());
            mKaloriMalam.add(String.valueOf(karbo.getKalori()));
            mKandunganMalam.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein) {
            mImagesMalam.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameMalam.add(protein.getNama());
            mKaloriMalam.add(String.valueOf(protein.getKalori()));
            mKandunganMalam.add(String.valueOf(protein.getProtein()));
        }
    }


    private void getImagesOlahraga() {
        mImagesOlahraga.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");

        mImagesOlahraga.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");

        mImagesOlahraga.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");

        mImagesOlahraga.add("https://i.redd.it/j6myfqglup501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");


        mImagesOlahraga.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");


        mImagesOlahraga.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");


        mImagesOlahraga.add("https://i.redd.it/glin0nwndo501.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");


        mImagesOlahraga.add("https://i.redd.it/obx4zydshg601.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");


        mImagesOlahraga.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNameOlahraga.add("Apple");
        mKaloriOlahraga.add("53 cal/100g");

//        initRecyclerViewOlahraga();
    }

    private void initRecyclerViewOlahraga() {
        mLayoutManagerMakananOlahraga = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerViewMakananOlahraga    = rootView.findViewById(R.id.recyclerViewOlahraga);
        mRecyclerViewMakananOlahraga.setLayoutManager(mLayoutManagerMakananOlahraga);
//        mAdapterMakananOlahraga = new RecyclerViewAdapterMakanan(getContext(), mNameOlahraga, mKaloriOlahraga, mImagesOlahraga, mKandungan);
        mRecyclerViewMakananOlahraga.setAdapter(mAdapterMakananOlahraga);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void listenList(MakananModel makanan) {

    }

    public void getMenuMakanPagi() {
        mRecyclerViewMenuPagi = rootView.findViewById(R.id.recyclerViewMakanPagi);
        mLayoutMenuMakanPagi = new LinearLayoutManager(getContext());
        mRecyclerViewMenuPagi.setLayoutManager(mLayoutMenuMakanPagi);
        mAdapterMenuPagi = new RecyclerViewAdapterMakanan(getContext(), HomeFragment.listMakananPagi);
        mRecyclerViewMenuPagi.setAdapter(mAdapterMenuPagi);
        float kalori = Float.valueOf(tv_banyakKalori.getText().toString()) / 3;
        banyakKaloriPagi.setText(String.valueOf(kalori) + " cal");
        float kaloriDimakan = 0;
        for (MakananModel data : listMakananPagi) {
            kaloriDimakan += data.getKalori();
        }
        float kaloriSisa = kalori - kaloriDimakan;
        kaloriDimakanPagi.setText(kaloriSisa + " cal tersisa");
    }

    public void getMenuMakanSiang() {
        mRecyclerViewMenuSiang = rootView.findViewById(R.id.recyclerViewMakanSiang);
        mLayoutMenuMakanSiang = new LinearLayoutManager(getContext());
        mRecyclerViewMenuSiang.setLayoutManager(mLayoutMenuMakanSiang);
        mAdapterMenuSiang = new RecyclerViewAdapterMakananSiang(getContext(), HomeFragment.listMakananSiang);
        mRecyclerViewMenuSiang.setAdapter(mAdapterMenuSiang);
        float kalori = Float.valueOf(tv_banyakKalori.getText().toString()) / 3;
        banyakKaloriSiang.setText(String.valueOf(kalori));
        float kaloriDimakan = 0;
        for (MakananModel data : listMakananSiang) {
            kaloriDimakan += data.getKalori();
        }
        float kaloriSisa = kalori - kaloriDimakan;
        kaloriDimakanSian.setText(kaloriSisa + " cal tersisa");
    }

    public void getMenuMakanMalam() {
        mRecyclerViewMenuMalam = rootView.findViewById(R.id.recylerMakanMalam);
        mLayoutMenuMakanMalam = new LinearLayoutManager(getContext());
        mRecyclerViewMenuMalam.setLayoutManager(mLayoutMenuMakanMalam);
        mAdapterMenuMalam = new RecyclerViewAdapterMakananMalam(getContext(), HomeFragment.listMakananMalam);
        mRecyclerViewMenuMalam.setAdapter(mAdapterMenuMalam);
        float kalori = Float.valueOf(tv_banyakKalori.getText().toString()) / 3;
        banyakKaloriMalam.setText(String.valueOf(kalori));
        float kaloriDimakan = 0;
        for (MakananModel data : listMakananMalam) {
            kaloriDimakan += data.getKalori();
        }
        float kaloriSisa = kalori - kaloriDimakan;
        kaloriDimakanMalam.setText(kaloriSisa + " cal tersisa");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("On Resume", "TEST");
        getMenuMakanPagi();
        getMenuMakanSiang();
        getMenuMakanMalam();

    }

    public static void KurangiKalori(float kalori) {
        if(kalori_saat_ini < kalori)
        {
            Snackbar.make(rootView, "Maaf kalori anda sudah berlebihan",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else
            {
                kalori_saat_ini = kalori_saat_ini - kalori;
                progressKalori.setProgress((int) (progressKalori.getProgress() - kalori));
                tv_banyakKalori.setText(String.valueOf((int)kalori_saat_ini));
            }

    }

    public static void KurangiProtein(int protein){
        banyakProtein = banyakProtein - protein;
        progressProtein.setProgress(progressProtein.getProgress() - protein);
        mTextProtein.setText(banyakProtein +"g tersisa");
    }

    public static void KurangiKarbo(int karbo)
    {
        banyakKarbo = banyakKarbo -karbo;
        progressKarbo.setProgress(progressKarbo.getProgress() - karbo);
        mTextKarbo.setText(banyakKarbo+"g tersisa");
    }

    public static void setListMakananPagi(ArrayList<MakananModel> listMakananPagi) {
        HomeFragment.listMakananPagi = listMakananPagi;

    }

    public int getBanyakProtein() {
        return banyakProtein;
    }

    public void setBanyakProtein(int banyakProtein) {
        this.banyakProtein = banyakProtein;
    }

    public int getBanyakKarbo() {
        return banyakKarbo;
    }

    public void setBanyakKarbo(int banyakKarbo) {
        this.banyakKarbo = banyakKarbo;
    }
}

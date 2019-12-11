package com.example.fooocare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooocare.Model.CaloryCounter;
import com.example.fooocare.Model.MakananKarboGenerator;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananProteinGenerator;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static ArrayList<ExampleItem> mExampleList = new ArrayList<>();
    private RecyclerView mRecyclerView,mRecyclerViewMakanan,mRecyclerViewMakananSiang,mRecyclerViewMakananMalam,mRecyclerViewMakananOlahraga;
    private RecyclerView.Adapter mAdapter,mAdapterMakanan,mAdapterMakananSiang,mAdapterMakananMalam,mAdapterMakananOlahraga;
    private RecyclerView.LayoutManager mLayoutManager,mLayoutManagerMakanan,mLayoutManagerMakananSiang,mLayoutManagerMakananMalam,mLayoutManagerMakananOlahraga;
    private View rootView;
    TextView tv_banyakKalori;
    private Button buttonInsertAgenda;
    private int line1 = 5,line2 = 6;
    private float banyakKalori,kaloriAgenda;
    ImageButton tambahPagi,tambahSiang,tambahMalam;



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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        MakananKarboGenerator generatorKarbo = new MakananKarboGenerator();
        listKarbo = generatorKarbo.getListMakanan();
        MakananProteinGenerator generatorProtein = new MakananProteinGenerator();
        listProtein = generatorProtein.getListMakanan();

        getImagesOlahraga();
        getImagesMalam();
        getImagesSiang();
        getImages();
//        buildRecyclerView();
        tambahPagi = rootView.findViewById(R.id.tambahMakanPagi);
        tv_banyakKalori = rootView.findViewById(R.id.tv_banyakKalori);
//        buttonInsertAgenda = rootView.findViewById(R.id.btn_tambah_agenda);

        tambahPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),JenisMakananActivity.class));
            }
        });


        CaloryCounter.GeneratePengali();
        CaloryCounter.GenerateBMR();
        banyakKalori = CaloryCounter.coutnCalory(60,19,"Laki-Laki",160,(float) 2.3);
        tv_banyakKalori.setText(String.valueOf(banyakKalori));


//        buttonInsertAgenda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = mExampleList.size();
//                openDialog();
//                insertItem(position);
//                kaloriAgenda = CaloryCounter.agendaCounter(60,3);
//                banyakKalori += kaloriAgenda/7;
//                Log.d("Kalori",String.valueOf(kaloriAgenda));
//                tv_banyakKalori.setText(String.valueOf(banyakKalori));
//            }
//        });
        return rootView;
    }

//    untuk membuka dialog,
    public void openDialog(){
        DialogPage dialogPage = new DialogPage();
        dialogPage.show(getFragmentManager(),"Tambah Agenda");
    }


    //    prosedur untuk menambah agenda
    public void insertItem(int position){
        mAdapter.notifyItemInserted(position);
    }



    public void buildRecyclerView(){
//        mRecyclerView = rootView.findViewById(R.id.recyclerViewAgenda);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void getImages(){
        for (MakananKarbohidratModel karbo : listKarbo){
            mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mName.add(karbo.getNama());
            mKalori.add(String.valueOf(karbo.getKalori()));
            mKandungan.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein){
            mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mName.add(protein.getNama());
            mKalori.add(String.valueOf(protein.getKalori()));
            mKandungan.add(String.valueOf(protein.getProtein()));
        }



//        initRecyclerView();

    }

    private void initRecyclerView() {

        mLayoutManagerMakanan   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        mRecyclerViewMakanan    = rootView.findViewById(R.id.recyclerViewMakanan);
        mRecyclerViewMakanan.setLayoutManager(mLayoutManagerMakanan);
        mAdapterMakanan = new RecyclerViewAdapterMakanan(getContext(),mName,mKalori,mImages, mKandungan);
        mRecyclerViewMakanan.setAdapter(mAdapterMakanan);
    }

    private void getImagesSiang() {

        for (MakananKarbohidratModel karbo : listKarbo){
            mImagesSiang.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameSiang.add(karbo.getNama());
            mKaloriSiang.add(String.valueOf(karbo.getKalori()));
            mKandunganSiang.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein){
            mImagesSiang.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameSiang.add(protein.getNama());
            mKaloriSiang.add(String.valueOf(protein.getKalori()));
            mKandunganSiang.add(String.valueOf(protein.getProtein()));
        }


//        initRecyclerViewSiang();
    }

    private void initRecyclerViewSiang() {
        mLayoutManagerMakananSiang   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        mRecyclerViewMakananSiang    = rootView.findViewById(R.id.recyclerViewMakananSiang);
        mRecyclerViewMakananSiang.setLayoutManager(mLayoutManagerMakananSiang);
        mAdapterMakananSiang = new RecyclerViewAdapterMakananSiang(getContext(),mNameSiang,mKaloriSiang,mImagesSiang, mKandunganSiang);
        mRecyclerViewMakananSiang.setAdapter(mAdapterMakananSiang);
    }

    private void getImagesMalam() {


        for (MakananKarbohidratModel karbo : listKarbo){
            mImagesMalam.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameMalam.add(karbo.getNama());
            mKaloriMalam.add(String.valueOf(karbo.getKalori()));
            mKandunganMalam.add(String.valueOf(karbo.getKarbohidrat()));
        }

        for (MakananProteinModel protein : listProtein){
            mImagesMalam.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNameMalam.add(protein.getNama());
            mKaloriMalam.add(String.valueOf(protein.getKalori()));
            mKandunganMalam.add(String.valueOf(protein.getProtein()));
        }

//        initRecyclerViewMalam();
    }

    private void initRecyclerViewMalam() {
        mLayoutManagerMakananMalam   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        mRecyclerViewMakananMalam    = rootView.findViewById(R.id.recyclerViewMakananMalam);
        mRecyclerViewMakananMalam.setLayoutManager(mLayoutManagerMakananMalam);
        mAdapterMakananMalam = new RecyclerViewAdapterMakananMalam(getContext(),mNameMalam,mKaloriMalam,mImagesMalam, mKandunganMalam);
        mRecyclerViewMakananMalam.setAdapter(mAdapterMakananMalam);
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
        mLayoutManagerMakananOlahraga   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        mRecyclerViewMakananOlahraga    = rootView.findViewById(R.id.recyclerViewOlahraga);
        mRecyclerViewMakananOlahraga.setLayoutManager(mLayoutManagerMakananOlahraga);
        mAdapterMakananOlahraga = new RecyclerViewAdapterMakanan(getContext(),mNameOlahraga,mKaloriOlahraga,mImagesOlahraga,mKandungan);
        mRecyclerViewMakananOlahraga.setAdapter(mAdapterMakananOlahraga);
    }
}

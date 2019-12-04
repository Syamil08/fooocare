package com.example.fooocare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView,mRecyclerViewMakanan,mRecyclerViewMakananSiang,mRecyclerViewMakananMalam,mRecyclerViewMakananOlahraga;
    private RecyclerView.Adapter mAdapter,mAdapterMakanan,mAdapterMakananSiang,mAdapterMakananMalam,mAdapterMakananOlahraga;
    private RecyclerView.LayoutManager mLayoutManager,mLayoutManagerMakanan,mLayoutManagerMakananSiang,mLayoutManagerMakananMalam,mLayoutManagerMakananOlahraga;
    private View rootView;
    private Button buttonInsertAgenda;
    private int line1 = 5,line2 = 6;


    //    array list makanan rekomendasi makan pagi
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mKalori = new ArrayList<>();

    //    array list makanan rekomendasi makan siang
    private ArrayList<String> mNameSiang = new ArrayList<>();
    private ArrayList<String> mKaloriSiang = new ArrayList<>();
    private ArrayList<String> mImagesSiang = new ArrayList<>();

    //    array list makanan rekomendasi makan malam
    private ArrayList<String> mNameMalam = new ArrayList<>();
    private ArrayList<String> mKaloriMalam = new ArrayList<>();
    private ArrayList<String> mImagesMalam = new ArrayList<>();

    //    array list olahraga yang dilakukan
    private ArrayList<String> mNameOlahraga = new ArrayList<>();
    private ArrayList<String> mKaloriOlahraga = new ArrayList<>();
    private ArrayList<String> mImagesOlahraga = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        getImagesOlahraga();
        getImagesMalam();
        getImagesSiang();
        getImages();
        createExampleList();
        buildRecyclerView();

        buttonInsertAgenda = rootView.findViewById(R.id.btn_tambah_agenda);

        buttonInsertAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mExampleList.size();
                Log.d("Posisi",Integer.toString(position));
                line1++;
                line2++;
                Log.d("Line 1",Integer.toString(line1));
                Log.d("Line 1",Integer.toString(line2));
                insertItem(position);
            }
        });
        return rootView;
    }

    //    prosedur untuk menambah agenda
    public void insertItem(int position){
        mExampleList.add(position, new ExampleItem("Line "+line1,"Line "+line2,"Makan Pagi",190));
        mAdapter.notifyItemInserted(position);
    }

//    Memabuat sebuah array list
    public void createExampleList(){
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem("Line 1","Line 2","Makan Pagi",110));
        mExampleList.add(new ExampleItem("Line 3","Line 4","Makan Pagi", 120));
        mExampleList.add(new ExampleItem("Line 5","Line 6","Makan Pagi", 200));

    }

    public void buildRecyclerView(){
        mRecyclerView = rootView.findViewById(R.id.recyclerViewAgenda);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void getImages(){
        mImages.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");

        mImages.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");

        mImages.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");

        mImages.add("https://i.redd.it/j6myfqglup501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");


        mImages.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");


        mImages.add("https://i.redd.it/k98uzl68eh501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");


        mImages.add("https://i.redd.it/glin0nwndo501.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");


        mImages.add("https://i.redd.it/obx4zydshg601.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");


        mImages.add("https://i.imgur.com/ZcLLrkY.jpg");
        mName.add("Apple");
        mKalori.add("53 cal/100g");

        initRecyclerView();

    }

    private void initRecyclerView() {

        mLayoutManagerMakanan   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewMakanan    = rootView.findViewById(R.id.recyclerViewMakanan);
        mRecyclerViewMakanan.setLayoutManager(mLayoutManagerMakanan);
        mAdapterMakanan = new RecyclerViewAdapterMakanan(getContext(),mName,mKalori,mImages);
        mRecyclerViewMakanan.setAdapter(mAdapterMakanan);
    }

    private void getImagesSiang() {
        mImagesSiang.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");

        mImagesSiang.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");

        mImagesSiang.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");

        mImagesSiang.add("https://i.redd.it/j6myfqglup501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");


        mImagesSiang.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");


        mImagesSiang.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");


        mImagesSiang.add("https://i.redd.it/glin0nwndo501.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");


        mImagesSiang.add("https://i.redd.it/obx4zydshg601.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");


        mImagesSiang.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNameSiang.add("Apple");
        mKaloriSiang.add("53 cal/100g");

        initRecyclerViewSiang();
    }

    private void initRecyclerViewSiang() {
        mLayoutManagerMakananSiang   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewMakananSiang    = rootView.findViewById(R.id.recyclerViewMakananSiang);
        mRecyclerViewMakananSiang.setLayoutManager(mLayoutManagerMakananSiang);
        mAdapterMakananSiang = new RecyclerViewAdapterMakanan(getContext(),mNameSiang,mKaloriSiang,mImagesSiang);
        mRecyclerViewMakananSiang.setAdapter(mAdapterMakananSiang);
    }

    private void getImagesMalam() {
        mImagesMalam.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");

        mImagesMalam.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");

        mImagesMalam.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");

        mImagesMalam.add("https://i.redd.it/j6myfqglup501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");


        mImagesMalam.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");


        mImagesMalam.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");


        mImagesMalam.add("https://i.redd.it/glin0nwndo501.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");


        mImagesMalam.add("https://i.redd.it/obx4zydshg601.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");


        mImagesMalam.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNameMalam.add("Apple");
        mKaloriMalam.add("53 cal/100g");

        initRecyclerViewMalam();
    }

    private void initRecyclerViewMalam() {
        mLayoutManagerMakananMalam   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewMakananMalam    = rootView.findViewById(R.id.recyclerViewMakananMalam);
        mRecyclerViewMakananMalam.setLayoutManager(mLayoutManagerMakananMalam);
        mAdapterMakananMalam = new RecyclerViewAdapterMakanan(getContext(),mNameMalam,mKaloriMalam,mImagesMalam);
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

        initRecyclerViewOlahraga();
    }

    private void initRecyclerViewOlahraga() {
        mLayoutManagerMakananOlahraga   = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewMakananOlahraga    = rootView.findViewById(R.id.recyclerViewOlahraga);
        mRecyclerViewMakananOlahraga.setLayoutManager(mLayoutManagerMakananOlahraga);
        mAdapterMakananOlahraga = new RecyclerViewAdapterMakanan(getContext(),mNameOlahraga,mKaloriOlahraga,mImagesOlahraga);
        mRecyclerViewMakananOlahraga.setAdapter(mAdapterMakananOlahraga);
    }
}

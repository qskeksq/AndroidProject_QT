package com.example.administrator.memosqver20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.memosqver20.Design.ManageInterface;
import com.example.administrator.memosqver20.Model.DataLab;
import com.example.administrator.memosqver20.Model.Memo;
import com.example.administrator.memosqver20.View.ListView;

import java.util.List;

public class ListFragment extends Fragment {

    ListView listView;
    RecyclerAdapter adapter;
    List<Memo> datas;
    ManageInterface mi;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = new ListView(view, this);
        listView.init();

        mi = (ManageInterface) getActivity();
        datas = DataLab.getLab(getContext()).getMemos();
        adapter = new RecyclerAdapter(datas, mi);
        listView.setRecyclerView(adapter);

        return view;
    }

    // 디테일 프래그먼트로 넘어가는 메소드
    public void to_DetailFrag(){
        mi.changeFragment();
    }
























//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Toast.makeText(getContext(), "onActivityCreated", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onAttachFragment(Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//        Toast.makeText(getContext(), "onAttachFragment", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
//    }
}

